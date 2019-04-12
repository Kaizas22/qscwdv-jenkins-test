#!/bin/bash

startpwd=$(pwd)

#source bash/config/env_config
#source poky/oe-init-build-env

# remove all sources from download cache
rm -rf ${YOCTO_DOWNLOAD_CACHE}"*svn01.com"
rm -rf ${YOCTO_DOWNLOAD_CACHE}"svn/svn01.com/"

# remove all sdks
rm -rf ${startpwd}/build/tmp/deploy/sdk/*

# clean all sources from sstate-cache and tmp-dir
echo "bitbake fw -c cleanall"
