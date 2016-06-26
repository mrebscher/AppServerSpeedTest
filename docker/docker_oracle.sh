# https://hub.docker.com/r/wnameless/oracle-xe-11g/
# hostname: localhost
# port: 49161
# sid: xe
# username: system
# password: oracle

docker run -d -p 49160:22 -p 49161:1521 -e ORACLE_ALLOW_REMOTE=true wnameless/oracle-xe-11g

