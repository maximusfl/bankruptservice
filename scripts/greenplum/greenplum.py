#!/usr/bin/env python3

import os
import subprocess

greenplum_version = '6.15.0'

download_url = f'https://github.com/greenplum-db/gpdb/releases/download/{greenplum_version}/greenplum-db-{greenplum_version}-rhel7-x86_64.rpm'

install_dir = '/usr/local/greenplum-db'

def download_greenplum():
    print(f'Downloading Greenplum version {greenplum_version}...')
    subprocess.run(['wget', '-O', 'greenplum-db.rpm', download_url], check=True)

def install_greenplum():
    print('Installing Greenplum...')
    subprocess.run(['sudo', 'yum', 'install', '-y', 'greenplum-db.rpm'], check=True)

def source_greenplum_path():
    os.system(f'source {install_dir}/greenplum_path.sh')

def initialize_and_start_cluster():
    print('Initializing and starting the Greenplum cluster...')
    subprocess.run(['gpinitsystem', '-c', 'gpinitsystem_config', '-h', 'hostfile_gpinitsystem'], check=True)
    subprocess.run(['gpstart'], check=True)

def main():
    download_greenplum()
    install_greenplum()
    source_greenplum_path()
    initialize_and_start_cluster()

if __name__ == '__main__':
    main()