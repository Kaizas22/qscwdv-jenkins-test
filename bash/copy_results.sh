#!/bin/bash -e

pwd

MACHINE=$1
DEVICE=$2

echo $MACHINE
echo $DEVICE

if [ ! -d "result" ]; then
    mkdir result
fi
