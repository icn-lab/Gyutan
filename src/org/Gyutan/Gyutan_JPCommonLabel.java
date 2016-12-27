// This software is a language translation version of "Open JTalk" developed by HTS Working Group.
// 
// Copyright (c) 2015-2016 Intelligent Communication Network (Ito-Nose) Laboratory
// Tohoku University
// Copyright (c) 2008-2016  Nagoya Institute of Technology
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

import java.io.FileOutputStream;
import java.io.PrintStream;

public class Gyutan_JPCommonLabel {
	static final int MAX_S = 19;
	static final int MAX_M = 49;
	static final int MAX_L = 99;
	static final int MAX_LL = 199;
	
	static final String MORA_UNVOICE = "’";
	static final String MORA_LONG_VOWEL = "ー";
	static final String MORA_SHORT_PAUSE = "、";
	static final String MORA_QUESTION = "？";
	static final String PHONEME_SHORT_PAUSE = "pau";
	static final String PHONEME_SILENT = "sil";
	static final String PHONEME_UNKNOWN = "xx";
	static final String FLAG_QUESTION = "1";
	
	static final String mora_list[] = {
		   "ヴョ", "by", "o",
		   "ヴュ", "by", "u",
		   "ヴャ", "by", "a",
		   "ヴォ", "v", "o",
		   "ヴェ", "v", "e",
		   "ヴィ", "v", "i",
		   "ヴァ", "v", "a",
		   "ヴ", "v", "u",
		   "ン", "N", null,
		   "ヲ", "o", null,
		   "ヱ", "e", null,
		   "ヰ", "i", null,
		   "ワ", "w", "a",
		   "ヮ", "w", "a",
		   "ロ", "r", "o",
		   "レ", "r", "e",
		   "ル", "r", "u",
		   "リョ", "ry", "o",
		   "リュ", "ry", "u",
		   "リャ", "ry", "a",
		   "リェ", "ry", "e",
		   "リ", "r", "i",
		   "ラ", "r", "a",
		   "ヨ", "y", "o",
		   "ョ", "y", "o",
		   "ユ", "y", "u",
		   "ュ", "y", "u",
		   "ヤ", "y", "a",
		   "ャ", "y", "a",
		   "モ", "m", "o",
		   "メ", "m", "e",
		   "ム", "m", "u",
		   "ミョ", "my", "o",
		   "ミュ", "my", "u",
		   "ミャ", "my", "a",
		   "ミェ", "my", "e",
		   "ミ", "m", "i",
		   "マ", "m", "a",
		   "ポ", "p", "o",
		   "ボ", "b", "o",
		   "ホ", "h", "o",
		   "ペ", "p", "e",
		   "ベ", "b", "e",
		   "ヘ", "h", "e",
		   "プ", "p", "u",
		   "ブ", "b", "u",
		   "フォ", "f", "o",
		   "フェ", "f", "e",
		   "フィ", "f", "i",
		   "ファ", "f", "a",
		   "フ", "f", "u",
		   "ピョ", "py", "o",
		   "ピュ", "py", "u",
		   "ピャ", "py", "a",
		   "ピェ", "py", "e",
		   "ピ", "p", "i",
		   "ビョ", "by", "o",
		   "ビュ", "by", "u",
		   "ビャ", "by", "a",
		   "ビェ", "by", "e",
		   "ビ", "b", "i",
		   "ヒョ", "hy", "o",
		   "ヒュ", "hy", "u",
		   "ヒャ", "hy", "a",
		   "ヒェ", "hy", "e",
		   "ヒ", "h", "i",
		   "パ", "p", "a",
		   "バ", "b", "a",
		   "ハ", "h", "a",
		   "ノ", "n", "o",
		   "ネ", "n", "e",
		   "ヌ", "n", "u",
		   "ニョ", "ny", "o",
		   "ニュ", "ny", "u",
		   "ニャ", "ny", "a",
		   "ニェ", "ny", "e",
		   "ニ", "n", "i",
		   "ナ", "n", "a",
		   "ドゥ", "d", "u",
		   "ド", "d", "o",
		   "トゥ", "t", "u",
		   "ト", "t", "o",
		   "デョ", "dy", "o",
		   "デュ", "dy", "u",
		   "デャ", "dy", "a",
		   "デェ", "dy", "e",
		   "ディ", "d", "i",
		   "デ", "d", "e",
		   "テョ", "ty", "o",
		   "テュ", "ty", "u",
		   "テャ", "ty", "a",
		   "ティ", "t", "i",
		   "テ", "t", "e",
		   "ヅ", "z", "u",
		   "ツォ", "ts", "o",
		   "ツェ", "ts", "e",
		   "ツィ", "ts", "i",
		   "ツァ", "ts", "a",
		   "ツ", "ts", "u",
		   "ッ", "cl", null,
		   "ヂ", "j", "i",
		   "チョ", "ch", "o",
		   "チュ", "ch", "u",
		   "チャ", "ch", "a",
		   "チェ", "ch", "e",
		   "チ", "ch", "i",
		   "ダ", "d", "a",
		   "タ", "t", "a",
		   "ゾ", "z", "o",
		   "ソ", "s", "o",
		   "ゼ", "z", "e",
		   "セ", "s", "e",
		   "ズィ", "z", "i",
		   "ズ", "z", "u",
		   "スィ", "s", "i",
		   "ス", "s", "u",
		   "ジョ", "j", "o",
		   "ジュ", "j", "u",
		   "ジャ", "j", "a",
		   "ジェ", "j", "e",
		   "ジ", "j", "i",
		   "ショ", "sh", "o",
		   "シュ", "sh", "u",
		   "シャ", "sh", "a",
		   "シェ", "sh", "e",
		   "シ", "sh", "i",
		   "ザ", "z", "a",
		   "サ", "s", "a",
		   "ゴ", "g", "o",
		   "コ", "k", "o",
		   "ゲ", "g", "e",
		   "ケ", "k", "e",
		   "ヶ", "k", "e",
		   "グヮ", "gw", "a",
		   "グ", "g", "u",
		   "クヮ", "kw", "a",
		   "ク", "k", "u",
		   "ギョ", "gy", "o",
		   "ギュ", "gy", "u",
		   "ギャ", "gy", "a",
		   "ギェ", "gy", "e",
		   "ギ", "g", "i",
		   "キョ", "ky", "o",
		   "キュ", "ky", "u",
		   "キャ", "ky", "a",
		   "キェ", "ky", "e",
		   "キ", "k", "i",
		   "ガ", "g", "a",
		   "カ", "k", "a",
		   "オ", "o", null,
		   "ォ", "o", null,
		   "エ", "e", null,
		   "ェ", "e", null,
		   "ウォ", "w", "o",
		   "ウェ", "w", "e",
		   "ウィ", "w", "i",
		   "ウ", "u", null,
		   "ゥ", "u", null,
		   "イェ", "y", "e",
		   "イ", "i", null,
		   "ィ", "i", null,
		   "ア", "a", null,
		   "ァ", "a", null
	};
	
	int size;
	String[] feature;
	StringBuilder lineBuffer;
	Gyutan_JPCommonLabelBreathGroup breath_head;
	Gyutan_JPCommonLabelBreathGroup breath_tail;
	Gyutan_JPCommonLabelAccentPhrase accent_head;
	Gyutan_JPCommonLabelAccentPhrase accent_tail;
	Gyutan_JPCommonLabelWord word_head;
	Gyutan_JPCommonLabelWord word_tail;
	Gyutan_JPCommonLabelMora mora_head;
	Gyutan_JPCommonLabelMora mora_tail;
	Gyutan_JPCommonLabelPhoneme phoneme_head;
	Gyutan_JPCommonLabelPhoneme phoneme_tail;
	Boolean short_pause_flag;
	
	 Gyutan_JPCommonLabel(){
		 initialize();
	 }
	 
	 void initialize(){
		 short_pause_flag = false;
		 breath_head = null;
		 breath_tail = null;
		 accent_head = null;
		 accent_tail = null;
		 word_head = null;
		 word_tail = null;
		 mora_head = null;
		 mora_tail = null;
		 phoneme_head = null;
		 phoneme_tail = null;
	 }
	 
	 void insert_pause(){
		 if(short_pause_flag){
			 if(phoneme_tail != null){
				 if(phoneme_tail.phoneme.equals(PHONEME_SHORT_PAUSE) == true){
					 System.err.printf("WARNING: JPCommonLabel.insert_word(): short pause should not be chained\n");
					 return;
				 }
				 phoneme_tail.next = new Gyutan_JPCommonLabelPhoneme();
				 phoneme_tail.next.initialize(PHONEME_SHORT_PAUSE, phoneme_tail, null, null);
				 phoneme_tail = phoneme_tail.next;
			 }
			 else{
				 System.err.printf("WARNING: JPCommonLabel.insert_word(): First mora should not be short pause.\n");
			 }
			 short_pause_flag = false;
		 }
	 }

	 int limit(int in, int min, int max){
		 if(in <= min)
			 return min;
		 if(in >= max)
			 return max;
		 return in;
	 }
	 
	 int strtopcmp(String str, String pattern){
		 char[] strat = str.toCharArray();
		 char[] patat = pattern.toCharArray();
		 
		 for(int i=0;;i++){
			 if(i == pattern.length())
				 return i;
			 if(i == str.length())
				 return -1;
			 //if(str.charAt(i) != pattern.charAt(i))
			 if(strat[i] != patat[i])
				 return -1;
		 }
	 }
	 
	 void set_emotion_flag(){
		 if(phoneme_tail != null){
			 if(phoneme_tail.phoneme.equals(PHONEME_SHORT_PAUSE) == true){
				 if(phoneme_tail.prev.up.up.up.emotion == null)
					 phoneme_tail.prev.up.up.up.emotion = FLAG_QUESTION;
			 }
			 else{
				 if(phoneme_tail.up.up.up.emotion == null)
					 phoneme_tail.up.up.up.emotion = FLAG_QUESTION;
			 }
		 }
		 else{
			 System.err.printf("WARNING* JPCommonLabel.push_word(): First mora should not be question flag.\n");
		 }
		 
		 short_pause_flag = true;
	 }
	 
	 int analysis_pronunciation(String pron, String pos, String ctype, String cform, int accent, int chain_flag, int is_first_word){
		 if(pron == null || pron.length() == 0)
			 return is_first_word;
		 
		 while(0 < pron.length()){
			//System.err.printf("### check pron:%s ###\n", pron);

			 int find = strtopcmp(pron, MORA_LONG_VOWEL);
			 if(find != -1){
				 if(phoneme_tail != null && short_pause_flag == false){
					 //System.err.printf("++ proc1 ++\n");
					 insert_pause();
					 phoneme_tail.next = new Gyutan_JPCommonLabelPhoneme();
					 mora_tail.next    = new Gyutan_JPCommonLabelMora();
					 
					 phoneme_tail.next.initialize(phoneme_tail.phoneme, phoneme_tail, null, mora_tail.next);
					 mora_tail.next.initialize(MORA_LONG_VOWEL, phoneme_tail.next, phoneme_tail.next, mora_tail, null, mora_tail.up);
					 
					 phoneme_tail   = phoneme_tail.next;
					 mora_tail      = mora_tail.next;
					 word_tail.tail = mora_tail;
				 }
				 else{
					 System.err.printf("WARNING: JPCommonLabel.push_word(): First mora should not be long vowel symbol.\n");
				 }
				 pron = pron.substring(find);
			 }
			 else{
				 find = strtopcmp(pron, MORA_UNVOICE);
				 if(find != -1){
					 //System.err.printf("++ proc2 ++\n");
					 
					 if(phoneme_tail != null && is_first_word != 1)
						 phoneme_tail.convert_unvoice();
					 else
						 System.err.printf("WARNING: JPCommonLabel.push_word(): First mora should not be unvoice flag.\n");
					 pron = pron.substring(find);
				 }
				 else{
					 //System.err.printf("++ proc3 ++\n");
					 int i = 0;
					 for(i=0;i < mora_list.length;i+=3){
						 find = strtopcmp(pron, mora_list[i]);
						 if(find != -1)
							 break;
					 }
				
					 if(find != -1){
						 if(phoneme_tail == null){
							 //System.err.printf("++ proc4 ++");
							 insert_pause();
							 phoneme_tail = new Gyutan_JPCommonLabelPhoneme();
							 mora_tail    = new Gyutan_JPCommonLabelMora();
							 word_tail    = new Gyutan_JPCommonLabelWord();
							 phoneme_tail.initialize(mora_list[i+1], null, null, mora_tail);
							 mora_tail.initialize(mora_list[i], phoneme_tail, phoneme_tail, null, null, word_tail);
							 word_tail.initialize(pron, pos, ctype, cform, mora_tail, mora_tail, null, null);
							 phoneme_head = phoneme_tail;
							 mora_head    = mora_tail;
							 word_head    = word_tail;
							 is_first_word = 0;
						 }
						 else {
							 if(is_first_word == 1){
								 //System.err.printf("++ proc5 ++\n");
								 insert_pause();
								 phoneme_tail.next = new Gyutan_JPCommonLabelPhoneme();
								 mora_tail.next    = new Gyutan_JPCommonLabelMora();
								 word_tail.next    = new Gyutan_JPCommonLabelWord();
								 phoneme_tail.next.initialize(mora_list[i+1], phoneme_tail, null, mora_tail.next);
								 mora_tail.next.initialize(mora_list[i], phoneme_tail.next, phoneme_tail.next, mora_tail, null, word_tail.next);
								 word_tail.next.initialize(pron, pos, ctype, cform, mora_tail.next, mora_tail.next, word_tail, null);
								 phoneme_tail = phoneme_tail.next;
								 mora_tail = mora_tail.next;
								 word_tail = word_tail.next;
								 is_first_word = 0;
							 }
							 else{
								 //System.err.printf("++ ptoc6 ++\n");
								 insert_pause();
								 phoneme_tail.next = new Gyutan_JPCommonLabelPhoneme();
								 mora_tail.next    = new Gyutan_JPCommonLabelMora();
								 phoneme_tail.next.initialize(mora_list[i+1], phoneme_tail, null, mora_tail.next);
								 mora_tail.next.initialize(mora_list[i], phoneme_tail.next, phoneme_tail.next, mora_tail, null, mora_tail.up);
								 phoneme_tail   = phoneme_tail.next;
							     mora_tail      = mora_tail.next;
							     word_tail.tail = mora_tail;
						      }
						 }
							 if(mora_list[i+2] != null){
								 //System.err.printf("++ proc7 ++\n");
								 insert_pause();
								 phoneme_tail.next = new Gyutan_JPCommonLabelPhoneme();
								 phoneme_tail.next.initialize(mora_list[i+2], phoneme_tail, null, mora_tail);
								 phoneme_tail   = phoneme_tail.next;
								 mora_tail.tail = phoneme_tail;
							 }
							 pron = pron.substring(find);
						 }
						 else{
							 System.err.printf("WARNING: JPCommonLabel.push_word(): %s is wrong mora list.\n", pron);
							 break;
						 }
				 }
			 }
		 }
		 
		 return is_first_word;
	 }

   	 void make_accent_and_phrase(int accent, int chain_flag){
   		 if(word_head == word_tail){
   			 accent_tail = new Gyutan_JPCommonLabelAccentPhrase();
   			 breath_tail = new Gyutan_JPCommonLabelBreathGroup();
   			 word_tail.up = accent_tail;
   			 accent_tail.initialize(accent, null, word_tail, word_tail, null, null, breath_tail);
   			 breath_tail.initialize(accent_tail, accent_tail, null, null);
   			 accent_head = accent_tail;
   			 breath_head = breath_tail;
   		 }
   		 else
   			 if(chain_flag == 1){
   				 word_tail.up     = accent_tail;
   				 accent_tail.tail = word_tail;
   			 }
   			 else{
   				 if(PHONEME_SHORT_PAUSE.equals(word_tail.prev.tail.tail.next.phoneme) != true){
   					 accent_tail.next = new Gyutan_JPCommonLabelAccentPhrase();
   					 word_tail.up     = accent_tail.next;
   					 accent_tail.next.initialize(accent, null, word_tail, word_tail, accent_tail, null, breath_tail);
   					 breath_tail.tail = accent_tail.next;
   					 accent_tail      = accent_tail.next;
   				 }
   				 else{
   					 accent_tail.next = new Gyutan_JPCommonLabelAccentPhrase();
   					 breath_tail.next = new Gyutan_JPCommonLabelBreathGroup();
   					 word_tail.up = accent_tail.next;
   					 accent_tail.next.initialize(accent, null, word_tail, word_tail, accent_tail, null, breath_tail.next);
   					 breath_tail.next.initialize(accent_tail.next, accent_tail.next, breath_tail, null);
   					 accent_tail = accent_tail.next;
   					 breath_tail = breath_tail.next;
   				 }
   			 }
   	 }
   	 
	 void push_word(String pron, String pos, String ctype, String cform, int accent, int chain_flag){
		 int is_first_word = 1;

		 if(MORA_SHORT_PAUSE.equals(pron) == true){
			 short_pause_flag = true;
			 return;
		 }
		 
		 if(MORA_QUESTION.equals(pron) == true){
			 set_emotion_flag();
			 return;
		 }
		 is_first_word = analysis_pronunciation(pron, pos, ctype, cform, accent, chain_flag, is_first_word);
		 if(is_first_word == 1)
			 return;
		 if(phoneme_tail == null)
			 return;
		 if(phoneme_tail.phoneme.equals(PHONEME_SHORT_PAUSE) == true)
			 return;
		 
		 make_accent_and_phrase(accent, chain_flag);
	 }
	 
	 void make_phoneme(int i, String[] phoneme_list){
		 lineBuffer = new StringBuilder( String.format("%s^%s-%s+%s=%s", phoneme_list[i], phoneme_list[i+1],
				 										phoneme_list[i+2], phoneme_list[i+3], phoneme_list[i+4]) );
	 }
	
	 void make_A(int i, Boolean short_pause_flag, Gyutan_JPCommonLabelPhoneme p, String [] phoneme_list){
		 String buff;
		 
		 if(i == 0 || i == size-1 || short_pause_flag == true)
			 buff = "/A:xx+xx+xx";
		 else{
			 int tmp1 = p.up.index_mora_in_accent_phrase();
			 int tmp2 = p.up.up.up.accent == 0 ? p.up.count_mora_in_accent_phrase() : p.up.up.up.accent;
			 buff = String.format("/A:%d+%d+%d", limit(tmp1-tmp2, -MAX_M, MAX_M), limit(tmp1, 1, MAX_M), 
					 				limit(p.up.count_mora_in_accent_phrase()-tmp1+1, 1, MAX_M) );
			 
		 }
		 
		 lineBuffer.append(buff);
	 }
	 
	 void make_B(int i, Boolean short_pause_flag, Gyutan_JPCommonLabelPhoneme p){
		 String buff;
		 Gyutan_JPCommonLabelWord w = null;
		
		 if(short_pause_flag)
			 w = p.prev.up.up;
		 else if(p.up.up.prev == null)
			 w = null;
		 else if(i == size-1)
			 w = p.up.up;
		 else
			 w = p.up.up.prev;
		 
		 if(w == null)
			 buff = "/B:xx-xx_xx";
		 else
			 buff = String.format("/B:%s-%s_%s", w.pos, w.ctype, w.cform);
		 
		 lineBuffer.append(buff);
	 }
	 
	 void make_C(int i, Boolean short_pause_flag, Gyutan_JPCommonLabelPhoneme p){
		 String buff;

		 if(i == 0 || i == size - 1 || short_pause_flag == true)
			 buff = "/C:xx_xx+xx";
		 else
			 buff = String.format("/C:%s_%s+%s",  p.up.up.pos, p.up.up.ctype, p.up.up.cform);
		 
		 lineBuffer.append(buff);
	 }
	 
	 void make_D(int i, Boolean short_pause_flag, Gyutan_JPCommonLabelPhoneme p){
		 String buff;
		 Gyutan_JPCommonLabelWord w = null;
		 
		 if(short_pause_flag == true)
			 w = p.next.up.up;
		 else if(p.up.up.next == null)
			 w = null;
		 else if(i==0)
			 w = p.up.up;
		 else
			 w = p.up.up.next;
		 
		 if(w == null)
			 buff = "/D:xx+xx_xx";
		 else
			 buff = String.format("/D:%s+%s_%s", w.pos, w.ctype, w.cform);
	
		 lineBuffer.append(buff);
	 }
	 
	 void make_E(int i, Boolean short_pause_flag, Gyutan_JPCommonLabelPhoneme p){
		 String buff;
		 Gyutan_JPCommonLabelAccentPhrase a = null;
		 
		 if(short_pause_flag == true)
			 a = p.prev.up.up.up;
		 else if(i == size -1)
			 a = p.up.up.up;
		 else
			 a = p.up.up.up.prev;
		 
		 if(a == null)
			 buff = "/E:xx_xx!xx_xx";
		 else
			 buff = String.format("/E:%d_%d!%s_xx", limit(a.head.head.count_mora_in_accent_phrase(), 1, MAX_M),
					 								limit(a.accent == 0?a.head.head.count_mora_in_accent_phrase():a.accent, 1, MAX_M),
					 								a.emotion == null ? "0" : a.emotion);
		 lineBuffer.append(buff);
	
		 if(i == 0 || i == size-1 || short_pause_flag == true || a == null)
			 lineBuffer.append("-xx");
		 else
			 lineBuffer.append( String.format("-%d",  a.tail.tail.tail.next.phoneme.equals(PHONEME_SHORT_PAUSE) == true ? 0:1) );
	 }
	 
	 void make_F(int i, Boolean short_pause_flag, Gyutan_JPCommonLabelPhoneme p){
		 String buff;
		 Gyutan_JPCommonLabelAccentPhrase a = null;
		 
		 if(i == 0 || i == size -1 || short_pause_flag == true)
			 a = null;
		 else
			 a = p.up.up.up;
		 
		 if(a == null)
			 buff = "/F:xx_xx#xx_xx@xx_xx|xx_xx";
		 else{
			 int tmp1 = a.index_accent_phrase_in_breath_group();
			 int tmp2 = a.head.head.index_mora_in_breath_group();
			 buff = String.format("/F:%d_%d#%s_xx@%d_%d|%d_%d", 
					 limit(a.head.head.count_mora_in_accent_phrase(), 1, MAX_M),
					 limit(a.accent == 0 ? a.head.head.count_mora_in_accent_phrase() : a.accent, 1, MAX_M),
					 (a.emotion == null) ? "0":a.emotion,
					 limit(tmp1, 1, MAX_M),
					 limit(a.count_accent_phrase_in_breath_group()-tmp1 + 1, 1, MAX_M),
					 limit(tmp2, 1, MAX_L),
					 limit(a.head.head.count_mora_in_breath_group()-tmp2 + 1, 1, MAX_L) 
					 );
		 }
		 
		 lineBuffer.append(buff);
	 }
	 
	 void make_G(int i, Boolean short_pause_flag, Gyutan_JPCommonLabelPhoneme p){
		 String buff;
		 Gyutan_JPCommonLabelAccentPhrase a = null;
				 
		 if(short_pause_flag == true)
			 a = p.next.up.up.up;
		 else if(i == 0)
			 a = p.up.up.up;
		 else
			 a = p.up.up.up.next;
		 
		 if(a == null)
			 buff = "/G:xx_xx%xx_xx";
		 else
			 buff = String.format("/G:%d_%d%%%s_xx",
					 limit(a.head.head.count_mora_in_accent_phrase(), 1, MAX_M),
					 limit(a.accent == 0 ? a.head.head.count_mora_in_accent_phrase() : a.accent, 1, MAX_M),
					 a.emotion == null ? "0": a.emotion
					);
		 lineBuffer.append(buff);
				 
		 if (i == 0 || i == size - 1 || short_pause_flag == true || a == null)
			 buff = "_xx";
		 else
			 buff = String.format("_%d",
					 PHONEME_SHORT_PAUSE.equals(a.head.head.head.prev.phoneme) == true ? 0:1);

		 lineBuffer.append(buff);
	 }
	 
	 void make_H(int i, Boolean short_pause_flag, Gyutan_JPCommonLabelPhoneme p){
		 String buff;
		 Gyutan_JPCommonLabelBreathGroup b = null;
		 
		 if(short_pause_flag == true)
			 b = p.prev.up.up.up.up;
		 else if(i == size - 1)
			 b = p.up.up.up.up;
		 else
			 b = p.up.up.up.up.prev;
		 
		 if(b == null)
			 buff = "/H:xx_xx";
		 else
			 buff = String.format("/H:%d_%d",
					 limit(b.head.count_accent_phrase_in_breath_group(), 1, MAX_M),
					 limit(b.head.head.head.count_mora_in_breath_group(), 1, MAX_L)
					 );

		 lineBuffer.append(buff);
	 }
	 
	 void make_I(int i, Boolean short_pause_flag, Gyutan_JPCommonLabelPhoneme p){
		 String buff;
		 Gyutan_JPCommonLabelBreathGroup b = null;
		 
		 if(i == 0 || i == size - 1 || short_pause_flag == true)
			 b = null;
		 else 
			 b = p.up.up.up.up;
		 
		 if(b == null)
			 buff = "/I:xx-xx@xx+xx&xx-xx|xx+xx";
		 else{
			 int tmp1 = b.index_breath_group_in_utterance();
			 int tmp2 = b.head.index_accent_phrase_in_utterance();
			 int tmp3 = b.head.head.head.index_mora_in_utterance();

			 buff = String.format("/I:%d-%d@%d+%d&%d-%d|%d+%d",
					 limit(b.head.count_accent_phrase_in_breath_group(), 1, MAX_M),
					 limit(b.head.head.head.count_mora_in_breath_group(), 1, MAX_L),
					 limit(tmp1, 1, MAX_S),
					 limit(b.count_breath_group_in_utterance() - tmp1 + 1, 1, MAX_S),
					 limit(tmp2, 1, MAX_M),
					 limit(b.head.count_accent_phrase_in_utterance() - tmp2 + 1, 1, MAX_M),
					 limit(tmp3, 1, MAX_LL),
					 limit(b.head.head.head.count_mora_in_utterance() - tmp3 + 1, 1, MAX_LL)
					 );
		 }
		 
		 lineBuffer.append(buff);
	}
	 
	
	 void make_J(int i, Boolean short_pause_flag, Gyutan_JPCommonLabelPhoneme p){
		 String buff;
		 Gyutan_JPCommonLabelBreathGroup b = null;
		 
		 if(short_pause_flag == true)
			 b = p.next.up.up.up.up;
		 else if(i==0)
			 b = p.up.up.up.up;
		 else
			 b = p.up.up.up.up.next;
		 
		 if(b == null)
			 buff = "/J:xx_xx";
		 else
			 buff = String.format("/J:%d_%d",
					 limit(b.head.count_accent_phrase_in_breath_group(), 1, MAX_M),
					 limit(b.head.head.head.count_mora_in_breath_group(), 1, MAX_L)
					 );

		 lineBuffer.append(buff);
	 }
	 
	 void make_K(int i){
		 lineBuffer.append( String.format("/K:%d+%d-%d",
				 				limit(breath_head.count_breath_group_in_utterance(), 1, MAX_S),
				 				limit(accent_head.count_accent_phrase_in_utterance(), 1, MAX_M),
				 				limit(mora_head.count_mora_in_utterance(), 1, MAX_LL)
				 				) );
	 }
	 
	 void make(){
		 size = 0;
		 for(Gyutan_JPCommonLabelPhoneme p = phoneme_head;p != null; p = p.next)
			 size++;
		 if(size < 1){
			 System.err.printf("WARNING: JPCommonLabel.make(): No phoneme.\n");
			 return;
		 }
		 
		 size += 2;
		 feature = new String[size];
		 
		 String[] phoneme_list = new String[size+4];
		 phoneme_list[0] = PHONEME_UNKNOWN;
		 phoneme_list[1] = PHONEME_UNKNOWN;
		 phoneme_list[2] = PHONEME_SILENT;
		 phoneme_list[size+1] = PHONEME_SILENT;
		 phoneme_list[size+2] = PHONEME_UNKNOWN;
		 phoneme_list[size+3] = PHONEME_UNKNOWN;
		 
		 int i=3;
		 for(Gyutan_JPCommonLabelPhoneme p = phoneme_head; p != null; p = p.next)
			 phoneme_list[i++] = p.phoneme;
		 
		 
		 Gyutan_JPCommonLabelPhoneme p = phoneme_head;
		 Boolean short_pause_flag = false;
		 for(i=0;i < size;i++){
			 if(p.phoneme.equals(PHONEME_SHORT_PAUSE) == true)
				 short_pause_flag = true;
			 else
				 short_pause_flag = false;
			 
			 make_phoneme(i, phoneme_list);
			 make_A(i, short_pause_flag, p, phoneme_list);
			 make_B(i, short_pause_flag, p);
			 make_C(i, short_pause_flag, p);
			 make_D(i, short_pause_flag, p);
			 make_E(i, short_pause_flag, p);
			 make_F(i, short_pause_flag, p);
			 make_G(i, short_pause_flag, p);
			 make_H(i, short_pause_flag, p);
			 make_I(i, short_pause_flag, p);
			 make_J(i, short_pause_flag, p);
			 make_K(i);
			 feature[i] = lineBuffer.toString();
			 
			 if(0 < i && i < size - 2)
				 p = p.next;
		 }
	 }
	 
	 int get_size(){
		 return size;
	 }
	 
	 String[] get_feature(){
		 return feature;
	 }
	 
	 void print(){
		 fprint(System.out);
	 }
	 
	 void fprint(PrintStream ps){
		 int i = 0;
		 int j = 0;
		 
		 for(Gyutan_JPCommonLabelBreathGroup b = breath_head; b != null; b = b.next){
			 ps.printf("%d\n", j++);
			 for(Gyutan_JPCommonLabelAccentPhrase a = b.head;a != null; a = a.next){
				 ps.printf("   %d\n", i++);
				 for(Gyutan_JPCommonLabelWord w = a.head; w != null; w = w.next){
					 ps.printf("      %s %s %s %s\n",  w.pronunciation, w.pos, w.ctype, w.cform);
					 for(Gyutan_JPCommonLabelMora m = w.head; m != null; m = m.next){
						 ps.printf("         %s\n",  m.mora);
						 for(Gyutan_JPCommonLabelPhoneme p = m.head; p!=null; p = p.next){
							 ps.printf("            %s\n",  p.phoneme);
							 if(p == m.tail)
								 break;
						 }
						 if(m == w.tail)
							 break;
					 }
					 if(w == a.tail)
						 break;
				 }
				 if(a == b.tail)
					 break;
			 }
		 }
	 }
	 
	 void save_label(FileOutputStream fos){
		PrintStream ps = new PrintStream(fos);
		for(int i=0;i < feature.length;i++)
			ps.println(feature[i]);
		ps.close();
	 }
}
