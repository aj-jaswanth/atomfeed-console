groupadd atomfeed-console
useradd -g atomfeed-console atomfeed-console
mkdir /opt/atomfeedConsole/var
mkdir /opt/atomfeedConsole/var/log
chmod +x /opt/atomfeedConsole/etc/atomfeed-console
chown -R atomfeed-console:atomfeed-console /opt/atomfeedConsole
sudo ln -s /opt/atomfeedConsole/etc/atomfeed-console /etc/init.d/atomfeed-console
sudo chkconfig --add atomfeed-console
