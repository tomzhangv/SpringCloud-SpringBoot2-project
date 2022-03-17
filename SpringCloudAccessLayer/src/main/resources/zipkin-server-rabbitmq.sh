#!/bin/bash
RABBIT_ADDRESSES=59.110.239.164:5673 RABBIT_USER=guest RABBIT_PASSWORD=guest JAVA_OPTS="-XX:ReservedCodeCacheSize=64m" nohup java -jar zipkin.jar &
