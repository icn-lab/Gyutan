y# Gyutan
Copyright (c) 2015 Intelligent Communication Network (Ito-Nose) Laboratory Tohoku University.   
Copyright (c) 2001-2015 Nagoya Institute of Technology Department of Computer Science.   
All rights reserved.  

Gyutan is a language translation version of "Open JTalk" developed by HTS Working Group.
Gyutan is a Japanese Text-to-Speech software implemented by Java.
Thanks to HTS Working Group, speech synthesis can be used easier.

## How to synthesize
Gyutan needs Sasakama(Sasakama.jar).
See also [Sasakama repository](https://github.com/icn-lab/Sasakama).

To synthesize speech from Japanese text, you must prepare speech model HMM (HTS voice) .

You can get HTS voice below.
* from our repository : <https://github.com/icn-lab/htsvoice-tohoku-f01>
* from HTS Working Group : <http://open-jtalk.sourceforge.net/>
* from MMDAgent Sample : <http://www.mmdagent.jp/>

Japanese morphological analyzer is needed to analyze Japanese text.
And to determine accent	type of	synthesized speech, accent dictionary is needed.
Sen, java implemented Japanese morphological analyzer, is used in this software.
And naist-jdic(ChaSen format) is used as dictionary.
Accent information is needed to synthesize speech.
Accent information is extracted from naist-jdic(MeCab format) in the Open JTalk package(1.08), and added to naist-jdic(ChaSen format)

## How to use
See bin/gyutan.sh(or bin/gyutan.bat on Windows).
You need to define shell variables of each JARs and dictionary.

### Link
* "Open JTalk" developed by HTS Working Group : <http://open-jtalk.sourceforge.net>
* Sen, Japanese morphological analyzer implement by Java : <https://java.net/projects/sen>
* naist-jdic (for chasen) : <https://osdn.jp/projects/naist-jdic/>

### What is Gyutan?
Gyutan is a cow's tongue.
Gyutan is a famous food in Sendai city, Japan (Our laboratory is in there).
