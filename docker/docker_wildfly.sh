# https://hub.docker.com/r/jboss/wildfly/
# hostname: localhost
# port: 18080
# admin-port: 19990
# To be able to place files locally and deploy you can use this command
# mkdir ~/wildfly/deployments
# docker run -p 18080:8080 -p 19990:9990 -v ~/wildfly/deployments:/opt/jboss/wildfly/standalone/deployments/ -d jboss/wildfly

# When only using remote deployments you can use this command
docker rm -f appserver-wildfly
docker run \
--name appserver-wildfly \
-p 18080:8080 \
-p 19990:9990 \
-d \
jboss/wildfly || docker start appserver-wildfly
