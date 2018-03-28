# ROSBAG Extractor

__This module is an external CIW component. 
Not in the maitenance scope of CIW, but related.__

This example project illustrates how to extract ROS-BAG files using a Java library.
The work is based on the project: _com.github.swrirobotics.bags.reader_.


### Features:
+ Metadata is extracted as RDF graph. 
+ Image files are extracted to a local staging directory.
+ Metrics or time series are exported into time series buckets and/or to OpenTSDB.


## Quickstart

### Run OpenTSDB
An OpenTSDB host is provided on Dockerhub:

```
docker run \
    -p 4242:4242 \
    jleight/opentsdb
```
Now, we can point the browser to http://127.0.0.1:4242 to show the OpenTSDB web interface.

You can also build your own OpenTSDB image based on this repository: https://github.com/kamir/docker-opentsdb 

### Prepare Demo Data

Please load the example data from Udacity with the torrent file stored in __. /data__.

### Build the Tool
Build an package the project, and finally run the demo runner script.
 

```
mvn clean compile package
cd bin
./run_demo.sh 1024 /Volumes/DS-Tools/IWH/raw_image_stage/udacity/self_driving_car/el_camino_north.bag
```

### Demo Runner Script: Arguments 

#### $1 : Specify the maximum size, in bytes, of the memory allocation pool (JVM).

1024 

#### $2 : Specify the file used for the Demo

/Volumes/DS-Tools/IWH/raw_image_stage/udacity/self_driving_car/el_camino_north.bag

## Output

Example output for the El Camino north ride (from Udacity):

```
>>> BAG Extraction DEMO ... 
>>> BAG file: /Volumes/DS-Tools/IWH/raw_image_stage/udacity/self_driving_car/el_camino_north.bag
>   Inspection of Metadata ... 

    [File]
	/Volumes/DS-Tools/IWH/raw_image_stage/udacity/self_driving_car/el_camino_north.bag - true


    [BAG Metadata]
	Path        : /Volumes/DS-Tools/IWH/raw_image_stage/udacity/self_driving_car/el_camino_north.bag
	start time  : 2016-11-17 23:23:02.025557431
	end time    : 2016-11-18 00:09:09.02184148
	duration [s]: 2766.996 s
	compression : none


    [Topics]
	/can_bus_dbw/can_rx 		1962568 msgs 	: dataspeed_can_msgs/CanMessageStamped 	
	/center_camera/camera_info 		55262 msgs 	: sensor_msgs/CameraInfo 	
	/center_camera/image_color/compressed 		55262 msgs 	: sensor_msgs/CompressedImage 	
	/diagnostics 		10873 msgs 	: diagnostic_msgs/DiagnosticArray 	(4 connections)
	/ecef/ 		1106772 msgs 	: geometry_msgs/PointStamped 	
	/fix 		1106771 msgs 	: sensor_msgs/NavSatFix 	
	/imu/data 		1106772 msgs 	: sensor_msgs/Imu 	
	/left_camera/camera_info 		55285 msgs 	: sensor_msgs/CameraInfo 	
	/left_camera/image_color/compressed 		55285 msgs 	: sensor_msgs/CompressedImage 	
	/pressure 		138346 msgs 	: sensor_msgs/FluidPressure 	
	/right_camera/camera_info 		55285 msgs 	: sensor_msgs/CameraInfo 	
	/right_camera/image_color/compressed 		55284 msgs 	: sensor_msgs/CompressedImage 	
	/time_reference 		3353201 msgs 	: sensor_msgs/TimeReference 	
	/vehicle/brake_info_report 		138346 msgs 	: dbw_mkz_msgs/BrakeInfoReport 	
	/vehicle/brake_report 		138127 msgs 	: dbw_mkz_msgs/BrakeReport 	
	/vehicle/dbw_enabled 		1 msgs 	: std_msgs/Bool 	
	/vehicle/filtered_accel 		138115 msgs 	: std_msgs/Float64 	
	/vehicle/fuel_level_report 		27676 msgs 	: dbw_mkz_msgs/FuelLevelReport 	
	/vehicle/gear_report 		55246 msgs 	: dbw_mkz_msgs/GearReport 	
	/vehicle/gps/fix 		2767 msgs 	: sensor_msgs/NavSatFix 	
	/vehicle/gps/time 		2767 msgs 	: sensor_msgs/TimeReference 	
	/vehicle/gps/vel 		2767 msgs 	: geometry_msgs/TwistStamped 	
	/vehicle/imu/data_raw 		276000 msgs 	: sensor_msgs/Imu 	
	/vehicle/joint_states 		414807 msgs 	: sensor_msgs/JointState 	
	/vehicle/misc_1_report 		55246 msgs 	: dbw_mkz_msgs/Misc1Report 	
	/vehicle/sonar_cloud 		14021 msgs 	: sensor_msgs/PointCloud2 	
	/vehicle/steering_report 		138115 msgs 	: dbw_mkz_msgs/SteeringReport 	
	/vehicle/surround_report 		14021 msgs 	: dbw_mkz_msgs/SurroundReport 	
	/vehicle/suspension_report 		138364 msgs 	: dbw_mkz_msgs/SuspensionReport 	
	/vehicle/throttle_info_report 		276695 msgs 	: dbw_mkz_msgs/ThrottleInfoReport 	
	/vehicle/throttle_report 		138126 msgs 	: dbw_mkz_msgs/ThrottleReport 	
	/vehicle/tire_pressure_report 		5533 msgs 	: dbw_mkz_msgs/TirePressureReport 	
	/vehicle/twist_controller/parameter_descriptions 		1 msgs 	: dynamic_reconfigure/ConfigDescription 	
	/vehicle/twist_controller/parameter_updates 		1 msgs 	: dynamic_reconfigure/Config 	
	/vehicle/wheel_speed_report 		276691 msgs 	: dbw_mkz_msgs/WheelSpeedReport 	
	/velodyne_packets 		26392 msgs 	: velodyne_msgs/VelodyneScan 	


    [Schema of Messages per Topic]

    {/can_bus_dbw/can_rx} [MD5:33747cb98e223cafb806d7e94cb4071f]  package: dataspeed_can_msgs
		msg => CanMessage
		header => Header

    {/center_camera/camera_info} [MD5:c0a06963d7b070870226e4c38764fcdd]  package: sensor_msgs
		P => float64[12]
		R => float64[9]
		D => float64[]
		distortion_model => string
		width => uint32
		header => Header
		K => float64[9]
		roi => RegionOfInterest
		binning_y => uint32
		height => uint32
		binning_x => uint32

    {/center_camera/image_color/compressed} [MD5:8f7a12909da2c9d3332d540a0977563f]  package: sensor_msgs
		data => uint8[]
		format => string
		header => Header

```


