# https://hub.docker.com/_/postgres/
# hostname: localhost
# port: 35432
# database: postgres 
# username: postgres
# password: ApplicationServerSpeedTest

docker run \
--name appserver-postgres \
-p 35432:5432 \
-e POSTGRES_PASSWORD=ApplicationServerSpeedTest \
-d \
postgres || docker start appserver-postgres
