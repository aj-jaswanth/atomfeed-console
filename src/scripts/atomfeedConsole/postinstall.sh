#!/bin/bash

set -x

USER=atomfeed-console
GROUP=atomfeed-console

/bin/id -g ${GROUP} 2>/dev/null
if [ $? -eq 1 ]; then
    groupadd ${GROUP}
fi

/bin/id ${USER} 2>/dev/null
if [ $? -eq 1 ]; then
    useradd -g ${USER} ${USER}
fi

usermod -s /usr/sbin/nologin atomfeed-console

mkdir -p /opt/atomfeed-console/var/log/
mkdir /etc/atomfeed-console/
mkdir /var/log/atomfeed-console/

chown -R atomfeed-console:atomfeed-console /opt/atomfeed-console/
chmod +x /opt/atomfeed-console/bin/atomfeed-console

ln -sf /opt/atomfeed-console/etc/application.yml /etc/atomfeed-console/atomfeed-console.yml
ln -sf /opt/atomfeed-console/etc/log4j.properties /etc/atomfeed-console/log4j.properties
ln -sf /opt/atomfeed-console/var/log/atomfeed-console.log /var/log/atomfeed-console/atomfeed-console.log
ln -sf /opt/atomfeed-console/bin/atomfeed-console /etc/init.d/atomfeed-console

chkconfig --add atomfeed-console