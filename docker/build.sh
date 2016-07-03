#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $DIR

cp ./target/appservertest.war . &>/dev/null
cp ../target/appservertest.war . &>/dev/null

docker build \
  -f Dockerfile.derby \
  -t mrebscher/payara-derby-speedtest:latest \
  -t mrebscher/payara-derby-speedtest:4.1.1.154 \
  -t mrebscher/payara-derby-speedtest:4.1.1 \
  .

docker build \
  -f Dockerfile.oracle \
  -t mrebscher/payara-oracle-speedtest:latest \
  -t mrebscher/payara-oracle-speedtest:4.1.1.154 \
  -t mrebscher/payara-oracle-speedtest:4.1.1 \
  .

docker build \
  -f Dockerfile.postgres \
  -t mrebscher/payara-postgres-speedtest:latest \
  -t mrebscher/payara-postgres-speedtest:4.1.1.154 \
  -t mrebscher/payara-postgres-speedtest:4.1.1 \
  .
