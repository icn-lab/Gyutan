set DOWNLOAD_DIR=c:\temp

set SASAKAMA_JAR=%DOWNLOAD_DIR%\Sasakama-master\Sasakama-master\jar\Sasakama.jar

set GYUTAN_HOME=%DOWNLOAD_DIR%\Gyutan-master\Gyutan-master
set GYUTAN_JAR=%GYUTAN_HOME%\jar\Gyutan.jar

set SEN_HOME=%GYUTAN_HOME%\sen
set SEN_LIB=%SEN_HOME%\lib
set SEN_JAR=%SEN_LIB%\sen.jar;%SEN_LIB%\commons-logging.jar;%SEN_LIB%\junit.jar

set SIMPLE_GUI_JAR=%DOWNLOAD_DIR%\SimpleGUI-master\SimpleGUI-master\jar\SimpleGUI.jar

set CLASSPATH=%SEN_JAR%;%SASAKAMA_JAR%;%GYUTAN_JAR%;

set FN_VOICE=./tohoku-f01-neutral.htsvoice

set WAVOUT=test.wav

# lab file ( without time information )
set LAB=test.lab

# lab file ( with time information )
set FLAB=test.full.lab

java -Dorg.apache.commons.logging.simplelog.log.net.java=info -Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog -classpath %CLASSPATH% gyutan -x %SEN_HOME% -m %FN_VOICE% -ow %WAVOUT% -ol %LAB% -of %FLAB% %text%

