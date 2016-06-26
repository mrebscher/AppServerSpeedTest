# https://hub.docker.com/r/jboss/wildfly/
# To be able to place files locally and deploy you can use this command
# mkdir ~/wildfly/deployments
# docker run -p 18080:8080 -p 9990:9990 -v ~/wildfly/deployments:/opt/jboss/wildfly/standalone/deployments/ -d jboss/wildfly

# For remote deployments only you can use this command
docker run -p 18080:8080 -p 9990:9990 -d jboss/wildfly
