#!/bin/bash
#the conf name,please make it not wrong!
cd `dirname $0`
APP_HOME=`pwd`
CONF_FILE=$APP_HOME/config/setenv.conf
#check CONF_FILE exist
if [[ ! -f "$CONF_FILE" ]]
then
   echo "(`pwd`) is not exist $CONF_FILE  file ,please check it!"
   exit 1
fi
#read CONF_FILE
. $CONF_FILE

#start Service
#!/bin/sh
#....Linux...java..................service......
#.......java...........
#
#Author: tudaxia.com, Date: 2011/6/7
#
#..!!!....stop......kill..........java.....
#........................................
#......................................
#.....kill..........
#
#
###################################
#...........
#..........Java...........
###################################
#JDK....
#check JAVA_HOME
if [ X$JAVA_HOME = X ]
    then
      echo "$CONF_FILE  file  is not set JAVA_HOME variable,please set it!"
	  exit 1
    else
      echo  "JAVA_HOME is $JAVA_HOME!"
fi

#check APP_MAINCLASS
if [ X$APP_MAINCLASS = X ]
    then
      echo "$CONF_FILE  file  is not set APP_MAINCLASS variable,please set it!"
	  echo ""
	  exit 1
    else
      echo "APP_MAINCLASS is $APP_MAINCLASS!"
fi

#check LOG_HOME
if [ X$LOG_HOME = X ]
    then
      echo "$CONF_FILE  file  is not set LOG_HOME variable,please set it!"
	  exit 1
    else
      echo  "LOG_HOME is $LOG_HOME!"
fi

#check JAVA_OPTS
if [ X"$JAVA_OPTS" = X ]
    then
      echo "$CONF_FILE  file  is not set JAVA_OPTS variable,please set it!"
	  exit 1
    else
      echo  "JAVA_OPTS is $JAVA_OPTS!"
fi



export STDOUT_LOG=$LOG_HOME/STDOUT.log
mkdir -p $LOG_HOME/
#check STDOUT_LOG exist
if [[ ! -f "$STDOUT_LOG" ]]
then
touch $STDOUT_LOG
fi
#.....classpath.......lib......jar
CLASSPATH=$APP_HOME
for i in "$APP_HOME"/lib/*.*; do
CLASSPATH="$CLASSPATH":"$i"
done
CLASSPATH=$CLASSPATH:"$APP_HOME"/config:"$APP_HOME"/config/logback.xml

#java.......


#echo "JAVA_OPTS is $JAVA_OPTS"

###################################
#(..).........
#
#...
#..JDK...JPS...grep.........pid
#jps . l .......java......
#..awk....pid ($1..)..Java....($2..)
###################################
#...psid......
psid=0

checkpid() {
javaps=`$JAVA_HOME/bin/jps -l | grep $APP_MAINCLASS`

if [ -n "$javaps" ]; then
psid=`echo $javaps | awk '{print $1}'`
else
psid=0
fi
}

###################################
#(..)....
#
#...
#1. ....checkpid.....$psid....
#2. .........$psid...0..........
#3. ..................
#4. ............checkpid..
#5. ....4..........pid,...[OK].....[Failed]
#...echo -n ...........
#..: "nohup ... >/dev/null 2>&1 &" ...
###################################
start() {
checkpid

if [ $psid -ne 0 ]; then
echo "================================"
echo "warn: $APP_MAINCLASS already started! (pid=$psid)"
echo "================================"
else
echo -n "Starting $APP_MAINCLASS ..."
echo "nohup $JAVA_HOME/bin/java $JAVA_OPTS -classpath $CLASSPATH $APP_MAINCLASS 2>&1 >> $STDOUT_LOG  &";
nohup $JAVA_HOME/bin/java $JAVA_OPTS -classpath $CLASSPATH $APP_MAINCLASS >>$STDOUT_LOG 2>&1  &
checkpid
if [ $psid -ne 0 ]; then
echo "(pid=$psid) [OK]"
else
echo "[Failed]"
fi
fi
}

###################################
#(..)....
#
#...
#1. ....checkpid.....$psid....
#2. .........$psid...0....................
#3. ..kill -9 pid..........
#4. ..kill.....................: $?
#5. ....4...$?..0,...[OK].....[Failed]
#6. ....java...............................stop..
#...echo -n ...........
#..: .shell...."$?" .................
###################################
stop() {
checkpid

if [ $psid -ne 0 ]; then
echo -n "Stopping $APP_MAINCLASS ...(pid=$psid) "
kill -9 $psid
if [ $? -eq 0 ]; then
echo "[OK]"
else
echo "[Failed]"
fi

checkpid
if [ $psid -ne 0 ]; then
stop
fi
else
echo "================================"
echo "warn: $APP_MAINCLASS is not running"
echo "================================"
fi
}

###################################
#(..)........
#
#...
#1. ....checkpid.....$psid....
#2. .........$psid...0.............pid
#3. ..........
###################################
status() {
checkpid

if [ $psid -ne 0 ]; then
echo "$APP_MAINCLASS is running! (pid=$psid)"
else
echo "$APP_MAINCLASS is not running"
fi
}

###################################
#(..)........
###################################
info() {
echo "System Information:"
echo "****************************"
echo `head -n 1 /etc/issue`
echo `uname -a`
echo
echo "JAVA_HOME=$JAVA_HOME"
echo `$JAVA_HOME/bin/java -version`
echo
echo "APP_HOME=$APP_HOME"
echo "APP_MAINCLASS=$APP_MAINCLASS"
echo "****************************"
}

###################################
#..........($1).....
#.......{start|stop|restart|status|info}
#...................
###################################
case "$1" in
'start')
start
;;
'stop')
stop
;;
'restart')
stop
start
;;
'status')
status
;;
'info')
info
;;
*)
echo "Usage: $0 {start|stop|restart|status|info}"
exit 1
esac
exit 0
