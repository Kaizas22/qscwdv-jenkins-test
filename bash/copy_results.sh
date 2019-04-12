#!/bin/bash

MACHINE=$1
DEVICE=$2

copy_mode="Error"
build_folder=${PWD}/build
output_folder=${PWD}/result
svn_folder=${PWD}/fw_dummy/testdir

copy() {
    from="$1"
    to="$2"
    if [ "NoError" == "$copy_mode" ]; then
        cp ${from} ${to} 2> /dev/null
    else
        cp ${from} ${to}
    fi
}

if [ "0" -lt "$#" ]; then
    if [ "--no-copy-error" == "$1" ]; then
        copy_mode="NoError"
    fi
fi

# clean up old files
rm -rf ${output_folder}/*

mkdir -p ${output_folder}/update/
mkdir -p ${output_folder}/sdk/
mkdir -p ${output_folder}/usb/
mkdir -p ${output_folder}/version_info/

deploy_folder=${build_folder}/tmp/deploy

copy ${deploy_folder}/images/${MACHINE}/${DEVICE}-image-base-${MACHINE}.* ${output_folder}/usb/${DEVICE}-image-base.*
copy ${deploy_folder}/images/${MACHINE}/${DEVICE}-bundle-base-${MACHINE}.raucb ${output_folder}/update/${DEVICE}-bundle-base.raucb

if [ -d "${deploy_folder}/sdk" ]; then
    copy "${deploy_folder}/sdk/*.sh" ${output_folder}/sdk/
    copy "${deploy_folder}/sdk/*.xz" ${output_folder}/sdk/
fi

copy ${build_folder}/buildhistory/images/${MACHINE}/glibc/${DEVICE}-image-base/build-id.txt ${output_folder}/version_info/
copy ${build_folder}/buildhistory/metadata-revs ${output_folder}/version_info/
copy ${deploy_folder}/images/${MACHINE}/${DEVICE}-image.base-${MACHINE}.manifest ${output_folder}/${DEVICE}-package.manifest

tar -C ${build_folder}/tmp/work/corei7-64-pxc-linux/arp/1.1.0-r0/build -czf ${output_folder}/api_documentation.tar.gz html

if [ "${SPECIAL_BUILD}" == "true" ]; then
    copy ${deploy_folder}/images/${MACHINE}/${DEVICE}-image-production-${MACHINE}.* ${output_folder}/usb/${DEVICE}-production-image-${svn_revision}.*
    tar -czf ${output_folder}/sources.tar.gz ${deploy_folder}/sources
fi

mkdir ${build_folder}/temp
cd ${build_folder}/temp
tar -xzf ${deploy_folder}/images/${MACHINE}/${DEVICE}-image-base-${MACHINE}.tar.gz
copy ${build_folder}/temp/etc/plcnext/fwversion ${output_folder}/version_info/
copy ${build_folder}/temp/etc/plcnext/FwVersionJenkins.properties ${output_folder}/version_info/
tar -czf ${output_folder}/licenses.tar.gz ${build_folder}/temp/usr/share/common-licenses/
cd ${build_folder}
rm -rf temp/
