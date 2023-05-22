import numpy as np
from sklearn.tree import DecisionTreeClassifier
from sklearn.naive_bayes import GaussianNB
from sklearn.neighbors import KNeighborsClassifier
from sklearn.neural_network import MLPClassifier
from sklearn.metrics import accuracy_score, confusion_matrix
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.discriminant_analysis import StandardScaler
from sklearn.preprocessing import LabelEncoder, MinMaxScaler

data = pd.read_csv('housing.csv', sep=',', header=0)
print(data.columns)
data = data.drop("ocean_proximity", axis=1)
data = data.fillna(0)
bins = [0, 50000, 100000, 150000, 200000, 250000, 300000, 350000, 400000, 450000, 500000, float('inf')] # 6 bins
data['median_house_value_category'] = pd.cut(data['median_house_value'], bins=bins, labels=False, right=False)

X = data.iloc[:, :-1].values
y = data.iloc[:, -1].values

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=1234)

label_encoder = LabelEncoder()
y_all = label_encoder.fit_transform(np.concatenate([y_train, y_test]))
y_train = y_all[:len(y_train)]
y_test = y_all[len(y_train):]

scaler = StandardScaler()
scaler.fit(X_train)
X_train = scaler.transform(X_train)
X_test = scaler.transform(X_test)

normalizer = MinMaxScaler()
normalizer.fit(X_train)
X_train = normalizer.transform(X_train)
X_test = normalizer.transform(X_test)


dtc_small = DecisionTreeClassifier(max_depth=3, random_state=1234)
dtc_large = DecisionTreeClassifier(max_depth=10, random_state=1234)
nb = GaussianNB()
knn = KNeighborsClassifier(n_neighbors=3)
knn2 = KNeighborsClassifier(n_neighbors=10)
knn3 = KNeighborsClassifier(n_neighbors=20)
mlp = MLPClassifier(hidden_layer_sizes=(10, 10), max_iter=1000, random_state=1234)
mlp2 = MLPClassifier(hidden_layer_sizes=(10,), max_iter=1000, random_state=1234)
mlp3 = MLPClassifier(hidden_layer_sizes=(10, 6, 3), max_iter=1000, random_state=1234)

dtc_small.fit(X_train, y_train)
dtc_large.fit(X_train, y_train)
nb.fit(X_train, y_train)
knn.fit(X_train, y_train)
knn2.fit(X_train, y_train)
knn3.fit(X_train, y_train)
mlp.fit(X_train, y_train)
mlp2.fit(X_train, y_train)
mlp3.fit(X_train, y_train)

y_pred_dtc_small = dtc_small.predict(X_test)
y_pred_dtc_large = dtc_large.predict(X_test)
y_pred_nb = nb.predict(X_test)
y_pred_knn = knn.predict(X_test)
y_pred_knn2 = knn2.predict(X_test)
y_pred_knn3 = knn3.predict(X_test)
y_pred_mlp = mlp.predict(X_test)
y_pred_mlp2 = mlp2.predict(X_test)
y_pred_mlp3 = mlp3.predict(X_test)

cm_dtc_small = confusion_matrix(y_test, y_pred_dtc_small)
cm_dtc_large = confusion_matrix(y_test, y_pred_dtc_large)
cm_nb = confusion_matrix(y_test, y_pred_nb)
cm_knn = confusion_matrix(y_test, y_pred_knn)
cm_knn2 = confusion_matrix(y_test, y_pred_knn2)
cm_knn3 = confusion_matrix(y_test, y_pred_knn3)
cm_mlp = confusion_matrix(y_test, y_pred_mlp)
cm_mlp2 = confusion_matrix(y_test, y_pred_mlp2)
cm_mlp3 = confusion_matrix(y_test, y_pred_mlp3)

print('DTC small: ', accuracy_score(y_test, y_pred_dtc_small))
print('DTC large: ', accuracy_score(y_test, y_pred_dtc_large))
print('NB: ', accuracy_score(y_test, y_pred_nb))
print('KNN: ', accuracy_score(y_test, y_pred_knn))
print('KNN2: ', accuracy_score(y_test, y_pred_knn2))
print('KNN3: ', accuracy_score(y_test, y_pred_knn3))
print('MLP: ', accuracy_score(y_test, y_pred_mlp))
print('MLP2: ', accuracy_score(y_test, y_pred_mlp2))
print('MLP3: ', accuracy_score(y_test, y_pred_mlp3))

print('DTC small: ', cm_dtc_small)
print('DTC large: ', cm_dtc_large)
print('NB: ', cm_nb)
print('KNN: ', cm_knn)
print('KNN2: ', cm_knn2)
print('KNN3: ', cm_knn3)
print('MLP: ', cm_mlp)
print('MLP2: ', cm_mlp2)
print('MLP3: ', cm_mlp3)

