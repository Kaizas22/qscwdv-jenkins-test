#!/bin/bash

startpwd=$(pwd)

PLATFORM=$1
DEVICE=$2

#source bash/config/env_config
#source poky/oe-init-build-env "" 

echo "bitbake ${DEVICE}-image-base"

if [[ "${DEVICE}" == "axcf2152" && "${LINUX_VERSION}" == "master" ]]; then
    echo "PREFERRED_PROVIDER_virtual/kernel = \"linux-rt-test\"" >> ${startpwd}/build/conf/local.conf
    echo "bitbake ${DEVICE}-image-test"
    sed -i "s:PREFERRED_PROVIDER_virtual/kernel\ =.*::g" ${startpwd}/build/conf/local.conf
fi

if [ ${SPECIAL_BUILD} == "true" ]; then
    if [[ "${PLATFORM}" == "rfc480" && "${LINUX_VERSION}" == "2019.0 LTS" ]]; then
        echo "bitbake ${DEVICE}-image-production"
    else
        echo "bitbake ${PLATFORM}-image-production"
    fi
fi
