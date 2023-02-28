#!/bin/sh -xe

cd "$(dirname "$0")/.."

echo "Build Docker Image"
/bin/sh ./local/build-docker-image

docker-compose --file local/docker/docker-compose-dev.yml up -d