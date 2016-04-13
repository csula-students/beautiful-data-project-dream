# Data Storage

Volume and variety of data is always a the primary concern of big data. How should we store them?

## Objectives

* 4/17
  * Homework 2 clarafication
  * Live demo data acquisition
  * [Set up MongoDB][1]
  * [Java MongoDB Connector][2]
  * Store data from acquisition to MongoDB
* 4/24
  * Docker
  * Set up Elastic Search
  * Initial exploration
  * [Optional] HDFS

## Metrics

* 4/17
  * MongoDB installation
  * Java Connector
  * MongoDB CRUD
* 4/24
  * Elastic Search installation
  * Docker provision

## Homework 2

Many students asked from homework 1 office hour -- What is considered to be big data?

I answered as meeting at least 2 Vs out of 3Vs of big data:

* Volume
* Velocity
* Variety

Lets be more specific and lets simplify their requirement for the sake of homework requirements:

* Volume

  > Text based data at least **1GB**
  
* Velocity

  > Real time or at least near real time data ... constantly getting new values from data source.
  
* Variety

  > More than 2 data sources.
  
In your homework 2, your data needs to meet at least above two requirements or it is not considered to be valid data.

## Data Collector Demo

Based on some of the questions from office hour, I'm certain some of you may have question on how to solve the puzzle of implementing my *Collector* and *Source*.

Thus, we will do a live demo implementing a data collector to get tweets from Twitter and store them as JSON.

## Set up MongoDB

Go to [MongoDB site][1] and download and install MongoDB on your host machine.

Use `mongod` to start MongoDB server. Once started, you should see something like below:

```sh
# eric @ Erics-MacBook-Pro-2 in ~/Downloads [20:14:24] 
$ mongod
2016-04-12T20:14:25.325-0700 I JOURNAL  [initandlisten] journal dir=/data/db/journal
2016-04-12T20:14:25.326-0700 I JOURNAL  [initandlisten] recover : no journal files present, no recovery needed
2016-04-12T20:14:25.342-0700 I JOURNAL  [durability] Durability thread started
2016-04-12T20:14:25.342-0700 I CONTROL  [initandlisten] MongoDB starting : pid=78049 port=27017 dbpath=/data/db 64-bit host=Erics-MacBook-Pro-2.local
2016-04-12T20:14:25.342-0700 I CONTROL  [initandlisten] 
2016-04-12T20:14:25.342-0700 I CONTROL  [initandlisten] ** WARNING: soft rlimits too low. Number of files is 256, should be at least 1000
2016-04-12T20:14:25.342-0700 I CONTROL  [initandlisten] db version v3.0.1
2016-04-12T20:14:25.342-0700 I JOURNAL  [journal writer] Journal writer thread started
2016-04-12T20:14:25.342-0700 I CONTROL  [initandlisten] git version: nogitversion
2016-04-12T20:14:25.342-0700 I CONTROL  [initandlisten] build info: Darwin miniyosemite.local 14.1.0 Darwin Kernel Version 14.1.0: Thu Feb 26 19:26:47 PST 2015; root:xnu-2782.10.73~1/RELEASE_X86_64 x86_64 BOOST_LIB_VERSION=1_49
2016-04-12T20:14:25.342-0700 I CONTROL  [initandlisten] allocator: system
2016-04-12T20:14:25.342-0700 I CONTROL  [initandlisten] options: {}
2016-04-12T20:14:26.180-0700 I NETWORK  [initandlisten] waiting for connections on port 27017
```

With above, we will start learning MongoDB by Mongo shell commands for your own debugging. To do this, run `mongo`. This will start your *mongo* shell. You should see something like below:

```sh
# eric @ Erics-MacBook-Pro-2 in ~/Downloads [20:15:47] 
$ mongo
MongoDB shell version: 3.0.1
connecting to: test
Server has startup warnings: 
2016-04-12T20:15:46.930-0700 I CONTROL  [initandlisten] 
2016-04-12T20:15:46.930-0700 I CONTROL  [initandlisten] ** WARNING: soft rlimits too low. Number of files is 256, should be at least 1000
> 
```

* `show dbs`
* `use {db name}`
* `show collections`
* `db.{collectionName}.find()`

[1]: https://www.mongodb.org/downloads
[2]: https://docs.mongodb.org/getting-started/java/
