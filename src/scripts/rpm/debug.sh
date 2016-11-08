#!/bin/bash

DEBUG_OPTS="-agentlib:jdwp=transport=dt_socket,address=8008,server=y,suspend=n"

nohup java -jar ${DEBUG_OPTS} /opt/atomfeed-console/bin/atomfeed-console-1.0-SNAPSHOT.jar --spring.config.location=/etc/atomfeed-console/atomfeed-console.yml >> /var/log/atomfeed-console/atomfeed-console.log 2>&1 &
