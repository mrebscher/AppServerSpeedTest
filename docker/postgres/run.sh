#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $DIR

# ./build.sh
docker stop postgres-appservertest
docker run \
  --name postgres-appservertest \
  -p 15432:5432 \
  -e POSTGRES_PASSWORD=appservertest \
  -e POSTGRES_USER=appservertest \
  -e POSTGRES_DB=appservertestdb \
  -d \
  postgres || docker start postgres-appservertest
