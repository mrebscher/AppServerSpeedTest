# https://hub.docker.com/_/postgres/
# hostname: localhost
# port: 5432
# database: postgres 
# username: postgres
# password: ApplicationServerSpeedTest

docker run --name appserver-postgres -p 5432:5432 -e POSTGRES_PASSWORD=ApplicationServerSpeedTest -d postgres
