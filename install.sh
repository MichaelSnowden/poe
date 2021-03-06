#!/usr/bin/env bash
set -e
rm -rf ~/poe-install
mkdir ~/poe-install
cd ~/poe-install
git clone https://github.com/MichaelSnowden/poe .
mvn generate-sources
mvn clean compile assembly:single
echo "#!/usr/bin/env bash" >> poe.sh
echo "java -jar $(pwd)/target/poe.jar \$1" >> poe.sh
sudo chmod 777 poe.sh
sudo cp poe.sh /usr/bin/poe
echo "Installation successful!"
echo "You can now run POE files using the 'poe' command"
echo "Try this as an example"
echo "  echo -n 'Say hi.' >> test.poe"
echo "  poe test.poe"