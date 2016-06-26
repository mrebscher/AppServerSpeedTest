# 
# hostname: localhost
# port: 28080
# admin-port: 24848

docker rm -f appserver-glassfish4
docker run \
--name appserver-glassfish4 \
-p 23700:3700 \
-p 24848:4848 \
-p 27676:7676 \
-p 28080:8080 \
-p 28181:8282 \
-d \
glassfish:latest || docker start appserver-glassfish4
