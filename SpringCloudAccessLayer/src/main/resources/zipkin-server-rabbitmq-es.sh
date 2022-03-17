#!/bin/bash
RABBIT_ADDRESSES=59.110.239.164:5673 RABBIT_USER=guest RABBIT_PASSWORD=guest STORAGE_TYPE=elasticsearch ES_HOSTS=http://59.110.239.164:9200 ES_INDEX=zipkin ES_INDEX_SHARDS=5 ES_INDEX_REPLICAS=1 JAVA_OPTS="-XX:ReservedCodeCacheSize=64m" nohup java -jar zipkin.jar &



#set RABBIT_ADDRESSES=59.110.239.164:5673
#set RABBIT_USER=guest
#set RABBIT_PASSWORD=guest
#set STORAGE_TYPE=mysql
#set MYSQL_HOST=192.168.4.110
#set MYSQL_TCP_PORT=3306
#set MYSQL_USER=root
#set MYSQL_PASS=hdxy123
#set MYSQL_DB=zipkin
#set MYSQL_USE_SSL=false

#set STORAGE_TYPE=elasticsearch
#set ES_HOSTS=http://59.110.239.164:9200
#set ES_INDEX=zipkin
#set ES_INDEX_SHARDS=5
#set ES_INDEX_REPLICAS=1


#set JAVA_OPTS="-XX:ReservedCodeCacheSize=64m" 
#nohup java -jar zipkin.jar &
