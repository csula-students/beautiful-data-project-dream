# Data Storage

Volume and variety of data is always a the primary concern of big data. How should we store them?

What about data comes in with high velocity? How do we store them and analyze them in real time?

## Objectives

* 4/17
  * Homework 2 clarification
  * Live demo data acquisition
  * [Set up MongoDB][1]
  * [Java MongoDB Driver][2]
  * Store data from acquisition to MongoDB
  * [Docker][3]
* 4/24
  * [Docker][3]
  * Introduction of Elastic Search
  * Set up Elastic Search on local dev machine
  * Learn Elastic Search
  * Set up Elastic Search Java Client
  * Set up Elastic Search on docker container
  * Initial exploration with Elastic Search and Kibana
  * [Optional] HDFS

## Metrics

* 4/17
  * [MongoDB installation][1]
  * [Java MongoDB Driver][2]
  * MongoDB CRUD
  * Docker set up
* 4/24
  * Elastic Search installation
  * Docker provision

## Homework 2

Many students asked questions regarding homework 1 during office hour -- **What is considered to be big data?**

Big data in this class needs to meet at least 2 of the following:

* Volume
* Velocity
* Variety

What does it mean by volume? How large is considered large enough?

To that, lets be more specific on our requirements:

* Volume

  > Text based data at least **3GB**  
  > Non-text based data, please discuss with me before you move on to acquire it.

* Velocity

  > Real time or at least near real time data ... constantly getting new values from data source.

* Variety

  > More than 3 data sources. e.g. Twitter, open weather, CNN news ... etc.

To sum up:

In homework 2, data needs to meet at least two requirements above; or it is not considered to be valid big data.

## Data Collector Demo

Another common questions regarding homework 2 from office hour:

How do I implement this *Collector* and *Source* interface? What does each interface do? How do I collect data from {insert your data source}.

First, we will go over what is our Collector and Source and implement an example of getting data from Twitter in class.

Regarding your question on how do you get data from {insert your data source}:

I will not be answering domain specific questions, such as how do I get data from Twitter.

Why?

This is your choice of your data source and you should be doing a lot of research on how you get data as well as how you are going to use data. In other word, these domain specific questions are your responsibility to answer them to your best effort.

I'd suggest you to Google your question before you ask me any further detail.

Without further due, demo time.

---

## Set up MongoDB

Go to [MongoDB official site][1] and download and install MongoDB on your host machine.

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

## Commonly used MongoDB shell commands

* `show dbs`
* `use {db name}`
* `show collections`
* `db.{collectionName}.find().pretty()`
* `db.{collectionName}.insert({data})`
* `db.{collectionName}.drop()`

## Example of using commands above

Import example JSON data from https://raw.githubusercontent.com/mongodb/docs-assets/primer-dataset/primer-dataset.json as `primer-dataset-.json`

You can import data using following command:

```
mongoimport --db test --collection restaurants --drop --file primer-dataset.json
```

> Make sure you have `mongod` (mongo instance) running before you run mongoimport above

## MongoDB terminology

### MySQL terminology and comparison

MySQL    | MongoDB
---      | ---
Database | Database
Table    | Collection
Row      | Document
Column   | Field
Joins    | Embedded documents, linking

### Query comparison

![MySQL vs MongoDB Query](imgs/mysql_vs_mongo_query.png)
Credit: https://www.mongodb.com/compare/mongodb-mysql

## Java MongoDB Driver

Check out the latest changes from this repository, you should have the changes below in your `build.gradle`:

```
dependencies{
  // ...
  compile 'org.mongodb:mongodb-driver:3.2.2'
}
```

Check out `MongoExampleApp.java` and continue reading from there.

## Docker

What is docker?

Docker allows you to package an application with all of its dependencies into a standardized unit for software development.

Why do we need to learn this docker?

In data science, you are likely to use a lot of dependencies that is outside of just Java. In example, you may choose to use MongoDB and other tools to support your own data analysis. You cannot simply get a server and manually SSH into server to install these dependencies one by one.

Thus, docker becomes a great solution for these tools management. In example, we will be using docker in this class to use MongoDB, Elastic Search and further to use Python.

Further, Docker makes it easier to deploy something from your development environment to cloud like Google Cloud Platform or Digital Ocean.

## Install Docker

Please direct yourself to [Docker][4] official site to install docker.

Install docker toolbox if you are using Mac or Windows. If you are using Linux, use your packaging system to install it.

After installation, you should be able to do `docker -v` to see docker version like below:

```sh
# eric @ Erics-MacBook-Pro in ~/Developments/csula/datascience-spring-2016 on git:master x [16:00:04]
$ docker -v
Docker version 1.11.0, build 4dc5990
# eric @ Erics-MacBook-Pro in ~/Developments/csula/datascience-spring-2016 on git:master x [16:00:04]
$ docker-machine -v
docker-machine version 0.7.0, build a650a40
# eric @ Erics-MacBook-Pro in ~/Developments/csula/datascience-spring-2016 on git:master x [16:00:32]
$ docker-compose -v
docker-compose version 1.7.0, build 0d7bf73
```

In this class, we will be using `docker`, `docker-machine` and `docker-compose`. Make sure you can see the version number above before we move on.

## Using docker to run this repository

### Setting up Docker Machine

Once you have docker-machine installed above, run the following command:

```sh
# if you don't docker-machine already
# for linux, you don't need docker-machine
$ docker-machine create --driver virtualbox default
$ docker-machine start
```

You should be able to do `docker-machine status` to see status like below:

```sh
# eric @ Erics-MacBook-Pro in ~/Developments/csula/datascience-spring-2016 on git:master x [16:27:36]
$ docker-machine status
Running
```

Now you have to use `docker-machine env` and run **the last line** to set up your environment variable like below:

```sh
# eric @ Erics-MacBook-Pro in ~/Developments/csula/datascience-spring-2016 on git:master x [16:31:04]
$ docker-machine env
# ...
# Run this command to configure your shell:
# eval $(docker-machine env)
###### for windows, you have a different command above
```

With above setting to be done, your docker-machine is now ready to run docker!

### Docker Engine

When docker machine is ready, you can run `docker build --rm=true -t big-data .` and you should see something like below:

```sh
$ docker build -rm=true -t big-data .
# ...
 ---> Running in f40fce6f0d24

 ---> 9d07afd612cd
Removing intermediate container f40fce6f0d24
Successfully built 9d07afd612cd
```

Above command will build up the **docker image** for your docker to run later with command `docker run --rm=true big-image`.

```sh
$ docker run --rm=true big-data
Hello Data Science
```

### Docker terminology

* build
> An process of building Docker image based on Dockerfile

* Dockerfile
> A Dockerfile is a text document that contains all the commands you would normally execute manually in order to build a Docker image. Docker can build images automatically by reading the instructions from a Dockerfile.  
> You can check out our course repository `Dockerfile` for example

* Image
> An Image is an ordered collection of root filesystem changes and the corresponding execution parameters for use within a container runtime. An image typically contains a union of layered filesystems stacked on top of each other. An image does not have state and it never changes.  
> Use `docker images` to find out more about images we have built so far

* Container
> Runtime instance of docker image  
> Use `docker ps` to find out what is current running

* Compose
>  With compose, you define a multi-container application in a single file, then spin your application up in a single command which does everything that needs to be done to get it running.

### Why Docker now?

Starting from this point on, we will be using more and more tools in this class like `MongoDB` or `Elastic Search`. It will get harder to maintain all these dependencies on your host machine as well as all your teammates machine.

It would be nicer if we can build our application once and run it everywhere.

Thus, it is great time to introduce docker!

For example, we ran MongoDB earlier with only on our host machine. Lets run the same MongoDB instance on Docker with `docker-compose`

### Git detour

Some students asked about how do you get the latest code from this repository while on the same time pushing code to your own team repository.

Well, one way to do it is through manual copy and paste the code. This way you don't need to learn Git but you have to do it all manually and can be prone to errors.

I'd suggest to use the following approach:

```sh
# list a remote you have
$ git remote -v

# add the other remote for your local git directory
# lets assume we have course repo url listed above
$ git remote add project {url}

# When you are done adding the remote, check it with
$ git remote -v

# And then you can get the latest course code by
$ git fetch && git pull origin master

# there might be some merge-conflicts happening after pull
# When in doubt, you can checkout course repo side of code only by
# $ git checkout --theirs .

# To push to your team repository
$ git push project {fromBranchName:toBranchName}
```

### Docker Compose

Alright, we have build docker successfully now. What is next?

The utility of docker starts only now. In our project, we probably need to include a couple services like MongoDB or Elastic Search. It's very hard to ask all deverlopers in your team to set up programming environment at all time.

Using docker, we can set up the tools with our application in one command `docker-compose up`

This `docker-compose up` will build and run multiple contains with the images we defined in the `docker-compose.yml`

### Gradle shadow jar

Wait, you meant Gradle can does more things than dependencies management!? What else can Gradle provide to us?

Gradle can also help to build the jar file just like Maven. In this course repository, we are using ShadowJar plugin to build our one-fat-jar.

Once we finish building this one fat jar, our Dockerfile becomes trivial to run the jar file. (See last line of Dockerfile).

For example, if we want to run MongoDB along with our example MongoDB example app, we will need to change the following line to:

```java
MongoClient mongoClient = new MongoClient();
```

to

```java
MongoClient mongoClient = new MongoClient("db");
```

Why?

We have to tell our application to connect to MongoDB running in different container.

In usual dev ops world, you will need to do your own manual networking. With docker, the networking process will be simplified by the docker-compose.

Now you can change our main class under `build.gradle` line 21 to MongoDB example app and build and run using docker.

### Elastic Search

Elastic search ... you know. For searching.

### Introduction

* Open source search engine
* Built on top of Apache Lucene
* JSON based
* Scheme free
* Distributed
* Multi-tenancy
* API Centric & REST

### What can it do?

* Unstructured & structured search
* Analytics
* Combine

### Get started

* Download [Elastic Search distribution](https://www.elastic.co/downloads/elasticsearch)

```sh
.
├── LICENSE.txt
├── NOTICE.txt
├── README.textile
├── bin                    # executable scripts
│   ├── elasticsearch
│   ├── plugin
│   └── ...
├── config                 # node configuration
│   ├── elasticsearch.yml
│   └── logging.yml
├── lib
│   ├── elasticsearch-2.2.0.jar
│   └── ...
└── ...
```

* Run `elasticsearch` executable

```sh
# eric at Erics-MacBook-Pro.local in ~/Downloads/elasticsearch-2.2.0 [22:37:14]
$ ./bin/elasticsearch
[2016-03-02 22:37:22,354][INFO ][node                     ] [NFL Superpro] version[2.2.0], pid[66651], build[8ff36d1/2016-01-27T13:32:39Z]
[2016-03-02 22:37:22,354][INFO ][node                     ] [NFL Superpro] initializing ...
[2016-03-02 22:37:22,937][INFO ][plugins                  ] [NFL Superpro] modules [lang-expression, lang-groovy], plugins [], sites []
[2016-03-02 22:37:22,967][INFO ][env                      ] [NFL Superpro] using [1] data paths, mounts [[/ (/dev/disk1)]], net usable_space [166gb], net total_space [464.7gb], spins? [unknown], types [hfs]
[2016-03-02 22:37:22,967][INFO ][env                      ] [NFL Superpro] heap size [989.8mb], compressed ordinary object pointers [true]
[2016-03-02 22:37:24,923][INFO ][node                     ] [NFL Superpro] initialized
[2016-03-02 22:37:24,923][INFO ][node                     ] [NFL Superpro] starting ...
[2016-03-02 22:37:25,020][INFO ][transport                ] [NFL Superpro] publish_address {127.0.0.1:9300}, bound_addresses {[fe80::1]:9300}, {[::1]:9300}, {127.0.0.1:9300}
[2016-03-02 22:37:25,028][INFO ][discovery                ] [NFL Superpro] elasticsearch/RMR814SUQ-SVatuaP1i31A
[2016-03-02 22:37:28,057][INFO ][cluster.service          ] [NFL Superpro] new_master {NFL Superpro}{RMR814SUQ-SVatuaP1i31A}{127.0.0.1}{127.0.0.1:9300}, reason: zen-disco-join(elected_as_master, [0] joins received)
[2016-03-02 22:37:28,075][INFO ][http                     ] [NFL Superpro] publish_address {127.0.0.1:9200}, bound_addresses {[fe80::1]:9200}, {[::1]:9200}, {127.0.0.1:9200}
[2016-03-02 22:37:28,075][INFO ][node                     ] [NFL Superpro] started
[2016-03-02 22:37:28,099][INFO ][gateway                  ] [NFL Superpro] recovered [0] indices into cluster_state
```

* confirm working by opening in browser under http://localhost:9200

```sh
# eric at Erics-MacBook-Pro.local in ~/Downloads/elasticsearch-2.2.0 [22:38:19]
$ curl http://localhost:9200/\?pretty
{
  "name" : "NFL Superpro",
  "cluster_name" : "elasticsearch",
  "version" : {
    "number" : "2.2.0",
    "build_hash" : "8ff36d139e16f8720f2947ef62c8167a888992fe",
    "build_timestamp" : "2016-01-27T13:32:39Z",
    "build_snapshot" : false,
    "lucene_version" : "5.4.1"
  },
  "tagline" : "You Know, for Search"
}
```

### Debugging in Sense

I suggested you to install sense so that we can walk through the tutorial of Elastic Search without worry of learning curl.

* Install [Kibana](https://www.elastic.co/downloads/kibana)
* Run `./bin/kibana plugin --install elastic/sense` under kibana folder to install sense
* Confirm Sense working by http://localhost:5601/app/sense?load_from=http://www.elastic.co/guide/en/elasticsearch/guide/current/snippets/010_Intro/10_Info.json

### Communicate with ES

* [Java Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index.html)
* REST

> We will focus on the REST to explain Elastic Search briefly first and then implement in Java Client later

### Quick Review on RESTful

* CRUD

```
* POST   => Create
* GET    => Read
* PUT    => Update
* DELETE => DELETE
```

### ES Terminologies from RDBS point of view

```
Relational DB  ⇒ Databases ⇒ Tables ⇒ Rows      ⇒ Columns
Elasticsearch  ⇒ Indices   ⇒ Types  ⇒ Documents ⇒ Fields
```

### Learn by examples using Sense

#### Example of write

```
http://localhost:5601/app/sense?load_from=http://www.elastic.co/guide/en/elasticsearch/guide/current/snippets/010_Intro/25_Index.json
```

#### Example of read

```
http://localhost:5601/app/sense?load_from=http://www.elastic.co/guide/en/elasticsearch/guide/current/snippets/010_Intro/30_Get.json
```

#### Example of search lite

```
http://localhost:5601/app/sense?load_from=http://www.elastic.co/guide/en/elasticsearch/guide/current/snippets/010_Intro/30_Simple_search.json
```

#### More complex search

```
http://localhost:5601/app/sense?load_from=http://www.elastic.co/guide/en/elasticsearch/guide/current/snippets/010_Intro/30_Query_DSL.json
```

### Searching

While many searches will just work out of the box, to use Elasticsearch to its full potential, you need to understand three subjects:

* Mapping

    How the data in each field is interpreted

* Analysis

    How full text is processed to make it searchable

* Query DSL

    The flexible, powerful query language used by Elasticsearch

### Java Elastic Search Client

Go to [Java Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index.html) to get started.

[1]: https://www.mongodb.org/downloads
[2]: https://docs.mongodb.org/getting-started/java/
[3]: https://www.docker.com/
[4]: https://docs.docker.com/windows/
