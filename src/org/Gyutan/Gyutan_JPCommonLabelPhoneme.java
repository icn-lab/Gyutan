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

public class Gyutan_JPCommonLabelPhoneme {
	static final String unvoice_list[] = {
		"a", "A",
		"i", "I",
		"u", "U",
		"e", "E",
		"o", "O"
		};

	String phoneme;
	Gyutan_JPCommonLabelPhoneme prev;
	Gyutan_JPCommonLabelPhoneme next;
	Gyutan_JPCommonLabelMora up;

	void initialize(String phoneme, Gyutan_JPCommonLabelPhoneme prev, Gyutan_JPCommonLabelPhoneme next, Gyutan_JPCommonLabelMora up){
		//System.err.printf("#### phoneme:%s\n", phoneme);
		this.phoneme = phoneme;
		this.prev = prev;
		this.next = next;
		this.up = up;
	}
	
	void convert_unvoice(){
		for(int i=0;i < unvoice_list.length;i+=2){
			if(phoneme.equals(unvoice_list[i]) == true){
				phoneme = unvoice_list[i+1];
				return;
			}
		}
		
		System.err.printf("WARNING: JPCommonLabelPhoneme.convert_unvoice(): %s cannot be unvoiced.", phoneme);
	}
	
	
}
