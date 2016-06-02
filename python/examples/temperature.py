import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# read from csv and parse as DataFrame
avg_temperature = pd.read_csv('resources/GlobalLandTemperaturesByState.csv')

# print stats summary
print avg_temperature.describe()

# print type and found out dt is object type
print avg_temperature.dtypes

# convert dt into time series
avg_temperature.dt = pd.to_datetime(avg_temperature.dt)

# check type again
print avg_temperature.dtypes

# now we can set the index
avg_temperature = avg_temperature.set_index(avg_temperature.dt)

# check index
print avg_temperature.head()

# we can groupby country and resample to look at data year by year
print avg_temperature.groupby('Country').resample('A').mean()

# visualize in pyplot
united_states_temperatures = avg_temperature[avg_temperature.Country=='United States']
united_states_temperatures = united_states_temperatures.resample('A').mean()
plt.figure(); united_states_temperatures.plot(); plt.legend(loc='best')
plt.show()
