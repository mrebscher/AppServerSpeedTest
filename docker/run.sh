#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $DIR

./build.sh
docker rm -f payara-derby-speedtest
docker rm -f payara-oracle-speedtest
docker rm -f payara-postgres-speedtest

if [ "$(docker ps | grep derby-appservertest)" != "" ]; then
  docker run \
    -d \
    -p 30000:8080 \
    -p 30001:4848 \
    --link derby-appservertest:derby \
    --name payara-derby-speedtest \
    mrebscher/payara-derby-speedtest
fi
if [ "$(docker ps | grep oracle-appservertest)" != "" ]; then
  docker run \
    -d \
    -p 30002:8080 \
    -p 30003:4848 \
    --link oracle-appservertest:oracle \
    --name payara-oracle-speedtest \
    mrebscher/payara-oracle-speedtest
fi
if [ "$(docker ps | grep postgres-appservertest)" != "" ]; then
  POSTGRESLINK=""
  docker run \
    -d \
    -p 30004:8080 \
    -p 30005:4848 \
    --link postgres-appservertest:postgres \
    --name payara-postgres-speedtest \
    mrebscher/payara-postgres-speedtest
fi
