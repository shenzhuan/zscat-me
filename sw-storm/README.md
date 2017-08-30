#启动Storm Topology
./storm jar /usr/local/im/hulu-recommend-1.0-jar-with-dependencies.jar RecommendTopology RecommendTopology &

##停止Topologies
cd /usr/local/storm/bin
./storm kill RecommendTopology



ui.port   6066