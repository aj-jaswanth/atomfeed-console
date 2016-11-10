#!/bin/bash

nohup java -jar /opt/atomfeed-console/bin/atomfeed-console.jar --spring.config.location=/etc/atomfeed-console/atomfeed-console.yml >> /var/log/atomfeed-console/atomfeed-console.log 2>&1 &
