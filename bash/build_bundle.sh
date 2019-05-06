#!/bin/bash

startpwd=$(pwd)

PLATFORM=$1
DEVICE=$2

#source bash/config/env_config
#source poky/oe-init-build-env ""

if [[  "${LINUX_VERSION}" == "2019.0 LTS" ]]; then
    if [ "${PLATFORM}" == "axcf2152" ]; then
        echo "bitbake ${PLATFORM}-image-base-bundle"
    fi
    if [ "${PLATFORM}" == "rfc480" ]; then
        echo "bitbake ${DEVICE}-image-base-bundle"
    fi
fi

if [ "${LINUX_VERSION}" != "2019.0 LTS" ]; then
    echo "bitbake ${PLATFORM}-bundle-base"
fi

if [[ "${PLATFORM}" == "axcf2152" && "${LINUX_VERSION}" == "master" ]]; then
    echo "PREFERRED_PROVIDER_virtual/kernel = \"linux-rt-test\"" >> ${startpwd}/build/conf/local.conf
    echo "bitbake ${PLATFORM}-bundle-test"
    sed -i "s:PREFERRED_PROVIDER_virtual/kernel\ =.*::g" ${startpwd}/build/conf/local.conf
fi
