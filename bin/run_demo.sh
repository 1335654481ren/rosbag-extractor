#!/usr/bin/env bash

cd ..

#
# ./run_demo.sh 1024 /Volumes/DS-Tools/IWH/raw_image_stage/udacity/self_driving_car/el_camino_north.bag
#

echo "$1 : Specify the maximum size, in bytes, of the memory allocation pool (JVM)."
echo "$2 : Specify the file used for the Demo"
echo

java -Xmx$1m -cp ./target/rosbag-demo-0.1.0-SNAPSHOT.jar core.Demo1 $2

java -Xmx$1m -cp ./target/rosbag-demo-0.1.0-SNAPSHOT.jar core.Demo2 $2

java -Xmx$1m -cp ./target/rosbag-demo-0.1.0-SNAPSHOT.jar core.Demo2 $2
