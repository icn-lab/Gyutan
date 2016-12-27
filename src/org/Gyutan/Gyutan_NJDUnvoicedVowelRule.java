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

public class Gyutan_NJDUnvoicedVowelRule {
	/*
	  無声子音: k ky s sh t ty ch ts h f hy p py
	  Rule 0 フィラーは無声化しない
	  Rule 1 助動詞の「です」と「ます」の「す」が無声化
	  Rule 2 動詞，助動詞，助詞の「し」は無声化しやすい
	  Rule 3 続けて無声化しない
	  Rule 4 アクセント核で無声化しない
	  Rule 5 無声子音(k ky s sh t ty ch ts h f hy p py)に囲まれた「i」と「u」が無声化
	         例外：s->s, s->sh, f->f, f->h, f->hy, h->f, h->h, h->hy
	*/

	static final String UNVOICED_VOWEL_FILLER = "フィラー";
	static final String UNVOICED_VOWEL_DOUSHI = "動詞";
	static final String UNVOICED_VOWEL_JODOUSHI = "助動詞";
	static final String UNVOICED_VOWEL_JOSHI = "助詞";
	static final String UNVOICED_VOWEL_KANDOUSHI = "感動詞";
	static final String UNVOICED_VOWEL_TOUTEN = "、";
	static final String UNVOICED_VOWEL_QUESTION = "？";
	static final String UNVOICED_VOWEL_QUOTATION = "’";
	static final String UNVOICED_VOWEL_SHI = "シ";
	static final String UNVOICED_VOWEL_MA = "マ";
	static final String UNVOICED_VOWEL_DE = "デ";
	static final String UNVOICED_VOWEL_CHOUON = "ー";
	static final String UNVOICED_VOWEL_SU = "ス";

	static final String[] unvoiced_vowel_candidate_list1 = {
		   "スィ",                      /* s i */
		   "ス"                        /* s u */
		};

	static final String[] unvoiced_vowel_next_mora_list1 = {
		   "カ",                        /* k ky */
		   "キ",
		   "ク",
		   "ケ",
		   "コ",
		   "タ",                        /* t ty ch ts */
		   "チ",
		   "ツ",
		   "テ",
		   "ト",
		   "ハ",                        /* h f hy */
		   "ヒ",
		   "フ",
		   "ヘ",
		   "ホ",
		   "パ",                        /* p py */
		   "ピ",
		   "プ",
		   "ペ",
		   "ポ"
		};

	static final String[] unvoiced_vowel_candidate_list2 = {
		   "フィ",                      /* f i */
		   "ヒ",                        /* h i */
		   "フ"                        /* f u */
		};

	static final String[] unvoiced_vowel_next_mora_list2 = {
			   "カ",                        /* k ky */
			   "キ",
			   "ク",
			   "ケ",
			   "コ",
			   "サ",                        /* s sh */
			   "シ",
			   "ス",
			   "セ",
			   "ソ",
			   "タ",                        /* t ty ch ts */
			   "チ",
			   "ツ",
			   "テ",
			   "ト",
			   "パ",                        /* p py */
			   "ピ",
			   "プ",
			   "ペ",
			   "ポ"
			};

	static final String[] unvoiced_vowel_candidate_list3 = {
			   "キュ",                      /* ky u */
			   "シュ",                      /* sh u */
			   "チュ",                      /* ch u */
			   "ツィ",                      /* ts i */
			   "ヒュ",                      /* hy u */
			   "ピュ",                      /* py u */
			   "テュ",                      /* ty u */
			   "トゥ",                      /* t u */
			   "ティ",                      /* t i */
			   "キ",                        /* k i */
			   "ク",                        /* k u */
			   "シ",                        /* sh i */
			   "チ",                        /* ch i */
			   "ツ",                        /* ts u */
			   "ピ",                        /* p i */
			   "プ"                        /* p u */
			};

	static final String[] unvoiced_vowel_next_mora_list3 = {
				   "カ",                        /* k ky */
				   "キ",
				   "ク",
				   "ケ",
				   "コ",
				   "サ",                        /* s sh */
				   "シ",
				   "ス",
				   "セ",
				   "ソ",
				   "タ",                        /* t ty ch ts */
				   "チ",
				   "ツ",
				   "テ",
				   "ト",
				   "ハ",                        /* h f hy */
				   "ヒ",
				   "フ",
				   "ヘ",
				   "ホ",
				   "パ",                        /* p py */
				   "ピ",
				   "プ",
				   "ペ",
				   "ポ"
				};
			
	static final String[] unvoiced_vowel_mora_list = {
				   "ヴョ",
				   "ヴュ",
				   "ヴャ",
				   "ヴォ",
				   "ヴェ",
				   "ヴィ",
				   "ヴァ",
				   "ヴ",
				   "ン",
				   "ヲ",
				   "ヱ",
				   "ヰ",
				   "ワ",
				   "ロ",
				   "レ",
				   "ル",
				   "リョ",
				   "リュ",
				   "リャ",
				   "リェ",
				   "リ",
				   "ラ",
				   "ヨ",
				   "ョ",
				   "ユ",
				   "ュ",
				   "ヤ",
				   "ャ",
				   "モ",
				   "メ",
				   "ム",
				   "ミョ",
				   "ミュ",
				   "ミャ",
				   "ミェ",
				   "ミ",
				   "マ",
				   "ポ",
				   "ボ",
				   "ホ",
				   "ペ",
				   "ベ",
				   "ヘ",
				   "プ",
				   "ブ",
				   "フォ",
				   "フェ",
				   "フィ",
				   "ファ",
				   "フ",
				   "ピョ",
				   "ピュ",
				   "ピャ",
				   "ピェ",
				   "ピ",
				   "ビョ",
				   "ビュ",
				   "ビャ",
				   "ビェ",
				   "ビ",
				   "ヒョ",
				   "ヒュ",
				   "ヒャ",
				   "ヒェ",
				   "ヒ",
				   "パ",
				   "バ",
				   "ハ",
				   "ノ",
				   "ネ",
				   "ヌ",
				   "ニョ",
				   "ニュ",
				   "ニャ",
				   "ニェ",
				   "ニ",
				   "ナ",
				   "ドゥ",
				   "ド",
				   "トゥ",
				   "ト",
				   "デョ",
				   "デュ",
				   "デャ",
				   "デェ",
				   "ディ",
				   "デ",
				   "テョ",
				   "テュ",
				   "テャ",
				   "テェ",
				   "ティ",
				   "テ",
				   "ヅ",
				   "ツォ",
				   "ツェ",
				   "ツィ",
				   "ツァ",
				   "ツ",
				   "ッ",
				   "ヂ",
				   "チョ",
				   "チュ",
				   "チャ",
				   "チェ",
				   "チ",
				   "ダ",
				   "タ",
				   "ゾ",
				   "ソ",
				   "ゼ",
				   "セ",
				   "ズィ",
				   "ズ",
				   "スィ",
				   "ス",
				   "ジョ",
				   "ジュ",
				   "ジャ",
				   "ジェ",
				   "ジ",
				   "ショ",
				   "シュ",
				   "シャ",
				   "シェ",
				   "シ",
				   "ザ",
				   "サ",
				   "ゴ",
				   "コ",
				   "ゲ",
				   "ケ",
				   "グ",
				   "ク",
				   "ギョ",
				   "ギュ",
				   "ギャ",
				   "ギェ",
				   "ギ",
				   "キョ",
				   "キュ",
				   "キャ",
				   "キェ",
				   "キ",
				   "ガ",
				   "カ",
				   "オ",
				   "ォ",
				   "エ",
				   "ェ",
				   "ウォ",
				   "ウェ",
				   "ウィ",
				   "ウ",
				   "ゥ",
				   "イェ",
				   "イ",
				   "ィ",
				   "ア",
				   "ァ",
				   "ー"
				};

	static int strtopcmp(String str, String pattern){
		//char[] strat = str.toCharArray();
		//char[] patat = pattern.toCharArray();
		
		for(int i = 0;;i++){
			if(i == pattern.length())
				return i;
			if(i == str.length())
				return -1;
			if(str.charAt(i) != pattern.charAt(i))
			//if(strat[i] != patat[i])
				return -1;
		}
	}
	
	static void get_mora_information(Gyutan_NJDNode node, int index, Gyutan_NJDUnvoicedVowelRuleMoraInformation mi){
		int len = 0;
		String str = node.get_pronunciation();
		if(str != null)
			len    = str.length();

		if(index >= len){
			if(node.next != null){
				get_mora_information(node.next, index-len, mi);
			}
			else{
				mi.mora = null;
				mi.nlink = null;
				mi.flag = -1;
				mi.size = 0;
				mi.midx = 0;
				mi.atype = 0;
			}
			return;
		}
		
		mi.nlink = node;
		
		 /* reset mora index and accent type for new word */
		if(index == 0 && node.get_chain_flag() != 1){
			mi.midx = 0;
			mi.atype = node.get_accent();
		}
		
		/* special symbol */
		if(str.equals(UNVOICED_VOWEL_TOUTEN) == true){
			mi.mora = UNVOICED_VOWEL_TOUTEN;
			mi.flag = 0;
			mi.size = UNVOICED_VOWEL_TOUTEN.length();
			return;
		}
		if(str.equals(UNVOICED_VOWEL_QUESTION) == true){
			mi.mora = UNVOICED_VOWEL_QUESTION;
			mi.flag = 0;
			mi.size = UNVOICED_VOWEL_QUESTION.length();
			return;
		}
		
		/* reset */
		mi.mora = null;
		mi.flag = -1;
		mi.size = 0;
		
		/* get mora */
		for(int i=0;i < unvoiced_vowel_mora_list.length;i++){
			int matched_size = strtopcmp(str.substring(index), unvoiced_vowel_mora_list[i]);
			if(matched_size > 0){
				mi.mora = unvoiced_vowel_mora_list[i];
				mi.size = matched_size;
				break;
			}
		}
		
		/*get unvoiced flag */
		int matched_size = strtopcmp(str.substring(index+mi.size), UNVOICED_VOWEL_QUOTATION);
		if(matched_size > 0){
			mi.flag = 1;
			mi.size += matched_size;
		}
	}
	
	static int apply_unvoice_rule(String current, String next){
		if(next == null)
			return 0;
		
		for(int i=0;i < unvoiced_vowel_candidate_list1.length;i++){
			if(unvoiced_vowel_candidate_list1[i].equals(current) == true){
				for(int j = 0;j < unvoiced_vowel_next_mora_list1.length;j++)
					if(strtopcmp(next, unvoiced_vowel_next_mora_list1[j]) > 0)
						return 1;
				return 0;
			}
		}
		
		for(int i=0;i < unvoiced_vowel_candidate_list2.length;i++){
			if(unvoiced_vowel_candidate_list2[i].equals(current) == true){
				for(int j = 0;j < unvoiced_vowel_next_mora_list2.length;j++)
					if(strtopcmp(next, unvoiced_vowel_next_mora_list2[j]) > 0)
						return 1;
				return 0;
			}
		}
		
		for(int i=0;i < unvoiced_vowel_candidate_list3.length;i++){
			if(unvoiced_vowel_candidate_list3[i].equals(current) == true){
				for(int j = 0;j < unvoiced_vowel_next_mora_list3.length;j++)
					if(strtopcmp(next, unvoiced_vowel_next_mora_list3[j]) > 0)
						return 1;
				return 0;
			}
		}
		
		return -1;
	}
	
	static void set_unvoiced_vowel(Gyutan_NJD njd){
		Gyutan_NJDUnvoicedVowelRuleMoraInformation mi1, mi2, mi3;
		
		mi1 = new Gyutan_NJDUnvoicedVowelRuleMoraInformation();
		mi1.size  = 0;
		mi1.mora  = null;
		mi1.nlink = null;
		mi1.flag  = -1;
		mi1.midx  = 0;
		mi1.atype = 0;
		
		mi2 = new Gyutan_NJDUnvoicedVowelRuleMoraInformation();
		mi2.size  = 0;
		mi2.mora  = null;
		mi2.nlink = null;
		mi2.flag  = -1;
		mi2.midx  = 1;
		mi2.atype = 0;

		mi3 = new Gyutan_NJDUnvoicedVowelRuleMoraInformation();
		mi3.size  = 0;
		mi3.mora  = null;
		mi3.nlink = null;
		mi3.flag  = -1;
		mi3.midx  = 2;
		mi3.atype = 0;
		
		for(Gyutan_NJDNode node = njd.head;node != null;node = node.next){
			StringBuilder buff = new StringBuilder();
			String str = node.get_pronunciation();
			int len;
			if(str != null)
				len = str.length();
			else 
				len = 0;
			
			/* parse pronunciation */
			for(int index = 0;index < len;){
				if(mi1.mora == null){
					get_mora_information(node, index, mi1);
				}
				if(mi1.mora == null){
					System.err.println("WARNING: NJDUnvoicedVowelRule.set_unvoiced_vowel(): Wrong pronunciation.");
					return;
				}
				
				if(mi2.mora == null){
					mi2.midx  = mi1.midx + 1;
					mi2.atype = mi1.atype;
					get_mora_information(node, index+mi1.size, mi2);
				}
				
				if(mi3.mora == null){
					mi3.midx  = mi2.midx + 1;
					mi3.atype = mi2.atype;
					get_mora_information(node, index+mi1.size+mi2.size, mi3);
				}
			
				/* rule 1: */
				if(mi2.mora != null && mi3.mora != null && 
						mi1.nlink == mi2.nlink &&
						mi2.nlink != mi3.nlink &&
						(mi1.mora.equals(UNVOICED_VOWEL_MA) == true ||
						mi1.mora.equals(UNVOICED_VOWEL_DE) == true) &&
						mi2.mora.equals(UNVOICED_VOWEL_SU) == true &&
						(mi2.nlink.get_pos().equals(UNVOICED_VOWEL_DOUSHI) == true ||
						mi2.nlink.get_pos().equals(UNVOICED_VOWEL_JODOUSHI) == true ||
						mi2.nlink.get_pos().equals(UNVOICED_VOWEL_KANDOUSHI) == true)){
					if(mi3.nlink.get_pronunciation().equals(UNVOICED_VOWEL_QUESTION) == true ||
							mi3.nlink.get_pronunciation().equals(UNVOICED_VOWEL_CHOUON) == true)
						mi2.flag = 0;
					else
						mi2.flag = 1;
				}
				
				/* rule 2: */
				if(mi1.flag != 1 && mi2.flag == -1 && mi3.flag != 1 && mi2.mora != null &&
						mi2.nlink.get_pronunciation().equals(UNVOICED_VOWEL_SHI) == true &&
						(mi2.nlink.get_pos().equals(UNVOICED_VOWEL_DOUSHI) == true ||
						mi2.nlink.get_pos().equals(UNVOICED_VOWEL_JODOUSHI) == true ||
						mi2.nlink.get_pos().equals(UNVOICED_VOWEL_JOSHI) == true)){
					if(mi2.atype == mi2.midx + 1){
						/* rule 4: */
						mi2.flag = 0;
					}
					else{
						/* rule 5: */
						mi2.flag = apply_unvoice_rule(mi2.mora, mi3.mora);
					}
					if(mi2.flag == 1){
						if(mi1.flag == -1)
							mi1.flag = 0;
						if(mi3.flag == -1)
							mi3.flag = 0;
					}
				}
				
				/* estimate unvoice */
				if(mi1.flag == -1){
					if(mi1.nlink.get_pos().equals(UNVOICED_VOWEL_FILLER) == true){
						/* rule 0 */
						mi1.flag = 0;
					}
					else if(mi2.flag == 1){
						/* rule 3 */
						mi1.flag = 0;
					}
					else if(mi1.atype == mi1.midx + 1){
						/* rule 4 */
						mi1.flag = 0;
					}
					else {
						/* rule 5 */
						mi1.flag = apply_unvoice_rule(mi1.mora, mi2.mora);
					}
				}
				if(mi1.flag == 1 && mi2.flag == -1){
					mi2.flag = 0;
				}
				
				/* store pronunciation */
				buff.append(mi1.mora);
				if(mi1.flag == 1)
					buff.append(UNVOICED_VOWEL_QUOTATION);
				
				/* prepare next step */
				index += mi1.size;
				
				mi1 = mi2;
				mi2 = mi3;
				
				mi3 = new Gyutan_NJDUnvoicedVowelRuleMoraInformation();
				mi3.mora  = null;
				mi3.nlink = null;
				mi3.size  = 0;
				mi3.flag  = -1;
				mi3.midx  = 0;
				mi3.atype = 0;
			}
		
			node.set_pronunciation(buff.toString());
		}
	}

}
