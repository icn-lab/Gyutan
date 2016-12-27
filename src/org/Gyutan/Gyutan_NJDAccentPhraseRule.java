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

public class Gyutan_NJDAccentPhraseRule {
	static final String ACCENT_PHRASE_MEISHI="名詞";
	static final String ACCENT_PHRASE_KEIYOUSHI="形容詞";
	static final String ACCENT_PHRASE_DOUSHI="動詞";
	static final String ACCENT_PHRASE_FUKUSHI="副詞";
	static final String ACCENT_PHRASE_SETSUZOKUSHI="接続詞";
	static final String ACCENT_PHRASE_RENTAISHI="連体詞";
	static final String ACCENT_PHRASE_JODOUSHI="助動詞";
	static final String ACCENT_PHRASE_JOSHI="助詞";
	static final String ACCENT_PHRASE_KIGOU="記号";
	static final String ACCENT_PHRASE_KEIYOUDOUSHI_GOKAN="形容動詞語幹";
	static final String ACCENT_PHRASE_FUKUSHI_KANOU="副詞可能";
	static final String ACCENT_PHRASE_SETSUBI="接尾";
	static final String ACCENT_PHRASE_HIJIRITSU="非自立";
	static final String ACCENT_PHRASE_RENYOU="連用";
	static final String ACCENT_PHRASE_SETSUZOKUJOSHI="接続助詞";
	static final String ACCENT_PHRASE_SAHEN_SETSUZOKU="サ変接続";
	static final String ACCENT_PHRASE_TE="て";
	static final String ACCENT_PHRASE_DE="で";
	static final String ACCENT_PHRASE_SETTOUSHI="接頭詞";
	static final String ACCENT_PHRASE_SEI="姓";
	static final String ACCENT_PHRASE_MEI="名";
	
	/*
	  Rule 01 デフォルトはくっつける
	  Rule 02 「名詞」の連続はくっつける
	  Rule 03 「形容詞」の後に「名詞」がきたら別のアクセント句に
	  Rule 04 「名詞,形容動詞語幹」の後に「名詞」がきたら別のアクセント句に
	  Rule 05 「動詞」の後に「形容詞」or「名詞」がきたら別のアクセント句に
	  Rule 06 「副詞」，「接続詞」，「連体詞」は単独のアクセント句に
	  Rule 07 「名詞,副詞可能」（すべて，など）は単独のアクセント句に
	  Rule 08 「助詞」or「助動詞」（付属語）は前にくっつける
	  Rule 09 「助詞」or「助動詞」（付属語）の後の「助詞」，「助動詞」以外（自立語）は別のアクセント句に
	  Rule 10 「*,接尾」の後の「名詞」は別のアクセント句に
	  Rule 11 「形容詞,非自立」は「動詞,連用*」or「形容詞,連用*」or「助詞,接続助詞,て」or「助詞,接続助詞,で」に接続する場合に前
	にくっつける
	  Rule 12 「動詞,非自立」は「動詞,連用*」or「名詞,サ変接続」に接続する場合に前にくっつける
	  Rule 13 「名詞」の後に「動詞」or「形容詞」or「名詞,形容動詞語幹」がきたら別のアクセント句に
	  Rule 14 「記号」は単独のアクセント句に
	  Rule 15 「接頭詞」は単独のアクセント句に
	  Rule 16 「*,*,*,姓」の後の「名詞」は別のアクセント句に
	  Rule 17 「名詞」の後の「*,*,*,名」は別のアクセント句に
	  Rule 18 「*,接尾」は前にくっつける
	*/

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
	
	static void set_accent_phrase(Gyutan_NJD njd){
		if(njd == null || njd.head == null)
			return;
		
		for(Gyutan_NJDNode node = njd.head.next; node != null; node = node.next){
			if(node.get_chain_flag() < 0){
				rule_001(node);
				rule_002(node);
				rule_003(node);
				rule_004(node);
				rule_005(node);
				rule_006(node);
				rule_007(node);
				rule_008(node);
				rule_009(node);
				rule_010(node);
				rule_011(node);
				rule_012(node);
				rule_013(node);
				rule_014(node);
				rule_015(node);
				rule_016(node);
				rule_017(node);
				rule_018(node);
			}
		}
	}
	
	static void rule_001(Gyutan_NJDNode node){
		node.set_chain_flag(1);
	}
	
	static void rule_002(Gyutan_NJDNode node){
		if(node.prev.get_pos().equals(ACCENT_PHRASE_MEISHI) == true)
			if(node.get_pos().equals(ACCENT_PHRASE_MEISHI) == true){
				node.set_chain_flag(1);
			}
	}

	static void rule_003(Gyutan_NJDNode node){
		if(node.prev.get_pos().equals(ACCENT_PHRASE_KEIYOUSHI) == true)
			if(node.get_pos().equals(ACCENT_PHRASE_MEISHI) == true){
				node.set_chain_flag(0);
			}
	}
	
	static void rule_004(Gyutan_NJDNode node){
		if(node.prev.get_pos().equals(ACCENT_PHRASE_MEISHI) == true)
			if(node.prev.get_pos_group1().equals(ACCENT_PHRASE_KEIYOUDOUSHI_GOKAN) == true)
				if(node.get_pos().equals(ACCENT_PHRASE_MEISHI) == true){
					node.set_chain_flag(0);
				}
	}
	
	static void rule_005(Gyutan_NJDNode node){
		if(node.prev.get_pos().equals(ACCENT_PHRASE_DOUSHI) == true)
			if(node.get_pos().equals(ACCENT_PHRASE_KEIYOUSHI) == true){
				node.set_chain_flag(0);
			}
			else if(node.get_pos().equals(ACCENT_PHRASE_MEISHI) == true){
				node.set_chain_flag(0);
			}
	}
	
	static void rule_006(Gyutan_NJDNode node){
		if(node.get_pos().equals(ACCENT_PHRASE_FUKUSHI) == true 
				|| node.prev.get_pos().equals(ACCENT_PHRASE_FUKUSHI) == true
				|| node.get_pos().equals(ACCENT_PHRASE_SETSUZOKUSHI) == true
				|| node.prev.get_pos().equals(ACCENT_PHRASE_SETSUZOKUSHI) == true
				|| node.get_pos().equals(ACCENT_PHRASE_RENTAISHI) == true
				|| node.prev.get_pos().equals(ACCENT_PHRASE_RENTAISHI) == true){
			node.set_chain_flag(0);
		}
	}
	
	static void rule_007(Gyutan_NJDNode node){
		if(node.prev.get_pos().equals(ACCENT_PHRASE_MEISHI) == true)
			if(node.prev.get_pos_group1().equals(ACCENT_PHRASE_FUKUSHI_KANOU) == true){
				node.set_chain_flag(0);
			}
		
		if(node.get_pos().equals(ACCENT_PHRASE_MEISHI) == true)
			if(node.get_pos_group1().equals(ACCENT_PHRASE_FUKUSHI_KANOU) == true){
				node.set_chain_flag(0);
			}
	}
	
	static void rule_008(Gyutan_NJDNode node){
		if(node.get_pos().equals(ACCENT_PHRASE_JODOUSHI) == true){
			node.set_chain_flag(1);
		}
		if(node.get_pos().equals(ACCENT_PHRASE_JOSHI) == true){
			node.set_chain_flag(1);
		}
	}
	
	static void rule_009(Gyutan_NJDNode node){
		if(node.prev.get_pos().equals(ACCENT_PHRASE_JODOUSHI) == true)
			if(node.get_pos().equals(ACCENT_PHRASE_JODOUSHI) == false &&
			node.get_pos().equals(ACCENT_PHRASE_JOSHI) == false){
				node.set_chain_flag(0);
			}
		if(node.prev.get_pos().equals(ACCENT_PHRASE_JOSHI) == true)
			if(node.get_pos().equals(ACCENT_PHRASE_JODOUSHI) == false &&
			node.get_pos().equals(ACCENT_PHRASE_JOSHI) == false){
				node.set_chain_flag(0);
			}
	}
	
	static void rule_010(Gyutan_NJDNode node){
		if(node.prev.get_pos_group1().equals(ACCENT_PHRASE_SETSUBI) == true)
			if(node.get_pos().equals(ACCENT_PHRASE_MEISHI) == true){
				node.set_chain_flag(0);
			}
	}
	
	static void rule_011(Gyutan_NJDNode node){
		if(node.get_pos().equals(ACCENT_PHRASE_KEIYOUSHI) == true)
			if(node.get_pos_group1().equals(ACCENT_PHRASE_HIJIRITSU) == true){
				if(node.prev.get_pos().equals(ACCENT_PHRASE_DOUSHI) == true){
					if(strtopcmp(node.prev.get_cform(), ACCENT_PHRASE_RENYOU) != -1){
						node.set_chain_flag(1);
					}
				}
				else if(node.prev.get_pos().equals(ACCENT_PHRASE_KEIYOUSHI) == true){
					if(strtopcmp(node.prev.get_cform(), ACCENT_PHRASE_RENYOU) != -1){
						node.set_chain_flag(1);
					}
				}
				else if(node.prev.get_pos().equals(ACCENT_PHRASE_JOSHI) == true){
					if(node.prev.get_pos_group1().equals(ACCENT_PHRASE_SETSUZOKUJOSHI) == true){
						if(node.prev.get_string().equals(ACCENT_PHRASE_TE) == true){
							node.set_chain_flag(1);
						}
						else if(node.prev.get_string().equals(ACCENT_PHRASE_DE) == true){
							node.set_chain_flag(1);
						}
					}
				}
			}
	}
	
	static void rule_012(Gyutan_NJDNode node){
		if(node.get_pos().equals(ACCENT_PHRASE_DOUSHI) == true)
			if(node.get_pos_group1().equals(ACCENT_PHRASE_HIJIRITSU) == true){
				if(node.prev.get_pos().equals(ACCENT_PHRASE_DOUSHI) == true){
					if(strtopcmp(node.prev.get_cform(), ACCENT_PHRASE_RENYOU) != -1){
						node.set_chain_flag(1);
					}
				}
				else if(node.prev.get_pos().equals(ACCENT_PHRASE_MEISHI) == true){
					if(node.prev.get_pos_group1().equals(ACCENT_PHRASE_SAHEN_SETSUZOKU) == true){
						node.set_chain_flag(1);
					}
				}
			}
	}
	
	static void rule_013(Gyutan_NJDNode node){
		if(node.prev.get_pos().equals(ACCENT_PHRASE_MEISHI) == true){
			if(node.get_pos().equals(ACCENT_PHRASE_DOUSHI) == true ||
					node.get_pos().equals(ACCENT_PHRASE_KEIYOUSHI) == true ||
					node.get_pos_group1().equals(ACCENT_PHRASE_KEIYOUDOUSHI_GOKAN) == true){
				node.set_chain_flag(0);
			}
		}
	}
	
	static void rule_014(Gyutan_NJDNode node){
		if(node.get_pos().equals(ACCENT_PHRASE_KIGOU) == true ||
				node.prev.get_pos().equals(ACCENT_PHRASE_KIGOU) == true){
			node.set_chain_flag(0);
		}
	}
	
	static void rule_015(Gyutan_NJDNode node){
		if(node.get_pos_group3().equals(ACCENT_PHRASE_SETTOUSHI) == true){
			node.set_chain_flag(0);
		}
	}
	
	static void rule_016(Gyutan_NJDNode node){
		if(node.prev.get_pos_group3().equals(ACCENT_PHRASE_SEI) == true){
			node.set_chain_flag(0);
		}
	}
	
	static void rule_017(Gyutan_NJDNode node){
		if(node.prev.get_pos().equals(ACCENT_PHRASE_MEISHI) == true &&
				node.get_pos_group3().equals(ACCENT_PHRASE_MEI) == true){
			node.set_chain_flag(0);
		}
	}
	
	static void rule_018(Gyutan_NJDNode node){
		if(node.get_pos_group1().equals(ACCENT_PHRASE_SETSUBI) == true){
			node.set_chain_flag(1);
		}
	}
}
