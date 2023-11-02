CURRENT_PORT=$(cat /etc/nginx/conf.d/service-url.inc | grep -Po '[0-9]+' | tail -1)
 TARGET_PORT=0

 if [ ${CURRENT_PORT} -eq 8089 ]; then
   TARGET_PORT=8090
 elif [ ${CURRENT_PORT} -eq 8090 ]; then
   TARGET_PORT=8089
 else
   echo "[$NOW_TIME] No WAS is connected to nginx"
 fi

 TARGET_PID=$(lsof -Fp -i TCP:${TARGET_PORT} | grep -Po 'p[0-9]+' | grep -Po '[0-9]+')

 if [ ! -z ${TARGET_PID} ]; then
   echo "Kill WAS running at ${TARGET_PORT}."
   sudo kill ${TARGET_PID}
 fi

 nohup java -jar -Dserver.port=${TARGET_PORT} /home/ubuntu/application/*.jar
 echo "Now new WAS runs at ${TARGET_PORT}."

 exit 0