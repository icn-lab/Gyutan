#!/bin/sh
text=text.txt

# JAR setting
DOWNLOAD_DIR=$HOME/workspace

SASAKAMA_JAR=$DOWNLOAD_DIR/Sasakama/jar/Sasakama.jar

GYUTAN_HOME=$DOWNLOAD_DIR/Gyutan
GYUTAN_JAR=$GYUTAN_HOME/jar/Gyutan.jar

SEN_HOME=$GYUTAN_HOME/sen
SEN_LIB=$SEN_HOME/lib
SEN_JAR=$SEN_LIB/sen.jar:$SEN_LIB/commons-logging.jar:$SEN_LIB/junit.jar

# set classpath
CLASSPATH=$SEN_JAR:$SASAKAMA_JAR:$GYUTAN_JAR

### htsvoice
#FN_VOICE=./nitech_jp_atr503_m001.htsvoice
FN_VOICE=./mei_happy.htsvoice

# Output setting
# wav file
WAVOUT="test.wav"

# lab file ( without time information )
LAB="test.lab"

# lab file ( with time information )
FLAB="test.full.lab"

java -Dorg.apache.commons.logging.simplelog.log.net.java=info \
     -Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog  \
     -classpath ${CLASSPATH} \
     gyutan -x $SEN_HOME -m $FN_VOICE -ow $WAVOUT -ol $LAB -of $FLAB $text
