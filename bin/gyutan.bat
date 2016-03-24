set DOWNLOAD_DIR=c:\temp

REM text to synthesize
set text=%DOWNLOAD_DIR%\test.txt

set SASAKAMA_JAR=%DOWNLOAD_DIR%\Sasakama-master\Sasakama-master\jar\Sasakama.jar

set GYUTAN_HOME=%DOWNLOAD_DIR%\Gyutan-master\Gyutan-master
set GYUTAN_JAR=%GYUTAN_HOME%\jar\Gyutan.jar

set SEN_HOME=%GYUTAN_HOME%\sen
set SEN_LIB=%SEN_HOME%\lib
set SEN_JAR=%SEN_LIB%\sen.jar;%SEN_LIB%\commons-logging.jar;%SEN_LIB%\junit.jar

set CLASSPATH=%SEN_JAR%;%SASAKAMA_JAR%;%GYUTAN_JAR%;

REM voice using synthesize
set FN_VOICE=%DOWNLOAD_DIR%\tohoku-f01-neutral.htsvoice

set WAVOUT=%DOWNLOAD_DIR%\test.wav

REM lab file ( without time information )
set LAB=%DOWNLOAD_DIR%\test.lab

REM lab file ( with time information )
set FLAB=%DOWNLOAD_DIR%\test.full.lab

java -Dorg.apache.commons.logging.simplelog.log.net.java=info -Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog -classpath %CLASSPATH% gyutan -x %SEN_HOME% -m %FN_VOICE% -ow %WAVOUT% -ol %LAB% -of %FLAB% %text%
