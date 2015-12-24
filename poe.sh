#!/usr/bin/env bash
set -e
rm -rf ~/poe-install
mkdir ~/poe-install
cd ~/poe-install
git clone https://github.com/MichaelSnowden/poe .
mvn generate-sources
mvn clean compile assembly:single
sudo cp poec.sh /usr/bin/poec