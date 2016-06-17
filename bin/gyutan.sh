#!/bin/sh

DOWNLOAD_DIR=$HOME/workspace

# text to synthesize
text=$DOWNLOAD_DIR/text.txt

# JAR setting
SASAKAMA_JAR=$DOWNLOAD_DIR/Sasakama/jar/Sasakama.jar

GYUTAN_HOME=$DOWNLOAD_DIR/Gyutan
GYUTAN_JAR=$GYUTAN_HOME/jar/Gyutan.jar

SEN_HOME=$GYUTAN_HOME/sen
SEN_LIB=$SEN_HOME/lib
SEN_JAR=$SEN_LIB/sen.jar:$SEN_LIB/commons-logging.jar:$SEN_LIB/junit.jar

# set classpath
CLASSPATH=$SEN_JAR:$SASAKAMA_JAR:$GYUTAN_JAR

# htsvoice
FN_VOICE=$DOWNLOAD_DIR/tohoku-f01-neutral.htsvoice

# Output setting
# wav file
WAVOUT=$DOWNLOAD_DIR/test.wav

# lab file ( without time information )
LAB=$DOWNLOAD_DIR/test.lab

# lab file ( with time information )
FLAB=$DOWNLOAD_DIR/test.full.lab



java -Dorg.apache.commons.logging.simplelog.log.net.java=info \
     -Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog  \
     -classpath ${CLASSPATH} \
     gyutan -x $SEN_HOME -m $FN_VOICE -ow $WAVOUT -ol $LAB -of $FLAB $text
