FROM airhacks/glassfish
COPY ./target/eprredmine.war ${DEPLOYMENT_DIR}
