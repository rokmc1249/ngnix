#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/app"
JAR_FILE="$PROJECT_ROOT/spring-webapp.jar"
PROFILE_FILE="$PROJECT_ROOT/application-real1.properties"  # 또는 application-real2.properties로 변경

DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# build 파일 복사
echo "$TIME_NOW > Build 파일 복사" >> $DEPLOY_LOG
cp $PROJECT_ROOT/*.jar $JAR_FILE

# JAR 파일 실행
echo "$TIME_NOW > JAR 파일 실행" >> $DEPLOY_LOG
nohup java -jar $JAR_FILE --spring.config.location=classpath:/application.properties,classpath:/application-real1.properties > $PROJECT_ROOT/application.log 2> $PROJECT_ROOT/error.log &

CURRENT_PID=$(pgrep -f $JAR_FILE)
if [ -z "$CURRENT_PID" ]; then
  echo "$TIME_NOW > 기존 애플리케이션이 실행 중이지 않습니다." >> $DEPLOY_LOG
fi
