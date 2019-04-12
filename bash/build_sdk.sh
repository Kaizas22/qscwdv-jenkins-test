#!/bin/bash

startpwd=$(pwd)

PLATFORMSDK=$1
SDKMACHINE=$2

#source bash/config/env_config
#source poky/oe-init-build-env ""

echo ${PLATFORMSDK}
echo ${SDKMACHINE}

cd ${startpwd}/conf
rm local.conf
cp local.conf.template local.conf
echo \"SDKMACHINE = \"${SDKMACHINE}\"\" >> local.conf
cd ..

echo "bitbake ${PLATFORMSDK} -c populate_sdk"
