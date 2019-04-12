#!/bin/bash

startpwd=$(pwd)

PLATFORMSDK=$1
SDKMACHINE=$2

#source bash/config/env_config
#source poky/oe-init-build-env ""

echo ${PLATFORMSDK}
echo ${SDKMACHINE}

rm ${startpwd}/build/conf/local.conf
cp ${startpwd}/build/conf/local.conf.template ${startpwd}/build/conf/local.conf
echo \"SDKMACHINE = \"${SDKMACHINE}\"\" >> ${startpwd}/build/conflocal.conf

echo "bitbake ${PLATFORMSDK} -c populate_sdk"
