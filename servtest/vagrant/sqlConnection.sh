#!/usr/bin/env bash


sudo yum install -y wget
wget https://dev.mysql.com/get/mysql57-community-release-el7-8.noarch.rpm
sudo yum install -y mysql57-community-release-el7-8.noarch.rpm


sudo yum -y update
sudo yum -y install mysql-server
sudo systemctl start mysqld
sudo systemctl enable mysqld


sudo `echo [mysqld] bind-address = 0.0.0.0` > /etc/my.cnf
sudo `echo max_allowed_packet=16M` > /etc/my.cnf


mysql -u root -p `echo GRANT ALL PRIVILEGES ON dbname.* TO 'root'@'%' IDENTIFIED BY 'root'`
mysql -u root -p `echo FLUSH PRIVILEGES`
MYSQL_TEMP_PWD=`sudo cat /var/log/mysqld.log | grep 'A temporary password is generated' | awk -F'root' '{print $2}'`
mysql -u root -p`echo $MYSQL_TEMP_PWD` password 'root'
cat << EOF > .my.cnf
[client]
user=root
password=root
EOF


# sudo apt-get install build-essential zlib1g-dev git-core sqlite3 libsqlite3-dev
# sudo aptitude install mysql-server mysql-client


# sudo nano /etc/mysql/my.cnf
# change:
# bind-address            = 127.0.0.1


# mysql -u root -p

# use mysql
# GRANT ALL ON *.* to root@'3306' IDENTIFIED BY 'jarvis';
# FLUSH PRIVILEGES;
# exit


# sudo /etc/init.d/mysql restart

