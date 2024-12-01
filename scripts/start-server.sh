#!/bin/bash

echo "--------------- 서버 배포 시작 -----------------"
cd /home/ubuntu/blog-server

# 현재 실행 중인 포트 확인 (8081 또는 8082)
CURRENT_PORT=$(sudo lsof -ti:8081 -sTCP:LISTEN)
if [ -z "$CURRENT_PORT" ]; then
  CURRENT_PORT=$(sudo lsof -ti:8082 -sTCP:LISTEN)
  if [ -z "$CURRENT_PORT" ]; then
      NEXT_PORT=8081 # 둘 다 실행 중이 아니면 8081 사용
  else
      NEXT_PORT=8081
  fi
else
  NEXT_PORT=8082
fi


echo "현재 포트: $CURRENT_PORT, 다음 포트: $NEXT_PORT"


# 새 포트로 서버 실행
nohup java -jar project.jar --server.port=$NEXT_PORT > ./output_$NEXT_PORT.log 2>&1 &

# 기존 서버 종료 (새 서버가 완전히 실행된 후)
if [ ! -z "$CURRENT_PORT" ]; then
    sleep 20 # 새 서버가 시작될 때까지 기다림 (필요에 따라 조정)
    sudo kill -9 $CURRENT_PORT || true
fi

echo "--------------- 서버 배포 끝 -----------------"