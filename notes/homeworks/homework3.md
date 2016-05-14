# Homework 3

## Due Date

May 29th Midnight.

## Description

Exploratory data anylysis.

In this homework, your job is to create your initial presentation on your EDA. The deliverable for this homework is to create a presentation and submit your link on CSNS.

So what is EDA? Recall from the data_analysis.md earlier:

> The objectives of EDA are to:
> 
> * Suggest hypotheses about the causes of observed phenomena
> * Assess assumptions on which statistical inference will be based
> * Support the selection of appropriate statistical tools and techniques
> * Provide a basis for further data collection through surveys or experiments`

Your job is to create a presentation containing graph about your data and answer each of the point above.

## Tasks

1. Aggregate data
2. Generate graph
3. Do statistical analysis based on your aggregation
 
A hint about creating these graph and aggregation:

You can utilize Elastic Search and Kibana to generate such graphs. To do so, you will need to import data into Elastic Search first. Please review the ElasticSearchExampleApp.java.

Using Elastic Search implies your tasks would be like below:

1. Import your data into Elastic Search

  > When you import data, plesae make sure your data has timestamp or any time related data first.

2. Check if your mapping is correct by `_mapping`
3. If not correct, you should be deleting your entire set of data and generate your mapping first.

  > If you do change your mapping, please submit your mapping as part of description in Pull Request

4. Start up Kibana and start visualizing with your data set

  > From this moment, you should be thinking about what type of graph you should be using. For instance, bar chart, pie chart, line chart ... etc.  
  > Once you are satisfied with your visualization. Save it. You will need to use these later

5. Create dashboard containing all your visualization

  > Make sure you have those visualization saved from 4th step

## Deliverables

* CSNS submission containing link your presentation slides and link to youtube video you guys present
* Answer each statement above
* Pull request containing all the coding changes you have

## Grading rubric

* CSNS submission [1 pts]
* Answer each statement in objectives of EDA above [2 pts]
* Presentation [7 pts]
