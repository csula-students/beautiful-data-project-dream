# Homework 3

## Due Date

May 29th Midnight.

## Description

Exploratory Data Analysis (EDA).  So what is EDA? Recall from the [data_analysis.md](../data_analysis.md) earlier:

> The objectives of EDA are to:
> 
> * Suggest hypotheses about the causes of observed phenomena
> * Assess assumptions on which statistical inference will be based
> * Support the selection of appropriate statistical tools and techniques
> * Provide a basis for further data collection through surveys or experiments

Your job is to create a presentation containing graph about your data.  Your presentation should answer each of the above.

## Tasks

In this homework, your job is to create your initial presentation on your EDA. The deliverable for this homework is to create a presentation and submit your link on CSNS.

In order to your presentation, your team will need to:

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

### Presentation Guideline

In order to preserve valuable lecture time, your team will need to create an online screencast using youtube, vimeo, or any other video distribution services.  Your video must be visible by a non-registered user.  However, it does not have to be listed in a public directory.  

Here is the guideline for the presentation:

- The screencast video must not require special pluggins (we recommend uploading to youtube)
- You **cannot** email or attach a video file, e.g. `.mov`, `.avi`, `.mpeg` etc.
- Your presentation must be between 1:40 and 2:00.  Videos with duration > 2:00 or < 1:40 will not be accepted
- Your presentation must be visually clear and have good audio; we recommend that you record at HD level

Please note that the above requirements are **not** negotiable.

Some recommendations:
- Avoid verbal pauses (ah and umm's should be avoided) -- when in doubt, practice.  You should script out what you are going to say ahead of time
- The whole idea of "winging it" is not recommended
- Avoid reading code or talking too low-level details
- We recommend the following presentation structure:
  - Title slide (presentation title and team members) -- introduce yourself and your team
  - Agenda slide, `here is our agenda...`
  - Background slide(s)
    - State your hypotheses
    - State your assumptions
  - Approach and methods
    - Talk about your data
    - Tools you use
  - Findings, give a one or two sentence punchline
- If you have live or animated data, recommend that you use keyboard shortcut to switch between slides and demo.
 
Some thoughts:
- It is estimated that, if this the first time doing an online presentation, you'd need to spend at least 1 hour to do the presentation justice.  Afterall, anything less than perfect is not worth doing.
- You will find yourself recording about 4 to 5 times before getting it perfect.  Afterall, anything less than perfect is not worth doing.  
- You should definitely watch your own video from start to finish.  Anything that does not sound right or not clear, you should re-do. Afterall, anything less than perfect is not worth doing.

## Deliverables

* CSNS submission containing the following:
  - list of team members
  - presentation title
  - link your presentation slides
  - link to the video screencast of your presentation
* Answer each statement above
* Pull request containing all the coding changes you have made

## Grading rubric

* CSNS submission [1 pts]
* Answer each statement in objectives of EDA above [2 pts]
* Presentation [7 pts]
