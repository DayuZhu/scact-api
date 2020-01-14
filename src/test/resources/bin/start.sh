#!/bin/bash

JAVA_MEM_OPTS=" -Xms256m -Xmx512m -Xmn64m  -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m "

echo -e "Starting the scact-api ...\c"
nohup java $JAVA_MEM_OPTS -jar scact-api.jar --spring.profiles.active=dev --server.port=8623 > /dev/null 2>&1 &

#java -Xms256m -Xmx512m -Xmn64m  -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -jar scact-api.jar --spring.profiles.active=dev --server.port=8623