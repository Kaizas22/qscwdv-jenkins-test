#!/bin/bash -e

pwd

PLATFORM=$1

echo "source poky/oe-init-build-env \"\""

echo $PLATFORM

echo "bitbake $PLATFORM-bundle-base -c populate_sdk"
