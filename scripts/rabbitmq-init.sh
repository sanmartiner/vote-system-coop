#!/bin/bash

set -e

# Espera até que o RabbitMQ esteja disponível
until nc -z -v -w30 rabbitmq 5672
do
  echo "Waiting RabbitMQ start..."
  sleep 5
done

sleep 10

# Comandos para criar a fila e a exchange
rabbitmqadmin -u developer -p dev123 declare queue name=voting.result durable=true
rabbitmqadmin -u developer -p dev123 declare exchange name=voting.exchange type=direct
rabbitmqadmin -u developer -p dev123 declare binding source=voting.exchange destination_type=queue destination=voting.result routing_key=routing-key

