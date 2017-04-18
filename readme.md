# Most Recent 100 Visitors
**MapReduce Program to find most recent 100 Visitors**


## Retrieve code

* `$ git clone https://github.com/sheshnath08/RecentVisitors.git`
* `$ cd RecentVisitors`


## Dependensies

*Project is written with Hadoop's MapReduce framework and it uses [org.json](https://mvnrepository.com/artifact/org.json/json/20160810) library to parse json data input*

## Running

* Put your input files on HDFS 
    `$ hadoop fs -put path-to-local-directory input-path-on-hdfs`
* Download org.json jar from [here](https://mvnrepository.com/artifact/org.json/json/20160810)
    https://mvnrepository.com/artifact/org.json/json/20160810
* Put org.json jar on HDFS 
    `$ hadoop fs -put path-to-local-jar jar-path-on-hdfs`
* I have compiled and created jar file for hadoop can be run using following command
    `$  hadoop jar RecentVisitor.jar RecentVisitorDriver input-path-on-hdfs output-path-on-hdfs`
* Get output files from HDFS
    `$ hadoop fs -get output-path-on-hdfs`
 
