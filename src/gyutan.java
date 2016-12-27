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

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.Gyutan.*;

public class gyutan {
	final static String progname = "gyutan";
	final static String version  = "version: 20151228";
	
	public static void usage(){
		System.err.printf("%s - The Japanese TTS system %s\n", progname, version);
		   System.err.printf("\n");
		   System.err.printf("  usage:\n");
		   System.err.printf("       %s [ options ] [ infile ] \n", progname);
		   System.err.printf(
		           "  options:                                                                   [  def][ min-- max]\n");
		   System.err.printf(
		           "    -x  dir        : dictionary directory                                    [  N/A]\n");
		   System.err.printf(
		           "    -m  htsvoice   : HTS voice files                                         [  N/A]\n");
		   System.err.printf(
		           "    -ow s          : filename of output wav audio (generated speech)         [  N/A]\n");
		   System.err.printf(
		           "    -ot s          : filename of output trace information                    [  N/A]\n");
		   System.err.printf(
				   "    -ol s		   : filename of output label without time                   [  N/A]\n");
		   System.err.printf(
				   "    -of            : filename of output label with time                      [  N/A]\n");
		   System.err.printf(
		           "    -s  i          : sampling frequency                                      [ auto][   1--    ]\n");
		   System.err.printf(
		           "    -p  i          : frame period (point)                                    [ auto][   1--    ]\n");
		   System.err.printf(
		           "    -a  f          : all-pass constant                                       [ auto][ 0.0-- 1.0]\n");
		   System.err.printf(
		           "    -b  f          : postfiltering coefficient                               [  0.0][ 0.0-- 1.0]\n");
		   System.err.printf(
		           "    -r  f          : speech speed rate                                       [  1.0][ 0.0--    ]\n");
		   System.err.printf(
		           "    -fm f          : additional half-tone                                    [  0.0][    --    ]\n");
		   System.err.printf(
		           "    -u  f          : voiced/unvoiced threshold                               [  0.5][ 0.0-- 1.0]\n");
		   System.err.printf(
		           "    -jm f          : weight of GV for spectrum                               [  1.0][ 0.0--    ]\n");
		   System.err.printf(
			        "    -jf f          : weight of GV for log F0                                 [  1.0][ 0.0--    ]\n");
		   System.err.printf(
		           "    -g  f          : volume (dB)                                             [  0.0][    --    ]\n");
		   System.err.printf(
		           "    -z  i          : audio buffer size (if i==0, turn off)                   [    0][   0--    ]\n");
		   System.err.printf("  infile:\n");
		   System.err.printf(
		           "    text file                                                                [stdin]\n");
		   System.err.printf("\n");

		   System.exit(0);


	}
	public static void main(String[] args){
		//long t1, t2;
		//t1 = System.nanoTime();
		
		String dn_dict  = null;
		String fn_voice = null;
		Boolean use_audio = false;
		
		if(args.length == 0)
			usage();
		
		for(int i=0;i < args.length;i++){
			if(args[i].equals("-x"))
				dn_dict = args[++i];
			else if(args[i].equals("-h"))
				usage();
		}
		if(dn_dict == null){
			System.err.printf("Error: Dictionary must be specified.\n");
			System.exit(1);
		}
		
		for(int i=0;i < args.length;i++){
			if(args[i].equals("-m"))
				fn_voice = args[++i];
		}
		if(fn_voice == null){
			System.err.print("Error: HTS voice must be specified.\n");
			System.exit(1);
		}
		
		
		Gyutan gyutan = new Gyutan();
		Boolean flag = gyutan.initialize(dn_dict, fn_voice);
		if(flag == false){
			System.err.println("Error: initialize failed");
			usage();
		}
		
		String txtfn              = null;
		BufferedInputStream txtfp = new BufferedInputStream(System.in);
		
		FileOutputStream wavfp    = null;
		FileOutputStream logfp    = null;
		FileOutputStream labelfp  = null;
		FileOutputStream flabelfp = null;
		try {	
			int cnt = 0;
			while(cnt < args.length){
				if(args[cnt].equals("-ow"))
					wavfp = new FileOutputStream(args[++cnt]);
				else if(args[cnt].equals("-ot"))
					logfp = new FileOutputStream(args[++cnt]);
				else if(args[cnt].equals("-ol"))
					labelfp = new FileOutputStream(args[++cnt]);
				else if(args[cnt].equals("-of"))
					flabelfp = new FileOutputStream(args[++cnt]);
				else if(args[cnt].equals("-h"))
					usage();
				else if(args[cnt].equals("-s"))
					gyutan.set_sampling_frequency(Integer.parseInt(args[++cnt]));
				else if(args[cnt].equals("-p"))
					gyutan.set_fperiod(Integer.parseInt(args[++cnt]));
				else if(args[cnt].equals("-a"))
					gyutan.set_alpha(Double.parseDouble(args[++cnt]));
				else if(args[cnt].equals("-b"))
					gyutan.set_beta(Double.parseDouble(args[++cnt]));
				else if(args[cnt].equals("-r"))
					gyutan.set_speed(Double.parseDouble(args[++cnt]));
				else if(args[cnt].equals("-fm"))
					gyutan.add_half_tone(Double.parseDouble(args[++cnt]));
				else if(args[cnt].equals("-u"))
					gyutan.set_msd_threshould(1, Double.parseDouble(args[++cnt]));
				else if(args[cnt].equals("-jm"))
					gyutan.set_gv_weight(0,  Double.parseDouble(args[++cnt]));
				else if(args[cnt].equals("-jf") || args[cnt].equals("-jp"))
					gyutan.set_gv_weight(1, Double.parseDouble(args[++cnt]));
				else if(args[cnt].equals("-g"))
					gyutan.set_volume(Double.parseDouble(args[++cnt]));
				else if(args[cnt].equals("-z")){
					int bufsize = Integer.parseInt(args[++cnt]);
					gyutan.set_audio_buff_size(bufsize);
					if(bufsize > 0)
						use_audio = true;
				}
				else if(args[cnt].equals("-x") || args[cnt].equals("-m"))
					cnt++;
				else if(args[cnt].startsWith("-")){
					System.err.printf("Error invalid option:%s\n", args[cnt]);
					System.exit(1);
				}
				else{
					txtfn = args[cnt];
					txtfp = new BufferedInputStream(new FileInputStream(txtfn));
				}
				cnt++;
			}
		
			byte[] buf = new byte[txtfp.available()];
			txtfp.read(buf);
			String text = new String(buf);
			gyutan.synthesis(text, wavfp, logfp);

			if(labelfp != null)
				gyutan.save_label(labelfp);
			if(flabelfp != null)
				gyutan.save_label(flabelfp, true);
		
			if(txtfn != null)
				txtfp.close();
			if(wavfp != null)
				wavfp.close();
			if(logfp != null)
				logfp.close();
			if(labelfp != null)
				labelfp.close();
			if(flabelfp != null)
				flabelfp.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(use_audio)
			gyutan.close_audio();
		
		//t2 = System.nanoTime();
		//System.err.printf("++total time[us]:%f\n", (t2-t1)/1e+03);
	}
}
