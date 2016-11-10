#!/bin/bash

set -x

service atomfeed-console stop || true

rm -rf /opt/atomfeed-console/
rm -f /etc/init.d/atomfeed-console
rm -rf /etc/atomfeed-console/
rm -rf /var/log/atomfeed-console/