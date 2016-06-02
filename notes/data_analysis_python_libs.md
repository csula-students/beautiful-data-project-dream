#### Numpy

Provides various utilities functions on top of N-dimensional array such statistical calculation (.mean(), .std()) and vectorized operation.

#### Pandas

Provides high-performance, easy to use data structures and data analytics tools in Python.

#### One dimensional data structure comparison

| Python | NumPy | Pandas |
| ------ | ----- | ------ |
| List   | Array | Series |
| Simple | More features | Even more features |
| Access by index, range of indexes, use loop | each element has same data type, vectorized operation, .mean() and .std() | .describe() |

#### NumPy examples

```python
>>> from numpy  import *
>>> a = arange(15).reshape(3, 5)
>>> a
array([[ 0,  1,  2,  3,  4],
       [ 5,  6,  7,  8,  9],
       [10, 11, 12, 13, 14]])
>>> a.shape
(3, 5)
>>> a.ndim
2
>>> a.dtype.name
'int32'
>>> a.itemsize
4
>>> a.size
15
>>> type(a)
numpy.ndarray
>>> b = array([6, 7, 8])
>>> b
array([6, 7, 8])
>>> type(b)
numpy.ndarray
```

Array creation

```python
>>> from numpy import *
>>> a = array( [2,3,4] )
>>> a
array([2, 3, 4])
>>> a.dtype
dtype('int32')
>>> b = array([1.2, 3.5, 5.1])
>>> b.dtype
dtype('float64')
```

Array operations

```python
>>> a = array( [20,30,40,50] )
>>> b = arange( 4 )
>>> b
array([0, 1, 2, 3])
>>> c = a-b
>>> c
array([20, 29, 38, 47])
>>> b**2
array([0, 1, 4, 9])
>>> 10*sin(a)
array([ 9.12945251, -9.88031624,  7.4511316 , -2.62374854])
>>> a<35
array([True, True, False, False], dtype=bool)
```

Linear algebra review:

Review [https://en.wikipedia.org/wiki/Matrix_(mathematics)](https://en.wikipedia.org/wiki/Matrix_(mathematics)) for vectorized operations.

```python
>>> A = array( [[1,1],
...             [0,1]] )
>>> B = array( [[2,0],
...             [3,4]] )
>>> A*B                         # elementwise product
array([[2, 0],
       [0, 4]])
>>> dot(A,B)                    # matrix product
array([[5, 4],
       [3, 4]])
```

```python
>>> a = ones((2,3), dtype=int)
>>> b = random.random((2,3))
>>> a *= 3
>>> a
array([[3, 3, 3],
       [3, 3, 3]])
>>> b += a
>>> b
array([[ 3.69092703,  3.8324276 ,  3.0114541 ],
       [ 3.18679111,  3.3039349 ,  3.37600289]])
>>> a += b                                  # b is converted to integer type
>>> a
array([[6, 6, 6],
       [6, 6, 6]])
```

```python
>>> a = random.random((2,3))
>>> a
array([[ 0.6903007 ,  0.39168346,  0.16524769],
       [ 0.48819875,  0.77188505,  0.94792155]])
>>> a.sum()
3.4552372100521485
>>> a.min()
0.16524768654743593
>>> a.max()
0.9479215542670073
```

Show histogram

```python
import numpy
import pylab
# Build a vector of 10000 normal deviates with variance 0.5^2 and mean 2
mu, sigma = 2, 0.5
v = numpy.random.normal(mu,sigma,10000)
# Plot a normalized histogram with 50 bins
pylab.hist(v, bins=50, normed=1)       # matplotlib version (plot)
pylab.show()
# Compute the histogram with numpy and then plot it
(n, bins) = numpy.histogram(v, bins=50, normed=True)  # NumPy version (no plot)
pylab.plot(.5*(bins[1:]+bins[:-1]), n)
pylab.show()
```

#### Pandas

Series = 1 dimensional data
DataFrame = N dimensional data

Example

```python
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# Create series
s = pd.Series([1,3,5,np.nan,6,8])
dates = pd.date_range('20130101', periods=6)

# Create DataFrame
df = pd.DataFrame(np.random.randn(6,4), index=dates, columns=list('ABCD'))
df2 = pd.DataFrame({ 'A' : 1.,
                      'B' : pd.Timestamp('20130102'),
                      'C' : pd.Series(1,index=list(range(4)),dtype='float32'),
                      'D' : np.array([3] * 4,dtype='int32'),
                      'E' : pd.Categorical(["test","train","test","train"]),
                      'F' : 'foo' })

# you can check type here
df2.dtypes

# Viewing data
df.head()
df.tail(3)

# showing indexes names
df.index

# quick statistics
df.describe()

# sorting by indexes
df.sort_index(axis=1, ascending=False)

# or by values
df.sort_values(by='B')

# select by position
df.iloc[3]

# use boolean to filter out rows
df[df.A > 0]

# Missing data? Pandas has way to deal with it
df1 = df.reindex(index=dates[0:4], columns=list(df.columns) + ['E'])
df1.loc[dates[0]:dates[1],'E'] = 1
df1.dropna(how='any') # drop any rows having NaN in value
df1.fillna(value=5)   # or fill with default value for NaN

# Stats
df.mean() # for the entire two dimensional data
df.mean(1) # or just one column

# apply() method is the same as map method, 
# you transform each element in DataFrame by calling apply()
df.apply(np.cumsum)

# histogram
data = np.random.randint(0, 7, size=50)
s = pd.Series(data)
s.value_counts()

# merge two DataFrames
df = pd.DataFrame(np.random.randn(10, 4))

pieces = [df[:3], df[3:7], df[7:]]

# can even join
left = pd.DataFrame({'key': ['foo', 'foo'], 'lval': [1, 2]})
right = pd.DataFrame({'key': ['foo', 'foo'], 'rval': [4, 5]})
pd.merge(left, right, on='key')

# group by? Panda got you
df = pd.DataFrame({'A' : ['foo', 'bar', 'foo', 'bar',
                           'foo', 'bar', 'foo', 'foo'],
                    'B' : ['one', 'one', 'two', 'three',
                           'two', 'two', 'one', 'three'],
                    'C' : np.random.randn(8),
                    'D' : np.random.randn(8)})

df.groupby('A').sum()
df.groupby(['A','B']).sum()

# Time series data
rng = pd.date_range('1/1/2012', periods=100, freq='S')
ts = pd.Series(np.random.randint(0, 500, len(rng)), index=rng)
ts.resample('5Min').sum()
ts.head()

# Change frequency of time series data
converted = ts.asfreq('45Min', method='pad')
converted.head()
# get mean by day
ts.resample('D').mean()

# plotting graph
ts = pd.Series(np.random.randn(1000), index=pd.date_range('1/1/2000', periods=1000))
ts = ts.cumsum()
ts.plot()

df = pd.DataFrame(np.random.randn(1000, 4), index=ts.index,
                   columns=['A', 'B', 'C', 'D'])
df = df.cumsum()
plt.figure(); df.plot(); plt.legend(loc='best')

# i/o from/to csv
df.to_csv('foo.csv')
pd.read_csv('foo.csv')
```
