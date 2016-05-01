# Data Analysis

Now we have a large amount of data. How do we make sense out of this set of data?

## Objectives

* 5/1
  * Elastic Search Query/Aggregation
* 5/8
  * MongoDB Aggregation
    * Sum, count, avg ...
    * Map Reduce
* 5/15, 5/22
  * Python
    * Common math libraries
    * Machine learning
* [Optional knowledge] Hadoop

## Metrics

* Elastic Search query
* MongoDB Aggregation test
* Map reduce concept
* Common Machine Learning methods


## Docker Architecture

Due to some confusions from students, I want to clarify docker a bit on architecture stand point.

![docker architecture graph](imgs/docker_architecture.png)
Credit: https://docs.docker.com/engine/understanding-docker/

## Exploratory Data Analysis

So far, we have been doing some initial data analysis(IDA) with our data munging process. We tested some of our assumptions on the data shape and clean up accordingly.

Many of you asked me if you data munge process is right. In example, is it right to just clean up the error values?

My answer is always depends on your question(s) and what you are trying to accomplish.

For instance, some of you are saying you don't need timestamp data because it doesn't fit into your question. So you munge timestamp data out.

This may not be the proper solution to just munge out data because you think you are may not be using it without going through the process of EDA.

So what is this EDA?

https://en.wikipedia.org/wiki/Exploratory_data_analysis

> [EDA] focuses more narrowly on checking assumptions required for model fitting and hypothesis testing, and handling missing values and making transformations of variables as needed. EDA encompasses IDA.

Examination among data and relationship among variables. EDA is usually the first part of the big analysis process to build the predict model. It is an opportunity to check your assumption about data and your intuition about data set.

Many times, business decisions are made by the visualization result from EDA. Other times, you may polish this visualization before you present your finding of data to public.

EDA is about an approach of understanding data using visualization or some statistic model (like what we did in week 1 ... mean, min, max ... etc.).

In this course, we will continue with Elastic Search to perform aggregations and use Kibana to do some initial visualization.

Later on, we will be using MongoDB to learn map reduce concept (again to perform aggregations).

Further, we will also spend two weeks learning Python and how to use Python to do detail analysis or machine learning for better predict model.

John, by next week, will talk more in depth about EDA in video. Stay tuned!

## Elastic Search Query In Depth

Lets do some searching on the weather data we have earlier from last class.

> Note that in the following examples, I'm assuming you all have the Sense with Kibana set up properly.  
> In other word, these code only work within **Sense**.

We will start by talking about `filtering queries` (structured search)

https://www.elastic.co/guide/en/elasticsearch/guide/current/structured-search.html

> When working with exact values, you will be working with non-scoring, filtering queries. Filters are important because they are very fast. They do not calculate relevance (avoiding the entire scoring phase) and are easily cached.

```
GET /bd-data/city-temperatures/_search
{
    "query" : {
        "constant_score" : {
            "filter" : {
                "term" : {
                    "state" : "Washington"
                }
            }
        }
    }
}
```

This finds nothing!? But we looked back to the csv file ourselves, we are able to find the state "Washington". Why?

Elastic search by default will analyze the string fields for full text searching. For instance, if we type in below:

```
GET /bd-data/city-temperatures/_search?q=Washington
```

And we are able to find some records.

Going back to the source code for Elastic Search example. We have to use specific `matchPhrase` as to `match` to look for "Washington" data. This is due to the same reason -- Elastic Search automatically analyze string field.

How do we turn this analyzer off?

```sh
# delete the entire index is required because we cannot change existing
# mapping
DELETE /bd-data

# This add up the index mapping before we add the data
PUT /bd-data
{
    "mappings" : {
        "city-temperatures" : {
            "properties" : {
                "state" : {
                    "type" : "string",
                    "index" : "not_analyzed"
                },
                "date": {
                    "type": "date"
                }
            }
        }
    }
}
```

With above mapping defined, we now can go ahead and re-insert data back to this index by running out elastic search example again.

After data now is inserted back to the index, we can check the mapping by:

```
GET /bd-data/city-temperatures/_mapping
```

And now you should be able to perform normal string search as above:

```
GET /bd-data/city-temperatures/_search
{
    "query" : {
        "constant_score" : {
            "filter" : {
                "term" : {
                    "state" : "Washington"
                }
            }
        }
    }
}
```

Take away?

1. In order to change mapping, you will have to delete entire index first. Therefore, make sure you have your original set of data somewhere ... like in CSV or in your primary database (MongoDB).
2. Be aware of string type mapping


### Internal Filter Operationedit

Internally, Elasticsearch is performing several operations when executing a non-scoring query:

1. Find matching docs.

    The term query looks up the term XHDK-A-1293-#fJ3 in the inverted index and retrieves the list of documents that contain that term. In this case, only document 1 has the term we are looking for.

2. Build a bitset.

    The filter then builds a bitset--an array of 1s and 0s—that describes which documents contain the term. Matching documents receive a 1 bit. In our example, the bitset would be [1,0,0,0]. Internally, this is represented as a "roaring bitmap", which can efficiently encode both sparse and dense sets.

3. Iterate over the bitset(s)

    Once the bitsets are generated for each query, Elasticsearch iterates over the bitsets to find the set of matching documents that satisfy all filtering criteria. The order of execution is decided heuristically, but generally the most sparse bitset is iterated on first (since it excludes the largest number of documents).

4. Increment the usage counter.

    Elasticsearch can cache non-scoring queries for faster access, but its silly to cache something that is used only rarely. Non-scoring queries are already quite fast due to the inverted index, so we only want to cache queries we know will be used again in the future to prevent resource wastage.

    To do this, Elasticsearch tracks the history of query usage on a per-index basis. If a query is used more than a few times in the last 256 queries, it is cached in memory. And when the bitset is cached, caching is omitted on segments that have fewer than 10,000 documents (or less than 3% of the total index size). These small segments tend to disappear quickly anyway and it is a waste to associate a cache with them.

### Combine filters

Recall boolean operation from elasticsearch:

```js
{
   "bool" : {
      "must" :     [], // All of these clauses must match. The equivalent of AND.
      "should" :   [], // At least one of these clauses must match. The equivalent of OR.
      "must_not" : [], // All of these clauses must not match. The equivalent of NOT.
      "filter":    [] // Clauses that must match, but are run in non-scoring, filtering mode.
   }
}
```

Learn by example:

```
GET /bd-data/city-temperatures/_search
{
   "query" : {
      "constant_score" : {
         "filter" : {
            "bool" : {
              "should" : [
                 { "term" : { "state" : "Washington"}},
                 { "term" : { "state" : "California"}}
              ]
           }
         }
      }
   }
}
```

Above is the same as the following sql statement:

```sql
SELECT *
FROM city-temperatures
WHERE state = "Washington" OR state = "California";
# or
SELECT *
FROM city-temperatures
WHERE state IN ("Washington", "California");
```

Does elasticsearch has something like `IN` sql operator? Sometimes we just need to find multiple exact values.

```
GET /bd-data/city-temperatures/_search
{
    "query" : {
        "constant_score" : {
            "filter" : {
                "terms" : {
                    "state" : ["Washington", "California"]
                }
            }
        }
    }
}
```

Consider the below sql query:

```sql
SELECT *
FROM   `city-temperatures`
WHERE  averageTemperature BETWEEN 20 AND 40
```

How do we translate this query to elasticsearch query?

```
GET /bd-data/city-temperatures/_search
{
    "query" : {
        "constant_score" : {
            "filter" : {
                "range" : {
                    "averageTemperature" : {
                        "gte" : 20,
                        "lt"  : 40
                    }
                }
            }
        }
    }
}
```

Cool, that explains the numerical number query. What about date/time? Same idea.

```
GET /bd-data/city-temperatures/_search
{
    "query" : {
        "constant_score" : {
            "filter" : {
                "range" : {
                    "date" : {
                        "gte" : "2012-01-01",
                        "lt"  : "2013-01-01"
                    }
                }
            }
        }
    }
}
```

### Full text search example

https://www.elastic.co/guide/en/elasticsearch/guide/current/full-text-search.html

```
http://localhost:5601/app/sense?load_from=https://www.elastic.co/guide/en/elasticsearch/guide/current/snippets/100_Full_Text_Search/05_Match_query.json
```

Elasticsearch executes the preceding match query as follows:

1. Check the field type.

    The title field is a full-text (analyzed) string field, which means that the query string should be analyzed too.

2. Analyze the query string.

    The query string QUICK! is passed through the standard analyzer, which results in the single term quick. Because we have just a single term, the match query can be executed as a single low-level term query.

3. Find matching docs.

    The term query looks up quick in the inverted index and retrieves the list of documents that contain that term—in this case, documents 1, 2, and 3.

4. Score each doc.

    The term query calculates the relevance `_score` for each matching document, by combining the term frequency (how often quick appears in the title field of each document), with the inverse document frequency (how often quick appears in the title field in all documents in the index), and the length of each field (shorter fields are considered more relevant).

### Aggregation

To master aggregations, you need to understand only two main concepts:

* Buckets

    Collections of documents that meet a criterion

* Metrics

    Statistics calculated on the documents in a bucket

**Buckets**

A bucket is simply a collection of documents that meet certain criteria:

* An employee would land in either the male or female bucket.
* The city of Albany would land in the New York state bucket.
* The date 2014-10-28 would land within the October bucket.

**Metrics**

Most metrics are simple mathematical operations (for example, min, mean, max, and sum) that are calculated using the document values. In practical terms, metrics allow you to calculate quantities such as the average salary, or the maximum sale price, or the 95th percentile for query latency.

Aggregation example in sense:

```
http://localhost:5601/app/sense?load_from=https://www.elastic.co/guide/en/elasticsearch/guide/current/snippets/300_Aggregations/20_basic_example.json
```

```sh
GET /cars/transactions/_search
{
    "size" : 0,
    "aggs" : {  # aggregation syntax
        "popular_colors" : {
            "terms" : { # bucket by color
              "field" : "color"
            }
        }
    }
}
```

You can also combine bucket with metrics like below:

```sh
GET /cars/transactions/_search
{
   "size" : 0,
   "aggs": {
      "colors": {
         "terms": {
            "field": "color"
         },
         "aggs": { # with another aggregation within aggregation!
            "avg_price": {
               "avg": {
                  "field": "price"
               }
            }
         }
      }
   }
}
```

Bucket in bucket with metric aggregation!

```
GET /cars/transactions/_search
{
   "size" : 0,
   "aggs": {
      "colors": {
         "terms": {
            "field": "color"
         },
         "aggs": {
            "avg_price": { "avg": { "field": "price" }
            },
            "make" : {
                "terms" : {
                    "field" : "make"
                },
                "aggs" : {
                    "min_price" : { "min": { "field": "price"} },
                    "max_price" : { "max": { "field": "price"} }
                }
            }
         }
      }
   }
}
```

**Histogram**

```sh
GET /cars/transactions/_search
{
   "size" : 0,
   "aggs":{
      "price":{
         "histogram":{ # using histogram we can build many graph with it (bar, pie or histogram!)
            "field": "price",
            "interval": 20000
         },
         "aggs":{
            "revenue": {
               "sum": {
                 "field" : "price"
               }
             }
         }
      }
   }
}
```

What about time series variables?

```
http://localhost:5601/app/sense?load_from=https://www.elastic.co/guide/en/elasticsearch/guide/current/snippets/300_Aggregations/35_date_histogram.json
```

Sky's the limit!

![Kibana graph](https://www.elastic.co/guide/en/elasticsearch/guide/current/images/elas_29in03.png)

Sometimes, you want to scope your aggregation. Such as searching only ford cars rather than searching everything all together.

This case you can do query/filter before doing aggregation.

```
http://localhost:5601/app/sense?load_from=https://www.elastic.co/guide/en/elasticsearch/guide/current/snippets/300_Aggregations/40_scope.json
```

Furthermore, if your data has geo location related data. You may want to consider reading through this document:

https://www.elastic.co/guide/en/elasticsearch/guide/current/geoloc.html

### Modeling your data for elasticsearch

Well, elasticsearch provides so many great benefits especially on its performance.

Why don't we use elasticsearch for everything (to replace traditional relational database)?

On one hand, elasticsearch does various things very well. It still has a few points it doesn't scale well.

For instance, handling relationships in elasticsearch is non-trivial.

In short, you want to model your data in elasticsearch or nosql database as flat as possible as opposite to having more relationships in RDBMS.

However, if you still need relationships for your data. Elasticsearch suggests the following approaches:

1. Application side join
2. Data de-normalization
3. Nested objects
4. Parent/child relationships

**Application side join**

Application side join basically have your applicaiton to simulate joins. Consider the following:

```
PUT /my_index/user/1
{
  "name":     "John Smith",
  "email":    "john@smith.com",
  "dob":      "1970/10/24"
}

PUT /my_index/blogpost/2
{
  "title":    "Relationships",
  "body":     "It's complicated...",
  "user":     1
}
```

Then your application would take blogpost and use user as the id to look for data at the `/my_index/user/` + index

**Data de-normalization**

In short, you intentionally store the same data in multiple places like the following:

```
PUT /my_index/user/1
{
  "name":     "John Smith",
  "email":    "john@smith.com",
  "dob":      "1970/10/24"
}

PUT /my_index/blogpost/2
{
  "title":    "Relationships",
  "body":     "It's complicated...",
  "user":     {
    "id":       1,
    "name":     "John Smith"
  }
}
```

This may require you to do locking on your application side in order to update multiple fields at once. I'll be skipping this part of document. If you are interested of learning out more on your own and wanting to apply this knowledge to production. Please look up above on your own.

**Nested objects**

```
PUT /my_index/blogpost/1
{
  "title": "Nest eggs",
  "body":  "Making your money work...",
  "tags":  [ "cash", "shares" ],
  "comments": [
    {
      "name":    "John Smith",
      "comment": "Great article",
      "age":     28,
      "stars":   4,
      "date":    "2014-09-01"
    },
    {
      "name":    "Alice White",
      "comment": "More like this please",
      "age":     31,
      "stars":   5,
      "date":    "2014-10-22"
    }
  ]
}
```

The primary difference between denormalization and nested objects is array vs objects.

You can still perform the search like `comments.name` (this is why you cannot have `.` in your attribute name).

> Please note that if you want to use nested object. Don't rely on the dynamic mapping provided by elastic search. Create the mapping ahead of time before you insert your data.

**Parent/child relationship**

Due to the time constraint, we will skip this part of notes.

## Kibana in action

Demo
