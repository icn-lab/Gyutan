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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Gyutan_NJD {
	Gyutan_NJDNode head;
	Gyutan_NJDNode tail;
	
	Gyutan_NJD(){
		initialize();
	}
	
	Gyutan_NJD(String[] feature){
		initialize();
		for(int i=0;i < feature.length;i++){
			Gyutan_NJDNode node = new Gyutan_NJDNode();
			node.load(feature[i]);
			push_node(node);
		}
	}
	
	private int get_token_from_string(String str, int[] index, StringBuilder buff, char d){
		buff.delete(0,  buff.length());
		char[] charat = str.toCharArray();
		
		if(index[0] == str.length())
			return 0;
		
		//char c = str.charAt(index[0]);
		char c = charat[index[0]];
		if(c == d){
			index[0]++;
			return 0;
		}
		
		while(c == '\n' || c == '\r' || c == '\t'){
			index[0]++;
			if(index[0] == str.length())
				return 0;
			//c = str.charAt(index[0]);
			c = charat[index[0]];
		}
		
		int i;
		for(i = 0;c != d && c != '\n' && c != 'r' && c != '\t';i++){
			buff.append(c);
			index[0]++;
			if(index[0] < str.length())
				//c = str.charAt(index[0]);
				c = charat[index[0]];
		}
		if(c == d)
			index[0]++;
		
		return i;
	}
	
	private int get_token_from_file(FileInputStream fis, StringBuilder buff, char d){
		buff.delete(0, buff.length());
		byte[] b = new byte[1];

		int ret = 0;
		try {
			ret = fis.read(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(ret == -1)
			return 0;
		
		char c = (char)b[0];
		if(c == d){
			return 0;
		}
		
		while(c=='\n' || c == '\r' || c == '\t'){
			try {
				ret = fis.read(b);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(ret == -1)
				return 0;
			c = (char)b[0];
		}
		
		int i;
		for(i = 0;c != d && c != '\n' && c != '\r' && c != '\t' && ret != -1;i++){
			buff.append(c);
			try {
				ret = fis.read(b);
			} catch (IOException e) {
				e.printStackTrace();
			}
			c = (char)b[0];
		}
		
		return i;
	}
	
	void initialize(){
		head = null;
		tail = null;
	}
	
	void load(String str){
		StringBuilder string     = new StringBuilder();
		StringBuilder pos        = new StringBuilder();
		StringBuilder pos_group1 = new StringBuilder();
		StringBuilder pos_group2 = new StringBuilder();
		StringBuilder pos_group3 = new StringBuilder();
		StringBuilder ctype      = new StringBuilder();
		StringBuilder cform      = new StringBuilder();

		StringBuilder orig       = new StringBuilder();
		StringBuilder read       = new StringBuilder();
		StringBuilder pron       = new StringBuilder();
		StringBuilder accent     = new StringBuilder();
		StringBuilder mora_size  = new StringBuilder();
		StringBuilder chain_rule = new StringBuilder();
		StringBuilder chain_flag = new StringBuilder();
		
		int[] index =  new int[1];
		index[0] = 0;

		while(true){
			get_token_from_string(str, index, string, ',');
			if(get_token_from_string(str, index, pos, ',') <= 0)
				break;
			if(get_token_from_string(str, index, pos_group1, ',') <= 0)
				break;
			if(get_token_from_string(str, index, pos_group2, ',') <= 0)
				break;
			if(get_token_from_string(str, index, pos_group3, ',') <= 0)
				break;
			if(get_token_from_string(str, index, ctype, ',') <= 0)
				break;
			if(get_token_from_string(str, index, cform, ',') <= 0)
				break;
			get_token_from_string(str, index, orig, ',');
			get_token_from_string(str, index, read, ',');
			get_token_from_string(str, index, pron, ',');
			if(get_token_from_string(str, index, accent, ',') <= 0)
				break;
			if(get_token_from_string(str, index, mora_size, ',') <= 0)
				break;
			get_token_from_string(str, index, chain_rule, ',');
			if(get_token_from_string(str, index, chain_flag, ',') <= 0)
				break;
			
			Gyutan_NJDNode node = new Gyutan_NJDNode();
			node.set_string(string.toString());
			node.set_pos(pos.toString());
			node.set_pos_group1(pos_group1.toString());
			node.set_pos_group2(pos_group2.toString());
			node.set_pos_group3(pos_group3.toString());
			node.set_ctype(ctype.toString());
			node.set_cform(cform.toString());
			node.set_orig(orig.toString());
			node.set_read(read.toString());
			node.set_pronunciation(pron.toString());
			node.set_accent(Integer.parseInt(accent.toString()));
			node.set_mora_size(Integer.parseInt(mora_size.toString()));
			node.set_chain_rule(chain_rule.toString());
			node.set_chain_flag(Integer.parseInt(chain_flag.toString()));
			push_node(node);
		}
	}
	
	void load_from_file(String str, FileInputStream fis){
		if(fis == null){
			System.err.println("WARNING: NJD.load_from_file: FileInputStream should not be null.");
			return;
		}
		
		StringBuilder string     = new StringBuilder();
		StringBuilder pos        = new StringBuilder();
		StringBuilder pos_group1 = new StringBuilder();
		StringBuilder pos_group2 = new StringBuilder();
		StringBuilder pos_group3 = new StringBuilder();
		StringBuilder ctype      = new StringBuilder();
		StringBuilder cform      = new StringBuilder();

		StringBuilder orig       = new StringBuilder();
		StringBuilder read       = new StringBuilder();
		StringBuilder pron       = new StringBuilder();
		StringBuilder accent     = new StringBuilder();
		StringBuilder mora_size  = new StringBuilder();
		StringBuilder chain_rule = new StringBuilder();
		StringBuilder chain_flag = new StringBuilder();
		
		while(true){
			get_token_from_file(fis, string, ',');
			if(get_token_from_file(fis, pos, ',') <= 0)
				break;
			if(get_token_from_file(fis, pos_group1, ',') <= 0)
				break;
			if(get_token_from_file(fis, pos_group2, ',') <= 0)
				break;
			if(get_token_from_file(fis, pos_group3, ',') <= 0)
				break;
			if(get_token_from_file(fis, ctype, ',') <= 0)
				break;
			if(get_token_from_file(fis, cform, ',') <= 0)
				break;
			get_token_from_file(fis, orig, ',');
			get_token_from_file(fis, read, ',');
			get_token_from_file(fis, pron, ',');
			if(get_token_from_file(fis, accent, ',') <= 0)
				break;
			if(get_token_from_file(fis, mora_size, ',') <= 0)
				break;
			get_token_from_file(fis, chain_rule, ',');
			if(get_token_from_file(fis, chain_flag, ',') <= 0)
				break;
			
			Gyutan_NJDNode node = new Gyutan_NJDNode();
			node.set_string(string.toString());
			node.set_pos(pos.toString());
			node.set_pos_group1(pos_group1.toString());
			node.set_pos_group2(pos_group2.toString());
			node.set_pos_group3(pos_group3.toString());
			node.set_ctype(ctype.toString());
			node.set_cform(cform.toString());
			node.set_orig(orig.toString());
			node.set_read(read.toString());
			node.set_pronunciation(pron.toString());
			node.set_accent(Integer.parseInt(accent.toString()));
			node.set_mora_size(Integer.parseInt(mora_size.toString()));
			node.set_chain_rule(chain_rule.toString());
			node.set_chain_flag(Integer.parseInt(chain_flag.toString()));
			this.push_node(node);
		}
	}
	
	int get_size(){
		Gyutan_NJDNode node = head;
		int i;
		for(i=0; node != null; i++)
			node = node.next;

		return i;
	}
	
	void push_node(Gyutan_NJDNode node){
		if(head == null){
			head = node;
		}
		else{
			tail.next = node;
			node.prev = tail;
		}
		
		while(node.next != null)
			node = node.next;
		tail = node;
	}
	
	Gyutan_NJDNode remove_node(Gyutan_NJDNode node){
		Gyutan_NJDNode next = null;
		
		if(node == head && node == tail){
			head = null;
			tail = null;
			next = null;
		}
		else if(node == head){
			head = node.next;
			head.prev = null;
			next = head;
		}
		else if(node == tail){
			tail = node.prev;
			tail.next = null;
			next = null;
		}
		else{
			node.prev.next = node.next;
			node.next.prev = node.prev;
			next = node.next;
		}
		
		return next;
	}
	
	void remove_silent_node(){
		for(Gyutan_NJDNode node = head;node != null;)
			if(node.get_pronunciation().equals("*") == true)
				node = remove_node(node);
			else
				node = node.next;
	}
	
	void print(){
		fprint(System.out);
	}
	
	void fprint(PrintStream ps){
		
		for(Gyutan_NJDNode node = head; node != null; node = node.next)
			node.print();
	}
	
	String sprint(String split_code){
		StringBuilder str = new StringBuilder();
		
		for(Gyutan_NJDNode node = head;node != null; node = node.next)
			str.append(node.sprint(split_code));
		
		return str.toString();
	}
	
	void refresh(){
		initialize();
	}
}
