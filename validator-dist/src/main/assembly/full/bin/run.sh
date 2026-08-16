#!/bin/sh

cd $(dirname $(readlink -f $0))/..

java -classpath .:conf:extension/*:lib/* network.oxalis.vefa.validator.dist.Cli $@