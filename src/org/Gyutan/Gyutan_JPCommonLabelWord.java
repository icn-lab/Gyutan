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

public class Gyutan_JPCommonLabelWord {
	static final String pos_list[] = {
		   "その他", "xx",
		   "感動詞", "09",
		   "記号", "xx",
		   "形状詞", "19",
		   "形容詞", "01",
		   "助詞-その他", "23",
		   "助詞-格助詞", "13",
		   "助詞-係助詞", "24",
		   "助詞-終助詞", "14",
		   "助詞-接続助詞", "12",
		   "助詞-副助詞", "11",
		   "助動詞", "10",
		   "接続詞", "08",
		   "接頭辞", "16",
		   "接頭辞-形状詞的", "16",
		   "接頭辞-形容詞的", "16",
		   "接頭辞-動詞的", "16",
		   "接頭辞-名詞的", "16",
		   "接尾辞-形状詞的", "15",
		   "接尾辞-形容詞的", "15",
		   "接尾辞-動詞的", "15",
		   "接尾辞-名詞的", "15",
		   "代名詞", "04",
		   "動詞", "20",
		   "動詞-非自立", "17",
		   "副詞", "06",
		   "名詞-サ変接続", "03",
		   "名詞-固有名詞", "18",
		   "名詞-数詞", "05",
		   "名詞-非自立", "22",
		   "名詞-普通名詞", "02",
		   "連体詞", "07",
		   "フィラー", "25"
	};
	
	static final String cform_list[] = {
		   "*", "xx",
		   "その他", "6",
		   "仮定形", "4",
		   "基本形", "2",
		   "未然形", "0",
		   "命令形", "5",
		   "連体形", "3",
		   "連用形", "1"
	};
	
	static final String ctype_list[] = {
		   "*", "xx",
		   "カ行変格", "5",
		   "サ行変格", "4",
		   "ラ行変格", "6",
		   "一段", "3",
		   "形容詞", "7",
		   "五段", "1",
		   "四段", "6",
		   "助動詞", "7",
		   "二段", "6",
		   "不変化", "6",
		   "文語助動詞", "6"
	};


	String pronunciation;
	String pos;
	String ctype;
	String cform;
	Gyutan_JPCommonLabelMora head;
	Gyutan_JPCommonLabelMora tail;
	Gyutan_JPCommonLabelWord prev;
	Gyutan_JPCommonLabelWord next;
	Gyutan_JPCommonLabelAccentPhrase up;
	
	void initialize(String pron, String pos, String ctype, String cform, 
					Gyutan_JPCommonLabelMora head, Gyutan_JPCommonLabelMora tail,
					Gyutan_JPCommonLabelWord prev, Gyutan_JPCommonLabelWord next){
		
		this.pronunciation = pron;
		int i = 0, find = 0;
		for(i=0;i < pos_list.length;i+=2){
			if(pos_list[i].equals(pos) == true){
				find = 1;
				break;
			}
		}
		if(find == 0){
			System.err.printf("WARNING: JPCommonLabelWord.initialize(): %s is unknown POS.\n", pos);
			i = 0;
		}
		this.pos = pos_list[i + 1];
	
		find = 0;
		for (i = 0; i < ctype_list.length; i += 2) {
			if (ctype_list[i].equals(ctype) == true) {
		         find = 1;
		         break;
		      }
		   }
		   if (find == 0) {
		      System.err.printf(
		              "WARNING: JPCommonLabelWord_initializel() in jpcommon_label.c: %s is unknown conjugation type.\n",
		              ctype);
		      i = 0;
		   }
		this.ctype = ctype_list[i+1];

		find = 0;
		for(i = 0;i < cform_list.length;i+=2){
			if(cform_list[i].equals(cform) == true){
				find = 1;
				break;
			}
		}
		if(find == 0){
			System.err.printf("WARNING: JPCommonLabelWord.initialize(): %s is unknown conjugation form.\n", cform);
			i = 0;
		}		
		this.cform = cform_list[i+1];

		this.head = head;
		this.tail = tail;
		this.prev = prev;
		this.next = next;
	}
	
}
