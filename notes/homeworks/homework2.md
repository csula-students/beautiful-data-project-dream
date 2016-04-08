# Homework 2

> This is your first team project. Please do form a team before you start your homework 2!

## Description

In this homework, your job is to acquire data by implementing our data collector interface with your own detail. This may involves you to download data using Http request library, to do web scraping or to make some API calls.

This implementation may vary a lot between all teams depending on the data source you are getting from. In example, a team who wants to get Twitter tweets to compute trending will have different implementation than a team who is getting data from bunch of sensors!

Since this homework is your first **team homework**, you will need to use Github by starting your team repository. This implies you will need to be able to use Git effectively enough to commit and push code to collaborate with your teammate(s) and instructor(s). If you find yourself having trouble of doing git, please utilize [this article][1] for your benefit or play with it on [this link][2]!

This homework will involve you to program your own acquiring process. It means it is important for you to follow up with your questions from homework 1. Now, since you will be all working in a team, you will need to decide to work on question or questions from your team members. Once you decide to do so, please answer the question below so that we know which question you are working on.

**Questions**

* What question(s) did you decide to work on as a team?
* What is your data source(s)?
* How long does it take for you to download data? Have you download complete data set?
* How large is your data (size wise and number of records wise)?
* Do you face any dirty data issue? If you do, how did you clean up your data?
* How do you store the data you downloaded?

**Testing**

Since we are downloading data from internet, how in the world we suppose to test our process!?

You don't have to test the whole process from end to end. Instead, think about what process can you simplify out to just Java programming and test only that part. In example, you may have a *model* to represent data like below:

```java
// import statements

public class Model {
  private final String id;
  private final String content;
  private final String city;
  private final int area;
  private final LocalDateTime timestamp;
  // constructors, getters, setters and other model related methods
}
```

Then my first question to you is to test data validity. Lets say if you read the data online as a String and construct your own data model. How do you handle the case of missing fields? Do you throw exceptions?

All these question can be tested with test case like:

```java
// import statements

public class ModelTest {
  @Test(expected=CityNotFoundException.class)
  public void TestCity() {
    String city = null;
    Model.setCity(city);
  }
}
```

In other word, think about all the edge cases and your Java collector implementation detail behavior before you start working on the code. Design an module that has clear defined dependencies so that it is testable.

But how do instructors measure your testing? I will set up the test coverage on each of your repository and see your test coverage number. I expect test coverage to be at least 30%. As long as the test coverage shows higher than 30%, you will get the test coverage points below.

## Tasks

* Create team repo
* Push initial commit
* Implement your data collector
* Answer questions in description

## Deliverables

* CSNS submission including team info and link to your repo
* Github pull request
  * Answer questions in the description of pull request
  * Implement data collector
  * Test your own data collector

## Grading rubric

* Github pull request [1 pt]
* Answer questions in description [2 pts]
* Implement data collector [5 pts]
* Testing your own data collector [2 pts]

[1]: https://github.com/csula/cs460-fall-2015/blob/master/documents/misc/github-tutorial.md
[2]: https://try.github.io/levels/1/challenges/1
