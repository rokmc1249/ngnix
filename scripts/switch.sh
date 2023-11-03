#!/usr/bin/env bash
CURRENT_PORT=$(cat /etc/nginx/conf.d/service-url.inc | grep -Po '[0-9]+' | tail -1)
TARGET_PORT=0

echo "Nginx currently proxies to ${CURRENT_PORT}."

if [ ${CURRENT_PORT} -eq 8089 ]; then
  TARGET_PORT=8090
elif [ ${CURRENT_PORT} -eq 8090 ]; then
  TARGET_PORT=8089
else
  echo "No WAS is connected to nginx"
  exit 1
fi

echo "set \$service_url http://127.0.0.1:${TARGET_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc

echo "Now Nginx proxies to ${TARGET_PORT}."
sudo service nginx reload

echo "Nginx reloaded."