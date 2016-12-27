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

public class Gyutan_NJDLongVowelRule {
	static final String long_vowel_table[] = {
		   "エイ", "エー",
		   "ケイ", "ケー",
		   "セイ", "セー",
		   "テイ", "テー",
		   "ネイ", "ネー",
		   "ヘイ", "ヘー",
		   "メイ", "メー",
		   "レイ", "レー",
		   "ゲイ", "ゲー",
		   "ゼイ", "ゼー",
		   "デイ", "デー",
		   "ベイ", "ベー",
		   "ペイ", "ペー",
		   "ヱイ", "ヱー"
		};

	static int strtopcmp(String str, String pattern){
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
	
	static void set_long_vowel(Gyutan_NJD njd){
		for(Gyutan_NJDNode node = njd.head; node != null; node = node.next){
			String str = node.get_pronunciation();
			int len = 0;
			if(str == null)
				len = 0;
			else
				len = str.length();
			
			StringBuilder buff = new StringBuilder();
			int bbyte = -1;
			for(int i=0;i < len;i+=bbyte){
				bbyte = -1;
				
				for(int j=0;j < long_vowel_table.length;j+=2){
					bbyte = strtopcmp(str.substring(i), long_vowel_table[j]);
					if(bbyte > 0){
						buff.append(long_vowel_table[j + 1]);
						break;
					}
				}
				if(bbyte < 0){
					bbyte = 1;
					buff.append(str.charAt(i));
				}
			}
			
			node.set_pronunciation(buff.toString());
		}
	}
}
