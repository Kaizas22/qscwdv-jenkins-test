#!/bin/bash -e

startpwd=$(pwd)

#source bash/config/env_config

SVN_REV=$1
MACHINE=$2
DEVICE=$3

echo "svn: "${SVN_REV}
echo "machine: "${MACHINE}
echo "device: "${DEVICE}
echo "linux version: "${LINUX_VERSION}
echo "build sdk: "${BUILD_SDK}
echo "build kernel sdk: "${BUILD_KERNEL_SDK}
echo "fw svn branch: "${FW_SVN_BRANCH}

if [ ! -d "build" ]; then
    mkdir build
fi
cd build

if [ ! -d "conf" ]; then
    mkdir conf
fi
cd conf

if [ -e "local.conf.template" ]; then
    rm -f local.conf.template
fi
touch local.conf.template

if [ -e "bblayers.conf" ]; then
    rm -f bblayers.conf
fi
touch bblayers.conf

echo "DL_DIR = \""${YOCTO_DOWNLOAD_CACHE}"\"" >> local.conf.template
echo "SSTATE_DIR = \""${YOCTO_SSTATE_DIR}"\"" >> local.conf.template
echo "BUILD_TAG = \""${BUILD_TAG}"\"" >> local.conf.template
echo "FW_VERSION_STATE = \""${FW_VERSION_STATE}"\"" >> local.conf.template
echo "FW_VERSION_BUILD = \""${BUILD_NUMBER}"\"" >> local.conf.template
echo "RAUC_KEY_FILE = \""${RAUC_KEY_FILE}"\"" >> local.conf.template
echo "RAUC_CERT_FILE = \""${RAUC_CERT_FILE}"\"" >> local.conf.template
echo "FWREVISION = \""${SVN_REV}"\"" >> local.conf.template
echo "FW_BRANCH = \""${FW_SVN_BRANCH}"\"" >> local.conf.template

if [ "${FW_UNIT_TEST}" == "true" ]; then
    echo "FW_TESTS = \"ON\"" >> local.conf.template
fi
if [ "${ALLOW_ROOT}" == "true" ]; then
   echo "ALLOW_ROOT = \"ON\"" >> local.conf.template
fi
if [ "${FW_LICENCE_EVALUATION_MODE}" == "true" ]; then
   echo "FW_LICENCE_EVALATION_MODE = \"ON\"" >> local.conf.template
fi
if [ "${TRUST_RELEASE_SIGNATURE}" == "true" ]; then
   echo "TRUST_RELEASE_SIGNATURE = \"ON\"" >> local.conf.template
fi
if [ "${SIGN_RELEASE_SIGNATURE}" == "true" ]; then
   echo "SIGN_RELEASE_SIGNATURE = \"ON\"" >> local.conf.template
fi

cat ${startpwd}/config/local.conf >> local.conf.template
sed -i "s:MACHINE\ \=.*:MACHINE\ \=\ \"${MACHINE}\":g" local.conf.template
cp local.conf.template local.conf
cat ${startpwd}/config/bblayers.conf >> bblayers.conf
cat ${startpwd}/config/${MACHINE}-bblayers.conf >> bblayers.conf
