#!/bin/bash -e

pwd

PLATFORM=$1
SDKMACHINE=$2

echo "source poky/oe-init-build-env \"\""

echo $PLATFORMSDK
echo $SDKMACHINE
echo $SDKTYPE

echo "cd conf"
echo "rm local.conf"
echo "cp local.conf.template local.conf"
echo "echo \"SDKMACHINE = \"$SDKMACHINE\"\" >> local.conf"
echo "cd .."

echo "bitbake $PLATFORMSDK -c populate_sdk"
