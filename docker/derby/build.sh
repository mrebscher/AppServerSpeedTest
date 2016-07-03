#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $DIR

docker build \
  -t mrebscher/derby:latest \
  -t mrebscher/derby:10.12.1.1 \
  -t mrebscher/derbyt:10 \
  .
