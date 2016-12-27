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

import java.io.PrintStream;

public class Gyutan_NJDNode {
	static final String NODATA="*";
	String string;
	String pos;
	String pos_group1;
	String pos_group2;
	String pos_group3;
	String ctype;
	String cform;
	String orig;
	StringBuilder read;
	StringBuilder pronunciation;
	int accent;
	int mora_size;
	String  chain_rule;
	int     chain_flag;
	Gyutan_NJDNode prev;
	Gyutan_NJDNode next;
	
	Gyutan_NJDNode(){
		initialize();
	}
	
	void initialize(){
		string = null;
		pos = null;
		pos_group1 = null;
		pos_group2 = null;
		pos_group3 = null;
		ctype = null;
		cform = null;
		orig = null;
		read = null;
		pronunciation = null;
		accent = 0;
		mora_size = 0;
		chain_rule = null;
		chain_flag = -1;
		prev = null;
		next = null;
	}
	
	private void get_token_from_string(String str, int[] index, StringBuilder buff, char d){
		buff.delete(0, buff.length());
		char[] strat = str.toCharArray();
		
		if(index[0] < str.length()){
			//char c = str.charAt(index[0]);
			char c = strat[index[0]];
			
			while(c != d){
				buff.append(c);
				index[0]++;
				if(index[0] < str.length())
					//c = str.charAt(index[0]);
					c = strat[index[0]];
				else
					break;
			}
			if(c == d)
				index[0]++;
		}
	}
	
	void set_string(String str){
		if(str == null || str.length() == 0)
			string = null;
		else
			string = str;
	}
	
	void set_pos(String str){
		if(str == null || str.length() == 0)
			pos = null;
		else
			pos = str;
	}
	
	void set_pos_group1(String str){
		if(str == null || str.length() == 0)
			pos_group1 = null;
		else
			pos_group1 = str;
	}
	
	void set_pos_group2(String str){
		if(str == null || str.length() == 0)
			pos_group2 = null;
		else
			pos_group2 = str;
	}
	
	void set_pos_group3(String str){
		if(str == null || str.length() == 0)
			pos_group3 = null;
		else
			pos_group3 = str;
	}
	
	void set_cform(String str){
		if(str == null || str.length() == 0)
			cform = null;
		else
			cform = str;
	}
	
	void set_ctype(String str){
		if(str == null || str.length() == 0)
			ctype = null;
		else
			ctype = str;
	}
	
	void set_orig(String str){
		if(str == null || str.length() == 0)
			orig = null;
		else
			orig = str;
	}
	
	void set_read(String str){
		if(str == null || str.length() == 0)
			read = null;
		else
			read = new StringBuilder(str);
	}
	
	void set_pronunciation(String str){
		if(str == null || str.length() == 0)
			pronunciation = null;
		else
			pronunciation = new StringBuilder(str);
	}
	
	void set_accent(int accent){
		this.accent = accent;
		if(accent < 0){
			System.err.println("WARNING: NJDNode.set_accent(): Accent must be positive value");
			this.accent = 0;
		}
	}
	
	void set_mora_size(int size){
		mora_size = size;
		if(mora_size < 0){
			System.err.println("WARNING: NJDNode.set_mora_size(): Mora size must be positive value");
			mora_size = 0;
		}
	}
	
	
	void set_chain_rule(String str){
		if(str == null||str.length() == 0)
			chain_rule = null;
		else
			chain_rule = str;
	}
	
	void set_chain_flag(int flag){
		chain_flag = flag;
	}
	
	void add_read(String str){
		if(str != null){
			if(read == null)
				read = new StringBuilder(str);
			else
				read.append(str);
		}
	}
	
	void add_pronunciation(String str){
		if(str != null){
			if(pronunciation == null)
				pronunciation = new StringBuilder(str);
			else
				pronunciation.append(str);
		}
	}
	
	void add_accent(int accent){
		this.accent += accent;
		if(this.accent < 0){
			System.err.println("WARNING: NJDNode.add_accent(): Accent must be positive value");
			this.accent = 0;
		}
	}
	
	void add_mora_size(int size){
		mora_size += size;
		if(mora_size < 0){
			System.err.println("WARNING: NJDNode.add_mora_size(): Mora size must be positive value");
			mora_size = 0;
		}
	}
	
	String get_string(){
		if(string != null)
			return string;
		else
			return NODATA;
	}
	
	String get_pos(){
		if(pos != null)
			return pos;
		else
			return NODATA;
	}
	
	String get_pos_group1(){
		if(pos_group1 != null)
			return pos_group1;
		else
			return NODATA;
	}
	
	String get_pos_group2(){
		if(pos_group2 != null)
			return pos_group2;
		else
			return NODATA;
	}
	
	String get_pos_group3(){
		if(pos_group3 != null)
			return pos_group3;
		else
			return NODATA;
	}
	
	String get_ctype(){
		if(ctype != null)
			return ctype;
		else
			return NODATA;
	}
	
	String get_cform(){
		if(cform != null)
			return cform;
		else
			return NODATA;
	}
	
	String get_orig(){
		if(orig != null)
			return orig;
		return NODATA;
	}
	
	String get_read(){
		if(read != null)
			return read.toString();
		else
			return NODATA;
	}
	
	String get_pronunciation(){
		if(pronunciation != null)
			return pronunciation.toString();
		else
			return NODATA;
	}
	
	int get_accent(){
		return accent;
	}
	
	int get_mora_size(){
		return mora_size;
	}
	
	String get_chain_rule(){
		if(chain_rule != null)
			return chain_rule;
		else
			return NODATA;
	}
	
	int get_chain_flag(){
		return chain_flag;
	}
	
	void load(String str){		
		StringBuilder buff_string = new StringBuilder();
		StringBuilder buff_orig   = new StringBuilder();
		StringBuilder buff_read   = new StringBuilder();
		StringBuilder buff_pron   = new StringBuilder();
		StringBuilder buff_acc    = new StringBuilder();

		load_cform(str, buff_string, buff_orig, buff_read, buff_pron, buff_acc);
		if(buff_acc.indexOf("*") != -1 || buff_acc.indexOf("/") == -1){
			set_for_symbol(buff_string, buff_orig, buff_read, buff_pron);
			return;
		}
		
		int count = 0;
		for(int i=0;i < buff_acc.length();i++)
			if(buff_acc.charAt(i) == '/')
				count++;

		if(count == 1){
			set_for_single_word(buff_string, buff_orig, buff_read, buff_pron, buff_acc);
			return;
		}
		
		parse_chained_word(buff_string, buff_orig, buff_read, buff_pron, buff_acc, count);
	}
	
	private void load_cform(String str, 
				StringBuilder buff_string, StringBuilder buff_orig, StringBuilder buff_read,
				StringBuilder buff_pron, StringBuilder buff_acc){
		
		StringBuilder buff = new StringBuilder();
		int[] index  = new int[1];
		index[0] = 0;
		
		get_token_from_string(str, index, buff_string, ',');
		get_token_from_string(str, index, buff, ',');
		set_pos(buff.toString());
		
		get_token_from_string(str, index, buff, ',');
		set_pos_group1(buff.toString());
		get_token_from_string(str, index, buff, ',');
		set_pos_group2(buff.toString());
		get_token_from_string(str, index, buff, ',');
		set_pos_group3(buff.toString());
		get_token_from_string(str, index, buff, ',');
		set_ctype(buff.toString());
		get_token_from_string(str, index, buff, ',');
		set_cform(buff.toString());
		
		get_token_from_string(str, index, buff_orig, ',');		
		get_token_from_string(str, index, buff_read, ',');
		get_token_from_string(str, index, buff_pron, ',');
		get_token_from_string(str, index, buff_acc, ',');
		get_token_from_string(str, index, buff, ',');
		set_chain_rule(buff.toString());
		
		get_token_from_string(str, index, buff, ',');
		if(buff.toString().equals("1") == true)
			set_chain_flag(1);
		else if(buff.toString().equals("0") == true)
			set_chain_flag(0);
	}
	
	private void set_for_symbol(StringBuilder buff_string, StringBuilder buff_orig, StringBuilder buff_read, StringBuilder buff_pron){
		set_string(buff_string.toString());
		set_orig(buff_orig.toString());
		set_read(buff_read.toString());
		set_pronunciation(buff_pron.toString());
		set_accent(0);
		set_mora_size(0);
	}
	
	private void set_for_single_word(StringBuilder buff_string, StringBuilder buff_orig, StringBuilder buff_read, 
									StringBuilder buff_pron, StringBuilder buff_acc){		

		set_string(buff_string.toString());
		set_orig(buff_orig.toString());
		set_read(buff_read.toString());
		set_pronunciation(buff_pron.toString());

		int[] index_acc = new int[1];
		index_acc[0] = 0;
		StringBuilder buff = new StringBuilder();
		get_token_from_string(buff_acc.toString(), index_acc, buff, '/');

		if(buff.length() == 0){
			set_accent(0);
			System.err.println("WARNING: NJDNode.set_for_single_word(): Accent is empty.");
		}
		else{
			set_accent(Integer.parseInt(buff.toString()));
		}
		
		get_token_from_string(buff_acc.toString(), index_acc, buff, ':');
		if(buff.length() == 0){
			set_mora_size(0);
			System.err.println("WARNING: NJDNode.set_for_single_word(): Mora size is empty.");
		}
		else{
			set_mora_size(Integer.parseInt(buff.toString()));
		}
		
		
	}
	
	private void parse_chained_word(StringBuilder buff_string, StringBuilder buff_orig, StringBuilder buff_read,
							StringBuilder buff_pron, StringBuilder buff_acc, int count){
		int[] index_string = new int[1];
		index_string[0] = 0;
		int[] index_orig = new int[1];
		index_orig[0] = 0;
		int[] index_read = new int[1];
		index_read[0] = 0;
		int[] index_pron = new int[1];
		index_pron[0] = 0;
		int[] index_acc = new int[1];
		index_acc[0] = 0;
		
		StringBuilder buff = new StringBuilder();
		Gyutan_NJDNode node = this;

		for(int i=0;i < count;i++){
			if(i > 0){
				node = new Gyutan_NJDNode();
				node.copy(prev);
				node.set_chain_flag(0);
				node.prev = prev;
				prev.next = node;
			}
			
			get_token_from_string(buff_orig.toString(), index_orig, buff, ':');
			set_orig(buff.toString());
			
			if(i + 1 < count){
				set_string(buff.toString());
				index_string[0] += buff.length();
			}
			else{
				set_string(buff_string.substring(index_string[0]));
			}
			
			get_token_from_string(buff_read.toString(), index_read, buff, ':');
			set_read(buff.toString());
			
			get_token_from_string(buff_pron.toString(), index_pron, buff, ':');
			set_pronunciation(buff.toString());
			
			get_token_from_string(buff_acc.toString(), index_acc, buff, '/');
			if(buff.length() == 0){
				set_accent(0);
			}
			else{
				set_accent(Integer.parseInt(buff.toString()));
			}
			
			get_token_from_string(buff_acc.toString(), index_acc, buff, ':');
			if(buff.length() == 0){
				set_mora_size(0);
				System.err.println("WARNING: NJDNode.parse_chained_word: Mora size is empty.");
			}
			else{
				set_mora_size(Integer.parseInt(buff.toString()));
			}
			
			prev = node;
		}
	}
	
	Gyutan_NJDNode insert(Gyutan_NJDNode prev, Gyutan_NJDNode next){
		if(prev == null || next == null){
			System.err.println("ERROR: NJDNode.insert(): NJDNode is not specified.");
			System.exit(1);
		}
			
		Gyutan_NJDNode tail;
		for(tail = this; tail.next != null; tail = tail.next);
		prev.next = this;
		this.prev = prev;
		next.prev = tail;
		tail.next = next;
			
		return tail;
	}
	
	void copy(Gyutan_NJDNode node){
		set_string(node.string);
		
		set_pos(node.pos);
		set_pos_group1(node.pos_group1);
		set_pos_group2(node.pos_group2);
		set_pos_group3(node.pos_group3);
		
		set_ctype(node.ctype);
		set_cform(node.cform);
		set_orig(node.orig);
		set_read(node.read.toString());
		set_pronunciation(node.pronunciation.toString());
		set_accent(node.accent);
		set_mora_size(node.mora_size);
		set_chain_rule(node.chain_rule);
		set_chain_flag(node.chain_flag);
	}
	
	void print(PrintStream ps){
		ps.printf("string:%s\n", string);
		ps.printf("pos:%s\n", pos);
		ps.printf("pos_group1:%s\n", pos_group1);
		ps.printf("pos group2:%s\n", pos_group2);
		ps.printf("pos_group3:%s\n", pos_group3);
		ps.printf("ctype:%s\n", ctype);
		ps.printf("cform:%s\n", cform);
		ps.printf("orig:%s\n", orig);
		ps.printf("pronunciation:%s\n", pronunciation);
		ps.printf("accent:%d\n", accent);
		ps.printf("mora_size:%d\n", mora_size);
		ps.printf("chain_rule:%s\n", chain_rule);
		ps.printf("chain_flag:%d\n", chain_flag);
	}
	
	void print(){
		fprint(System.out);
	}
	
	void fprint(PrintStream ps){
		ps.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%d/%d,%s,%d\n",
				get_string(),get_pos(),get_pos_group1(),get_pos_group2(),get_pos_group3(),
				get_ctype(),get_cform(),get_orig(),get_read(),get_pronunciation(),get_accent(),get_mora_size(),get_chain_rule(),get_chain_flag());
	}
	
	String sprint(String split_code){
		return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%d/%d,%s,%d\n",
				string,pos,pos_group1,pos_group2,pos_group3,
				ctype,cform,orig,read,pronunciation,accent,mora_size,chain_rule,chain_flag);
	}
}
