#!/usr/bin/env bash
IMAGE_NAME=correction-test-java
TAG=latest

docker build --file ./Dockerfile --tag "$IMAGE_NAME:$TAG" .
