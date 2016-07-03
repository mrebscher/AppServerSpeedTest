#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $DIR


# https://hub.docker.com/r/wnameless/oracle-xe-11g/
# hostname: localhost
# port: 11521
# sid: xe
# username: system
# password: oracle
docker stop oracle-appservertest
docker run \
--name oracle-appservertest \
-p 11521:1521 \
-e ORACLE_ALLOW_REMOTE=true \
-d \
wnameless/oracle-xe-11g || docker start oracle-appservertest
