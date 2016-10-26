groupadd atomfeed-console
sudo usermod -s /usr/sbin/nologin atomfeed-console
mkdir /opt/atomfeed-console/var
mkdir /opt/atomfeed-console/var/log

chmod +x /opt/atomfeed-console/etc/atomfeed-console
chown -R atomfeed-console:atomfeed-console /opt/atomfeed-console

sudo ln -s /opt/atomfeed-console/etc/application.yml /etc/atomfeed-console.yml
sudo ln -s /opt/atomfeed-console/etc/log4j.properties /etc/log4j.properties
sudo ln -s /opt/atomfeed-console/var/log/atomfeed-console.log /var/log/atomfeed-console.log
sudo ln -s /opt/atomfeed-console/etc/atomfeed-console /etc/init.d/atomfeed-console
sudo chkconfig --add atomfeed-console