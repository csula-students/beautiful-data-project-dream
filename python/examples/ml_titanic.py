import pandas as pd
import numpy as np
from sklearn import svm

training_data = pd.read_csv('resources/train.csv', header=0)
testing_data = pd.read_csv('resources/test.csv', header=0)

# cleaning/munging data
training_data['Gender'] = training_data['Sex'].map( {'female': 0, 'male': 1} ).astype(int)
testing_data['Gender'] = testing_data['Sex'].map( {'female': 0, 'male': 1} ).astype(int)
training_data = training_data.drop(['Name', 'Sex', 'Ticket', 'Cabin', 'Embarked'], axis=1) 
testing_data = testing_data.drop(['Name', 'Sex', 'Ticket', 'Cabin', 'Embarked'], axis=1) 
training_data = training_data.dropna()
testing_data = testing_data.dropna()

training_labels = training_data[['Survived']]
training_features = training_data.drop(['Survived'], axis=1)


# Debugging
print training_features.head()
print training_labels.head()

# instantiating classifier
clf = svm.SVC(gamma=0.001, C=100.)

# train classifier
clf.fit(training_features, training_labels)

print clf.predict(testing_data[-1:])
