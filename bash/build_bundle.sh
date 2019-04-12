#!/bin/bash

startpwd=$(pwd)

PLATFORM=$1

#source bash/config/env_config
#source poky/oe-init-build-env ""

if [[ "${PLATFORM}" == "axcf2152" && "${LINUX_VERSION}" == "2019.0 LTS" ]]; then
    echo "bitbake ${PLATFORM}-image-base-bundle"
fi

if [[ "${PLATFORM}" == "axcf2152" && "${LINUX_VERSION}" != "2019.0 LTS" ]]; then
    echo "bitbake ${PLATFORM}-bundle-base"
fi

if [[ "${PLATFORM}" == "axcf2152" && "${LINUX_VERSION}" == "master" ]]; then
    echo "PREFERRED_PROVIDER_virtual/kernel = \"linux-rt-test\"" >> ${startpwd}/build/conf/local.conf
    echo "bitbake ${PLATFORM}-bundle-test"
    sed -i "s:PREFERRED_PROVIDER_virtual/kernel\ =.*::g" ${startpwd}/build/conf/local.conf
fi

if [[ "${PLATFORM}" == "rfc480" && "${LINUX_VERSION}" == "2019.0 LTS" ]]; then
    echo "bitbake ${DEVICE}-image-base-bundle"
fi

if [[ "${PLATFORM}" == "rfc480" && "${LINUX_VERSION}" != "2019.0 LTS" ]]; then
    echo "bitbake ${PLATFORM}-bundle-base"
fi
