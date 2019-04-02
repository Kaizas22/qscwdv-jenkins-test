#!/bin/bash

#source config/env_config

SVN_REV=$1

echo "svn: "${SVN_REV}

startpwd=$(pwd)

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

cat ${startpwd}/config/local.conf >> local.conf.template
sed -i "s:MACHINE\ \=.*:MACHINE\ \=\ \"TEST\":g local.conf.template
cp local.conf.template local.conf
