#!/bin/bash

echo "pre install"
rm -rf /opt/atomfeed-console
sudo rm -rf /etc/init.d/atomfeed-console
sudo rm -rf /etc/atomfeed-console.yml
sudo rm -rf /etc/log4j.properties
sudo rm -rf /var/log/atomfeed-console.log