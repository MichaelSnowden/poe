#!/usr/bin/env bash
set -e
rm -rf ~/poe-install
mkdir ~/poe-install
cd ~/poe-install
git clone https://github.com/MichaelSnowden/poe .
mvn generate-sources
mvn clean compile assembly:single
echo "java -jar $(pwd)/target/poe.jar \$1" >> poec.sh
sudo chmod 777 poec.sh
sudo cp poec.sh /usr/bin/poec