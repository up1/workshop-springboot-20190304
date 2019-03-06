#! /bin/bash

docker stop demo-postgres
docker rm demo-postgres
docker container run -d --name demo-postgres -p 15432:5432 -e POSTGRES_PASSWORD=password -e POSTGRES_USER=testuser postgres