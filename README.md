# kafka-play
Some test snippets with Kafka producers, consumers (subscribe) and consumers (assign)  
I used windows for these, after installing kafka 2.12-2.1.0, but the same work on Linux on my Virtual Box Ubuntu     
Some of the imporant commands:  
#### Start zookeeper  
$ bin\windows\zookeeper-server-start.bat config\zookeeper.properties  

#### Start Kafka
$ bin\windows\kafka-server-start.bat config\server.properties  

#### Create a topic
$ bin\windows\kafka-topics.bat --create --topic my-big-topic1 --zookeeper localhost:2181 --partitions 1 --replication-factor 1  

#### Get info re a topic. Omit --topic to describe all topics  
$ bin\windows\kafka-topics.bat --zookeeper localhost:2181 --describe --topic my-big-topic  

#### Add partitions  
$ bin\windows\kafka-topics.bat --zookeeper localhost:2181 --alter --topic my-big-topic --partitions 4  
