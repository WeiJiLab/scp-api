#!/bin/sh -xe

cd "$(dirname "$0")/.."

echo "Build Docker Image"
/bin/sh ./local/build-docker-image

nerdctl compose --file local/docker/docker-compose-dev.yml up -d