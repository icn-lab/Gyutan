// This software is a language translation version of "Open JTalk" developed by HTS Working Group.
// 
// Copyright (c) 2015 Intelligent Communication Network (Ito-Nose) Laboratory
// Tohoku University
// Copyright (c) 2008-2015  Nagoya Institute of Technology
// Department of Computer Science
// 
// All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
// * Redistributions of source code must retain the above copyright notice, 
// this list of conditions and the following disclaimer.
// * Redistributions in binary form must reproduce the above copyright notice, 
// this list of conditions and the following disclaimer in the documentation 
// and/or other materials provided with the distribution.
// * Neither the name of the "Intelligent Communication Network Laboratory, Tohoku University" nor the names of its contributors 
// may be used to endorse or promote products derived from this software 
// without specific prior written permission.
// 
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
// ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
// WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
// DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
// DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
// (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
// LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
// ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
// SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package org.Gyutan;

import java.io.PrintStream;
import java.io.FileOutputStream;

import org.Sasakama.*;

import net.java.sen.*;

public class Gyutan {
	static final String OOV = "未知語";
	static final String[] REPLACE = 
		{ "～", "〜", 
		  "\\.", "．",
		  "%", "％",
		  "\\+", "＋",
		  "\\*", "＊",
		  "-", "−",
		  "/", "／",
		  "=", "＝"
		};
	
	StringTagger sen;
	Gyutan_NJD njd;
	Gyutan_JPCommon jpcommon;
	Sasakama_Engine engine;
	
	public Boolean initialize(String confPath, String fn_voice){		
		jpcommon = null;
		njd      = null;

		// for Sen;
		Boolean flagSen    = initializeSen(confPath);
				
		// for HTS_Engine;
		Boolean flagEngine = initializeEngine(fn_voice);
		
		return flagSen && flagEngine;
	}
	
	public Boolean initializeSen(String confPath){
		try{
			sen = StringTagger.getInstance(confPath+"/conf/sen.xml");
			/*
			CompositPostProcessor cpp = new CompositPostProcessor();
			cpp.readRules(new BufferedReader(new StringReader("記号-アルファベット")));
			sen.addPostProcessor(cpp);
			*/
			return true;
		} catch (Exception e) {
		      e.printStackTrace();
		      sen = null;
		      return false;
	    }
	}

	public Boolean initializeEngine(String fn_voice){
		String [] fn_voices = new String[1];
		fn_voices[0] = fn_voice;
		engine = new Sasakama_Engine();
		Boolean flag = engine.load(fn_voices);
		
		if(engine.get_full_context_label_format().equals("HTS_TTS_JPN") == false) {
			System.err.println("ERROR: Gyutan.initializeEngine(): hts_voice is not support HTS_TTS_JPN");
			engine.clear();
			engine = null;
			return false;
		}

		return flag;
	}
	
	public Boolean availableSen(){
		if(sen != null)
			return true;
		else
			return false;
	}
	
	public Boolean availableEngine(){
		if(engine != null)
			return true;
		else
			return false;
	}
	
	Token[] analysis_text(String text){
		Token[] token = null;
		
		if(availableSen() == false)
			return null;
		
		try{
			token = sen.analyze(replace(hankakuToZenkaku(text)));
		}catch(Exception e){
			e.printStackTrace();
		}

		return token;
	}
	
	String[] tokenToString(Token[] token){		
		String[] retString = new String[token.length];
		for(int i=0;i < retString.length;i++){
				/*
				System.err.printf("surface:%s, terminfo:%s\n", token[i].getSurface(), token[i].getTermInfo());
				System.err.printf("pos:%s,cform:%s,basic:%s,reading:%s,pron:%s\n", 
						token[i].getPos(), token[i].getCform(), token[i].getBasicString(),token[i].getReading(),token[i].getReading());
				System.err.printf("addInfo:%s\n", token[i].getAddInfo());
				*/
			if(token[i].getPos().equals(OOV) == false){
				retString[i] = token[i].getSurface() + "," + token[i].getTermInfo();					//System.err.printf("%s\n", retString[i]);
			}
			else{
				retString[i] = String.format("%s,%s,*,*,*,*,*,%s,*,*,*,*,*", token[i].getSurface(),OOV,token[i].getSurface());
			}
			
			//System.err.printf("feature[%d]:%s\n", i, retString[i]);
		}
		
		return retString;
	}
	
	public void set_sampling_frequency(int sf){
		engine.set_sampling_frequency(sf);
	}
	
	public void set_fperiod(int fp){
		engine.set_fperiod(fp);
	}
	
	public void set_alpha(double a){
		engine.set_alpha(a);
	}
	
	public void set_beta(double b){
		engine.set_beta(b);
	}
	
	public void set_speed(double s){
		engine.set_speed(s);
	}
	
	public void add_half_tone(double f){
		engine.add_half_tone(f);
	}
	
	public void set_msd_threshould(int i, double f){
		engine.set_msd_threshold(i, f);
	}
	
	public void set_gv_weight(int i, double f){
		engine.set_gv_weight(i, f);
	}
	
	public void set_volume(double f){
		engine.set_volume(f);
	}
	
	public void set_audio_buff_size(int i){
		engine.set_audio_buff_size(i);
	}
	
	public int synthesis(String text, FileOutputStream wavf, FileOutputStream logf){
		if(availableSen() == false || availableEngine() == false)
			return -1;
		make_label(text);
		return synthesis(wavf, logf);
	}
	
	public int synthesis(String text){
		return synthesis(text, null, null);
	}
	
	public int synthesis(FileOutputStream wavf, FileOutputStream logf){
		//long t1, t2;
		if(jpcommon == null)
			return -1;
		int result = 0;
		
		//t1 = System.nanoTime();	
		if(jpcommon.get_label_size() > 2){
			System.nanoTime();
			if(engine.synthesize_from_strings(jpcommon.get_label_feature()) == true){
				//t2 = System.nanoTime();
				//System.err.printf("++synthesize time[us]:%f\n", (t2-t1)/1e+03);
				result = 1;
			}
			if(wavf != null)
				save_riff(wavf);
			if(logf != null){
				save_information(logf);
			}
		}
		
		return result;
	}

	public Boolean synthesis_from_fn(String labelFilename){
		return engine.synthesize_from_fn(labelFilename);
	}
	
	public void close_audio(){
		engine.close_audio();
	}
	
	public void save_information(FileOutputStream logf){
		if(njd == null)
			return;
		
		njd.fprint(new PrintStream(logf));
		engine.save_information(logf);
	}
	
	public void save_label(FileOutputStream fos){
		save_label(fos, false);
	}
	
	public void save_label(FileOutputStream fos, Boolean withTime){
		if(withTime)
			if(availableEngine())
				engine.save_label(fos);
		else
			if(jpcommon != null)
				jpcommon.label.save_label(fos);
	}
	
	public void save_riff(FileOutputStream wavf){
		if(availableEngine())
		engine.save_riff(wavf);
	}
	
	public void make_label(String text){
//		long t1, t2, t3;

//		t1 = System.nanoTime();
		Token[] token = analysis_text(text);
//		t2 = System.nanoTime();
//		System.err.printf("++morpheme_analysis time[us]:%f\n", (t2-t1)/1e+03);

		if(token == null)
			return;
		
		String[] feature = tokenToString(token);
		
		njd = new Gyutan_NJD(feature);
		//njd.print();
		
		Gyutan_NJDPronunciationRule.set_pronunciation(njd);
		//System.err.printf("== after set_pronunciation ==\n");
		//njd.print();

		Gyutan_NJDDigitRule.set_digit(njd);
		//System.err.printf("== after set_digit ==\n");
		//njd.print();

		Gyutan_NJDAccentPhraseRule.set_accent_phrase(njd);
		//System.err.printf("== after set_accent ==\n");
		//njd.print();

		Gyutan_NJDAccentTypeRule.set_accent_type(njd);
		//System.err.printf("== after set_accent_type ==\n");
		//njd.print();

		Gyutan_NJDUnvoicedVowelRule.set_unvoiced_vowel(njd);
		//System.err.printf("== after set_unvoiced_vowel ==\n");
		//njd.print();

		//Gyutan_NJDLongVowelRule.set_long_vowel(njd);
		//System.err.printf("== after set_long_vowel==\n");
		//njd.print();

		jpcommon = new Gyutan_JPCommon(njd);
		jpcommon.make_label();	
		
		//jpcommon.fprint(System.err);
//		t3 = System.nanoTime();
//		System.err.printf("++accent_analysis time[us]:%f\n", (t3-t2)/1e+03);
	}
	
	public String hankakuToZenkaku(String text){
		StringBuilder sb = new StringBuilder(text);
		for(int i=0; i < sb.length();i++){
			char c = sb.charAt(i);
			if((0x30 <= c && c <= 0x39) ||
			   (0x41 <= c && c <= 0x5A) ||
			   (0x61 <= c && c <= 0x7A)){
				sb.setCharAt(i, (char)(c+0xFEE0));
			}
		}
		
		return sb.toString();
	}
	
	public static String replace(String text){
		for(int i=0;i < REPLACE.length;i+=2){
			text = text.replaceAll(REPLACE[i], REPLACE[i+1]);
		}
		
		return text;
	}
}