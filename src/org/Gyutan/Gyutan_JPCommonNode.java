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

public class Gyutan_JPCommonNode {
	String pronunciation;
	String pos;
	String ctype;
	String cform;
	int accent;
	int chain_flag;
	Gyutan_JPCommonNode prev;
	Gyutan_JPCommonNode next;
	
	Gyutan_JPCommonNode(){
		initialize();
	}
	
	void initialize(){
		pronunciation = null;
		pos = null;
		ctype = null;
		cform = null;
		accent = 0;
		chain_flag = -1;
		prev = null;
		next = null;
	}
	
	void set_pronunciation(String str){
		pronunciation = str;
	}
	
	void set_pos(String str){
		pos = str;
	}
	
	void set_ctype(String str){
		ctype = str;
	}
	
	void set_cform(String str){
		cform = str;
	}
	
	void set_accent(int acc){
		accent = acc;
	}
	
	void set_chain_flag(int flag){
		chain_flag = flag;
	}
	
	String get_pronunciation(){
		return pronunciation;
	}
	
	String get_pos(){
		return pos;
	}
	
	String get_ctype(){
		return ctype;
	}
	
	String get_cform(){
		return cform;
	}
	
	int get_accent(){
		return accent;
	}
	
	int get_chain_flag(){
		return chain_flag;
	}
	
	void copy(Gyutan_JPCommonNode node){
		set_pronunciation(node.pronunciation);
		set_pos(node.pos);
		set_ctype(node.ctype);
		set_cform(node.cform);
		set_accent(node.accent);
		set_chain_flag(node.chain_flag);
	}
	
	void print(){
		fprint(System.out);
	}
	
	void fprint(PrintStream ps){ 
		ps.printf("--- node ---\n");
		ps.printf("%s,%s,%s,%s,%d,%d\n",
				pronunciation,pos,ctype,cform,accent,chain_flag);
		ps.printf("--- END ---\n");

	} 
}
