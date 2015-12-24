#!/usr/bin/env bash
set -e
rm -rf ~/poe-install
mkdir ~/poe-install
cd ~/poe-install
git clone https://github.com/MichaelSnowden/poe .
mvn generate-sources
mvn clean compile assembly:single
echo "export CLASSPATH=\"${CLASSPATH}:$(pwd)/target/poe.jar\"" >> ~/.bashrc
echo "export PATH=\"\$PATH:$(pwd)/target/poe.jar\"" >> ~/.bashrc
echo "chown -R $(whoami) ~/poe-install" >> ~/.bashrc
source ~/.bashrc
echo "Say hi." >> test.poe
java -jar poe.jar test.poe
rm test.poe