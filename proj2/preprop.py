import pandas as pd
import numpy as np
from sklearn.preprocessing import LabelEncoder

data = pd.read_csv('housing.csv', sep=',', header=0)
print(data.columns)
label_encoder = LabelEncoder()
data['ocean_proximity_encoded'] = label_encoder.fit_transform(data['ocean_proximity'])


data = data.fillna(0)
bins = [0, 50000, 100000, 150000, 200000, 250000, 300000, 350000, 400000, 450000, 500000, float('inf')]
data['median_house_value_category'] = pd.cut(data['median_house_value'], bins=bins, labels=False, right=False)
data = data.drop(['median_house_value', 'ocean_proximity'], axis=1)
data.to_csv('housing_preprocessed.csv', sep=',',index=False)