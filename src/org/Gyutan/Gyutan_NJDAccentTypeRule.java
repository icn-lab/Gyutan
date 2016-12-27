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

public class Gyutan_NJDAccentTypeRule {
	static final String ACCENT_TYPE_KAZU="数";
	static final String ACCENT_TYPE_ICHI="一";
	static final String ACCENT_TYPE_NI="二";
	static final String ACCENT_TYPE_SAN="三";
	static final String ACCENT_TYPE_YON="四";
	static final String ACCENT_TYPE_GO="五";
	static final String ACCENT_TYPE_ROKU="六";
	static final String ACCENT_TYPE_NANA="七";
	static final String ACCENT_TYPE_HACHI="八";
	static final String ACCENT_TYPE_KYUU="九";
	static final String ACCENT_TYPE_JYUU="十";
	static final String ACCENT_TYPE_HYAKU="百";
	static final String ACCENT_TYPE_SEN="千";
	static final String ACCENT_TYPE_MAN="万";
	static final String ACCENT_TYPE_OKU="億";
	static final String ACCENT_TYPE_CHOU="兆";
	static final String ACCENT_TYPE_SUU="数";
	static final String ACCENT_TYPE_NAN="何";
	static final String ACCENT_TYPE_IKU="幾";
	
	static char get_token_from_string(String string, int[] index, StringBuilder sb){
		sb.delete(0,  sb.length());
		char c = 0;
		//char[] charat = string.toCharArray();
		
		if(index[0] == string.length()){
			c = (char)-1;
		}
		else{
			c = string.charAt(index[0]);
			//c = charat[index[0]];
		}
		
		if(index[0] < string.length()){
			while(c != '%' && c != '@' && c != '/' && c != (char)-1){
				sb.append(c);
				index[0]++;
				if(index[0] < string.length())
					c = string.charAt(index[0]);
					//c = charat[index[0]];
				else
					c = (char)-1;
			}
			if(c == '%' || c == '@' || c == '/')
				index[0]++;
		}
	
		return c;
	}
	
	static String get_rule(String input_rule, String prev_pos, int[] add_type){
		char c = ' ';
		StringBuilder buff = new StringBuilder();
		
		int [] index = new int[1];
		index[0] = 0;
		
		String rule = "";
		
		if(input_rule != null){
			while(c != (char)-1){
				c = get_token_from_string(input_rule, index, buff);
				if((c == '%' && prev_pos.indexOf(buff.toString()) != -1) ||
						c == '@' || c == '/' || c == (char)-1){
					if(c == '%')
						c = get_token_from_string(input_rule, index, buff);
					else
						rule = buff.toString();
					if(c == '@'){
						c = get_token_from_string(input_rule, index, buff);
						add_type[0] = Integer.parseInt(buff.toString());
					}
					else{
						add_type[0] = 0;
					}
					return rule;
				}
				else{
					while(c == '%' || c == '@')
						c = get_token_from_string(input_rule, index, buff);
				}
			}
		}
		
		rule = "*";
		add_type[0] = 0;
		
		return rule;
	}
	
	static void change_accent_type(Gyutan_NJDNode top_node, Gyutan_NJDNode node, String rule, int mora_size, int add_type){
		if(rule == null)
			return;
		
		switch(rule){
		case "F1":
			break;
		case "F2":
			if(top_node.get_accent() == 0)
				top_node.set_accent(mora_size + add_type);
			break;
		case "F3":
			if(top_node.get_accent() != 0)
				top_node.set_accent(mora_size + add_type);
			break;
		case "F4":
			top_node.set_accent(mora_size + add_type);
			break;
		case "F5":
			top_node.set_accent(0);
			break;
		case "C1":
			top_node.set_accent(mora_size + node.get_accent());
			break;
		case "C2":
			top_node.set_accent(mora_size + 1);
			break;
		case "C3":
			top_node.set_accent(mora_size);
			break;
		case "C4":
			top_node.set_accent(0);
			break;
		case "C5":
			break;
		case "P1":
			if(node.get_accent() == 0)
				top_node.set_accent(0);
			else
				top_node.set_accent(mora_size + node.get_accent());
			break;
		case "P2":
			if(node.get_accent() == 0)
				top_node.set_accent(mora_size + 1);
			else
				top_node.set_accent(mora_size + node.get_accent());
			break;
		case "P6":
			top_node.set_accent(0);
			break;
		case "P14":
			if(node.get_accent() != 0)
				top_node.set_accent(mora_size + node.get_accent());
			break;
		default:
			break;
		}
	}
	
	static void change_accent_type_for_digit(Gyutan_NJDNode node){
		if(node.prev != null && node.get_chain_flag() == 1 &&
				node.prev.get_pos_group1().equals(ACCENT_TYPE_KAZU) == true &&
				node.get_pos_group1().equals(ACCENT_TYPE_KAZU) == true){
				if(node.get_string().equals(ACCENT_TYPE_JYUU) == true){

					if(node.prev.get_string() != null && 
							(node.prev.get_string().equals(ACCENT_TYPE_SAN) == true ||
							node.prev.get_string().equals(ACCENT_TYPE_YON) == true ||
							node.prev.get_string().equals(ACCENT_TYPE_KYUU) == true ||
							node.prev.get_string().equals(ACCENT_TYPE_NAN) == true ||
							node.prev.get_string().equals(ACCENT_TYPE_SUU) == true)){
								node.prev.set_accent(1);
					}
					else{
							node.prev.set_accent(1);
					}
			
					if(node.prev.get_string() != null &&
							(node.prev.get_string().equals(ACCENT_TYPE_GO) == true ||
							node.prev.get_string().equals(ACCENT_TYPE_ROKU) == true||
							node.prev.get_string().equals(ACCENT_TYPE_HACHI) == true)){
			
						if(node.next != null && node.next.get_string() != null &&
								(node.next.get_string().equals(ACCENT_TYPE_ICHI) == true ||
								node.next.get_string().equals(ACCENT_TYPE_NI) == true ||
								node.next.get_string().equals(ACCENT_TYPE_SAN) == true ||
								node.next.get_string().equals(ACCENT_TYPE_YON) == true ||
								node.next.get_string().equals(ACCENT_TYPE_GO) == true ||
								node.next.get_string().equals(ACCENT_TYPE_ROKU) == true ||
								node.next.get_string().equals(ACCENT_TYPE_NANA) == true ||
								node.next.get_string().equals(ACCENT_TYPE_HACHI) == true ||
								node.next.get_string().equals(ACCENT_TYPE_KYUU) == true))
							node.prev.set_accent(0);
					}
				}
				else if(node.get_string().equals(ACCENT_TYPE_HYAKU) == true){
					if(node.prev.get_string() != null && node.prev.get_string().equals(ACCENT_TYPE_NANA) == true)
						node.prev.set_accent(2);
					else if(node.prev.get_string() != null &&
							(node.prev.get_string().equals(ACCENT_TYPE_SAN) == true ||
							node.prev.get_string().equals(ACCENT_TYPE_YON) == true ||
							node.prev.get_string().equals(ACCENT_TYPE_KYUU) == true ||
							node.prev.get_string().equals(ACCENT_TYPE_NAN) == true))
								node.prev.set_accent(1);
					else
								node.prev.set_accent(node.prev.get_mora_size() + node.get_mora_size());
				}
				else if(node.get_string().equals(ACCENT_TYPE_SEN) == true ||
						node.get_string().equals(ACCENT_TYPE_MAN) == true)
							node.prev.set_accent(node.prev.get_mora_size() + 1);
				else if(node.get_string().equals(ACCENT_TYPE_OKU) == true)
					if(node.prev.get_string() != null &&
						(node.prev.get_string().equals(ACCENT_TYPE_ICHI) == true ||
						node.prev.get_string().equals(ACCENT_TYPE_ROKU) == true ||
						node.prev.get_string().equals(ACCENT_TYPE_NANA) == true ||
						node.prev.get_string().equals(ACCENT_TYPE_HACHI) == true ||
						node.prev.get_string().equals(ACCENT_TYPE_IKU) == true))
							node.prev.set_accent(2);
					else
							node.prev.set_accent(1);
				else if(node.get_string().equals(ACCENT_TYPE_CHOU) == true)
					if(node.prev.get_string() != null &&
						(node.prev.get_string().equals(ACCENT_TYPE_ROKU) == true ||
						node.prev.get_string().equals(ACCENT_TYPE_NANA) == true))
							node.prev.set_accent(2);
					else
							node.prev.set_accent(1);
		}
		if(node.get_string().equals(ACCENT_TYPE_JYUU) == true &&
				node.get_chain_flag() != 1 && 
				node.next != null &&
				node.next.get_pos_group1().equals(ACCENT_TYPE_KAZU) == true
				)
			node.set_accent(0);
	}
	
	static void set_accent_type(Gyutan_NJD njd){
		String rule;
		int[] add_type = new int[1];
		add_type[0] = 0;
		int mora_size = 0;
		
		if(njd == null || njd.head == null)
			return;
	
		Gyutan_NJDNode top_node = null;
		for(Gyutan_NJDNode node = njd.head;node != null; node = node.next){
			if(node.get_string() == null)
				continue;
			if(node == njd.head || node.get_chain_flag() != 1){
				top_node = node;
				mora_size = 0;
			}
			else if(node.prev != null || node.get_chain_flag() == 1){
				rule = get_rule(node.get_chain_rule(), node.prev.get_pos(), add_type);
				
				if(rule.equals("*") == false){
					change_accent_type(top_node, node, rule, mora_size, add_type[0]);
				}
				
			}
			change_accent_type_for_digit(node);
			
			
			mora_size += node.get_mora_size();
		}
	}
}
