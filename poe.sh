#!/usr/bin/env bash
set -e
git clone https://github.com/MichaelSnowden/poe
cd poe
mvn generate-sources
mvn clean compile assembly:single
echo "export CLASSPATH=\"${CLASSPATH}:$(pwd)/target/poe.jar\"" >> ~/.bashrc
echo "export PATH=\"\$PATH:$(pwd)/target/poe.jar\"" >> ~/.bashrc
echo "chmod u+x \"$(pwd)/target/poe.jar\"" >> ~/.bashrc
source ~/.bashrc
echo "Say hi." >> test.poe
java -jar poe.jar test.po
rm test.poe