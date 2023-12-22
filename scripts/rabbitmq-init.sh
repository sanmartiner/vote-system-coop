#!/bin/bash

set -e
#
rabbitmq-server -detached

#Wait unitl rabbit avaiable
until rabbitmqctl status >/dev/null 2>&1
do
  echo "Waiting for RabbitMQ to start..."
  sleep 5
done

# Create qeue exchange with bind
rabbitmqadmin -u developer -p dev123 declare queue name=voting.result durable=true
rabbitmqadmin -u developer -p dev123 declare exchange name=voting.exchange type=direct
rabbitmqadmin -u developer -p dev123 declare binding source=voting.exchange destination_type=queue destination=voting.result routing_key=routing

# Keep script runing
tail -f /dev/null