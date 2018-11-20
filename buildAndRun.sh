#!/bin/sh
mvn clean package && docker build -t es.eprinsa.eprRedmine/eprredmine .
docker rm -f eprredmine || true && docker run -d -p 8080:8080 -p 4848:4848 --name eprredmine es.eprinsa.eprRedmine/eprredmine 
