#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $DIR

./build.sh
docker stop derby-appservertest
docker run \
  -d \
  -p 11527:1527 \
  --name derby-appservertest \
  mrebscher/derby || docker start derby-appservertest
