#!/bin/sh

# Espera até que o MySQL esteja disponível
until nc -z -v -w30 mysql 3306
do
  echo "Waiting MySQL..."
  sleep 5
done

# Inicia a aplicação Spring
java -jar app.jar
