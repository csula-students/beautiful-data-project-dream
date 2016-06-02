# Data Analysis

Now we have a large amount of data. How do we make sense out of this set of data?

## Objectives

* 5/1
  * Elastic Search Query/Aggregation
* 5/8 -- No class
  * MongoDB Aggregation
    * Sum, count, avg ...
    * Map Reduce
* 5/15, 5/22
  * Python
    * Common math libraries
    * Machine learning
* [Optional knowledge] Hadoop

## Metrics

* Elastic Search query/aggregation
* Kibana visualization
* MongoDB aggregation
* Map reduce concept
* Machine learning algorithms

## Docker Architecture

Due to some questions from students that still don't quite get what Docker is, I want to clarify docker a bit on architecture stand point.

![docker architecture graph](imgs/docker_architecture.png)  
Credit: https://docs.docker.com/engine/understanding-docker/

## Exploratory Data Analysis

So far, we have been doing some initial data analysis(IDA) with our data munging process. We tested some of our assumptions on the data shape and clean up accordingly.

Many of you asked me if you data munge process is right. In example, is it right to just clean up the null values? What else should we clean up?

My answer is always "it depends on your question(s) and what you are trying to accomplish".

For instance, some of you are saying you don't need timestamp data because it doesn't fit into your question. So you remove timestamp data out.

This may not be the right move to do just because you think you are may not be using it without going through the process of EDA.

So what is this EDA?

![Data Science graph](https://upload.wikimedia.org/wikipedia/commons/b/ba/Data_visualization_process_v1.png)  
credit: https://en.wikipedia.org/wiki/Exploratory_data_analysis

> [EDA] focuses more narrowly on checking assumptions required for model fitting and hypothesis testing, and handling missing values and making transformations of variables as needed. EDA encompasses IDA.

EDA is an examination among data and relationship among variables. EDA is usually the first part of the big analysis process to build the predict model. It is an opportunity to check your assumption about data and to build your intuition about data set.

Many times, business decisions are made by the visualization result from EDA. Other times, you may polish the visualization before you present your finding of data to public.

EDA is about an approach of understanding data using visualization or some statistic model (like what we did in week 1 ... mean, min, max ... etc.).

The objectives of EDA are to:

* Suggest hypotheses about the causes of observed phenomena
* Assess assumptions on which statistical inference will be based
* Support the selection of appropriate statistical tools and techniques
* Provide a basis for further data collection through surveys or experiments

In this course, we will continue with Elastic Search to perform aggregations and use Kibana to do some initial visualization without too much programming behind the scene.

Later on, we will be using MongoDB to learn map reduce concept (again to perform aggregations in scalable way).

Map reduce concept is quite important in a sense that Hadoop is all about map reduce. Even though MongoDB map reduce is different from Hadoop, I think it's a great starting point to see what map reduce can do.

Further, we will also spend about two weeks learning Python and how to use Python to do more analysis or machine learning for better predict model.

John, by next week (May 8th), will talk more in depth about EDA in his video. Stay tuned!

## Elastic search and kibana in depth

[Detail goes here](data_analysis_elasticsearch.md)

## Python

### Basic Python

[Detail goes here](data_analysis_python_basic.md)

### Python with NumPy and Pandas

[Detail goes here](data_analysis_python_libs.md)

### Machine Learning

[Detail goes here](data_analysis_python_ml.md)

### Resources/references

* https://github.com/vinta/awesome-python
* https://developers.google.com/edu/python/
* [Numpy Tutorial](http://scipy.github.io/old-wiki/pages/Tentative_NumPy_Tutorial.html)
* [Scipy tutorial](http://docs.scipy.org/doc/scipy/reference/tutorial/)
* [Pandas in 10 minutes](http://pandas.pydata.org/pandas-docs/stable/10min.html)
