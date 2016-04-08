# Data Acquisition

To collect data ... in any format your question may require.

But ... most of time, data comes in with some variety.

## Objectives

* Data variety
* Common data acquisition problems
* Data quality
* Java data collector
* Data cleaning
* Git tutorial
* Set up team repository
* [Homework 2](homeworks/homework2.md)

## Metrics

* Java data collector
* Team repo
* Initial storage concern
* Test test test

### Data variety (problems)

When acquiring data, they usually come in with some variety.

Therefore, it is important for data scientists to have ability to work with this
variety. In other word, data scientists need to be able to:
* Test about assumptions about data
  * Value
  * Data type
  * Shape
* Identify errors or outliers
* Finding missing values

### How do we acquire data?

* Http request
* Web scraping
* Api calls

### Common data format

* Tabular data
* CSV
* JSON
* HTML
* Binary files

### Extract data

Upon download data, you will usually need to extra data out of its raw format.

In example, you might need to deserialize from JSON to your Java object. You might
need to extract HTML and read its text or even to download more content from this
extracted content.

### Data cleaning

After initial extraction, you will soon find out not all data are ideal. Some of
them may be missing and while some of them may just be *off*.

How do we deal with dirty data?

We need perform what is called data cleaning (e.g. removing duplicated entity)

Keep it in mind that data cleaning is an iterative process. In other word, you
might need to perform multiple iterations in order to get to your ideal data format.
Sometimes, this may involve changing the *shape* of the data.

### Data quality

* Validity
> Conforms a schema

* Accuracy
> Conforms to gold standard

* Completeness
> Contains **all** records

* Consistency
> Same data may show up twice with different values

* Uniformity
> Same unit

### Blue print of cleaning

1. Audit your data
2. Create data cleaning plans
  * Identify causes
  * Define operations
  * Test your theory
3. Execute the plan
4. Manually correct

### Data storage concern

Follow up with discussion, data comes in with variety. This implies traditional relational database may have hard time to store the data due to unknown shape. Therefore, it is common to store the data in **plain file** or to store in **NoSQL database**.

Since we will be learning MongoDB in next class. I'd suggest you to store all data in plain file format. Can be just in CSV or JSON as long as you can read the file later.

But it's just a plain file we are writing. What about performance?

File format is not that bad. Think about HDFS, it's just plain file! But underlying implementation, HDFS partition the files and do parallel processing on top. In short, plain file may not be the worst in performance.

## Programming time

* Cli
* Git
* Collector Interface
