# Homework 4

## Due Date

June 5th Midnight.

## Description

In this homework, your job is to **publish** and *polish* your visualization from homework 3.

To recall, in homework 3, you did EDA and present your own findings to us in a private video with many different graphs.

Now, you will have to publish and polish your findings to public with ElasticSearch running on the cloud.

In summary, we want you to show your work on the internet!

But how?

Here is what we suggest you to do:

* Host on AWS:
  1. Host ElasticSearch on [AWS][2]
  2. Start to build your own Kibana dashboard on AWS ElasticSearch
  3. Send us your dashboard link (should display what you have built)
* Optionally as bonus
  1. Build a static site to host your visualization

### Statements to answer for visualizations

* Within a sentence, summarize your findings
* What challenges do you have for polishing visualizations?
* Why did you choose the certain graph types over others (Pie chart, Bar chart, ... etc.)?

### Instructions

#### Overview

Previously, we are able to insert data into ElasticSearch we ran locally. However, we are not able to show these visualizations to our friends cross internet. We are sad because we don't get to show our precious works :(

For this homework, we want to host our findings on the internet and want to show these results to our friends and most importantly to us (lecturers).

In order to do so, you will need to do one of the following things:

1. Get an AWS account
2. Create an ElasticSearch service on your AWS
3. Send data to ElasticSearch using JestExampleApp.java (with your modification)
4. Open Kibana to build your dashboard

And then optionally if you want bonus

* Host your visualizations on the cloud using something like [Firebase][1]

#### Send data to AWS ElasticSearch

Please look into JestExampleApp.java for sending data from local storage to AWS ElasticSearch cluster.

Try to modify the JestExampleApp.java to send data to your own ElasticSearch cluster and see it showing up on the cloud first.

JestExampleApp is very similar to the ElasticSearchExampleApp.java we went through before but with different library -- Jest.

Please go here to find their API document -- https://github.com/searchbox-io/Jest

A quick reason why on we have to use different library rather than using ElasticSearch java.

> In AWS, they don't support TCP transport (which is what ElasticSearch Java client was written in). Therefore, we have to use Jest to send data to ElasticSearch with REST only.  

Based on the above restriction, you might find sending data to Cloud ElasticSearch to be noticeably longer. Please be patient while waiting data to show up on ElasticSearch cluster on AWS.

Once you started sending data to AWS ElasticSearch cluster, you should be able to query immediately on the AWS cluster.

In example, you should be able to open http://your-elastic-search.us-west-2.es.amazonaws.com/_search and find some result out.

Similarly, you should be able to open your Kibana and start seeing graph immediately by http://your-elastic-search.us-west-2.es.amazonaws.com/_plugin/kibana/

## Tasks

1. Get an AWS account
3. Create an ElasticSearch service
4. Send data to ElasticSearch
5. Build your own Kibana dashboard
6. Submit the link to your Kibana dashboard

## Deliverables

* CSNS Submission containing:
  * link to your Kibana
  * Pull request on your repository if there is any coding changes

## Grading rubric

* CSNS Submission [1 pt]
* Hosting on cloud [2 pts]
* Visualizations in cloud (data in cloud ElasticSearch instance) [7 pts]
* Visualization using web technologies (NVD3.js, Chart.js or any other libraries you used) [2 pts]

[1]: https://firebase.google.com/
[2]: https://aws.amazon.com/
