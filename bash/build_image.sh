#!/bin/bash -e

pwd

DEVICE=$1

echo "source poky/oe-init-build-env \"\""

echo $DEVICE

echo "bitbake $DEVICE-image-base"

if [params.SPECIAL_BUILD == true]; then
    echo "bitbake $DEVICE-image-special"
fi
