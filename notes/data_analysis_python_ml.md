## Intro to Machine learning

> Please note this class is not intended to learn Machine Learning in detail. If you want to learn more on Machine Learning, please take the appropriate class like CS-461.  

Above disclaimer doesn't mean we will not talk about Machine Learning at all. Instead, we will cover the Machine Learning algorithms like blackbox and use them as what they are intended for.

In short, if you want to learn the implementation for the Machine Learning, go to CS-461!

### Problems to solve

So what kind of problems does machine learning algorithms solve?

> In general, a learning problem considers a set of n samples of data and then tries to predict properties of unknown data. If each sample is more than a single number and, for instance, a multi-dimensional entry (aka multivariate data), is it said to have several attributes or features.  
> from http://scikit-learn.org/stable/tutorial/basic/tutorial.html

In short, you have a set of training data and you train your algorithms with them. After training your algorithm, you can feed some unknown data (which is not presented in the training data set) to predict the outcome.

### Decisions on algorithms

I found [this cheat sheet](http://scikit-learn.org/stable/tutorial/machine_learning_map/) to be very effective to select an algorithms on your problem. Please utilize it!

Cheat sheet:  
http://scikit-learn.org/stable/tutorial/machine_learning_map/

Briefly speaking in selection algorithms, you want to start by highest level with below two categories: *supervised* and *unsupervised* learning.

Supervised learning indicates your training set of data has a expected labels as outcome which we want to predict.

Within supervised learning algorithms, you will face another decision on either *classification* or *regression*.

**Classification** means you want to predict samples into two or mote classes. Such as handwritten digit recognition. You can think of this as discrete form of supervised learning where one has a limited number of categories and for each of the n samples provided, one is to try to label them with the correct category or class.

**Regression** if the desired outcome contains one or more continuous variables. An example of a regression problem would be the prediction of the length of a salmon as a function of its age and weight. (as you cannot discrete limit the number of length)

**Unsupervised** learning usually involves grouping similar data together like *k-nearest neighbors*.

### Training data set and testing data set

When feeding algorithms data, you want to split your data into training set and testing data set. They should be different so that your algorithms don't get overfitting into certain outcome.

But how much data should I provide as training set and test data set?

This is where the art of machine learning engineer lives, you have to play with experiments and figure out the proper number.

Briefly speaking, you can start 10% of data as testing set and test your accuracy accordingly.

### Brief workflow for sklearn

In this class, we will be using Python sklearn(scikit) for all Machine Learning algorithms. Please get used to their official site and their documentation -- http://scikit-learn.org/stable/index.html

Without going through the detail of implementation, your sklearn code will mostly look like below:

```python
# get the training set of data and test set of data
from sklearn import datasets
# luckily sklearn provides some sample data to start with
iris = datasets.load_iris()
digits = datasets.load_digits()

# import sklearn libraries
from sklearn import svm # for example

# instantiate your algorithm
clf = svm.SVC(gamma=0.001, C=100.)

# learn by `fit` or train your algorithm by providing features_test and features_labels
clf.fit(digits.data[:-1], digits.target[:-1])  

clf.predict(digits.data[-1:])

# to measure how good your algorithms are, you can measure accuracy by below:
clf.score(features_test, labels_test)

# what about you don't want to relearn your algorithm everytime you run it?
# you can save your algorithm using `pickle`
import pickle
s = pickle.dumps(clf)
# and load it up afterward
clf2 = pickle.loads(s)
```

### Agenda of the day

Without further due, lets get into a few selected algorithms.

In this class, we will talk very briefly on some algorithms that I think it's useful for your problem sets.

* Supervised learning
  * Naive Bayes algorithm
  * SVM (Support Vector Machine)
  * Decision tree
* Unsupervised learning
  * k-means
  * SVM (yes, this can also be used for unsupervised)

### Naive Bayes

http://scikit-learn.org/stable/modules/naive_bayes.html

Using Bayes rule to predict outcome.

It assumes independence between every pair of features.

You can start using this by using http://scikit-learn.org/stable/modules/generated/sklearn.naive_bayes.GaussianNB.html#sklearn.naive_bayes.GaussianNB

*Strength*

* Very easy to implement
* Supported in almost all languages
* Very simple to understand

*Weakness*

* Can break if the features have dependencies

### SVM (Support Vector Machine)

http://scikit-learn.org/stable/modules/svm.html

Basically finding a line to maximize the margin (distance to the nearest points). This sometimes implies ignoring outliers.

*Strength*

* Works very well when there is clear set of boundary even in complex situation

*Weakness*

* Training time can be slow when the data set is large
* When there is a lot of noise(outliers), this can overfitting to the data.

### Decision tree

http://scikit-learn.org/stable/modules/tree.html

In nutshell, you can think of decision tree to generate a list of if else statements. And predict function will run through the series of if else statement to get the outcome.

In more theoretical way of speaking, decision tree algorithm maximize the information gain (entropy of parent - weighted average of children).

*Strength*

* Very easy to use
* Can be very easy to understand as outcome

*Weakness*

* Can be overfitting sometimes

### k-means

http://scikit-learn.org/stable/modules/generated/sklearn.cluster.KMeans.html

You can think of k-means to split data set into k groups by grouping near data together.

Your argument will be k -- number of clusters.

It's the most common algorithm used for clustering unknown set of data.
