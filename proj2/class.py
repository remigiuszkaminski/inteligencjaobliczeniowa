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

# data = pd.read_csv('housing_preprocessed.csv', sep=',', header=0)
data = pd.read_csv('housing_preprocessed2.csv', sep=',', header=0)
print(data.columns)



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


dtc_small = DecisionTreeClassifier(max_depth=5, random_state=1234)
dtc_large = DecisionTreeClassifier(max_depth=10, random_state=1234)
nb = GaussianNB()
knn = KNeighborsClassifier(n_neighbors=3)
knn2 = KNeighborsClassifier(n_neighbors=10)
knn3 = KNeighborsClassifier(n_neighbors=20)
mlp = MLPClassifier(hidden_layer_sizes=(20, 20), max_iter=1500, random_state=1234)
mlp2 = MLPClassifier(hidden_layer_sizes=(20,), max_iter=1500, random_state=1234)
mlp3 = MLPClassifier(hidden_layer_sizes=(20, 10, 5), max_iter=1500, random_state=1234)

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

print('Dokładnosc dla: DTC small: ', accuracy_score(y_test, y_pred_dtc_small))
print('Dokładnosc dla: DTC large: ', accuracy_score(y_test, y_pred_dtc_large))
print('Dokładnosc dla: NB: ', accuracy_score(y_test, y_pred_nb))
print('Dokładnosc dla: KNN: ', accuracy_score(y_test, y_pred_knn))
print('Dokładnosc dla: KNN2: ', accuracy_score(y_test, y_pred_knn2))
print('Dokładnosc dla: KNN3: ', accuracy_score(y_test, y_pred_knn3))
print('Dokładnosc dla: MLP: ', accuracy_score(y_test, y_pred_mlp))
print('Dokładnosc dla: MLP2: ', accuracy_score(y_test, y_pred_mlp2))
print('Dokładnosc dla: MLP3: ', accuracy_score(y_test, y_pred_mlp3))

print('Macierz błędu dla: DTC small: ', cm_dtc_small)
print('Macierz błędu dla: DTC large: ', cm_dtc_large)
print('Macierz błędu dla: NB: ', cm_nb)
print('Macierz błędu dla: KNN: ', cm_knn)
print('Macierz błędu dla: KNN2: ', cm_knn2)
print('Macierz błędu dla: KNN3: ', cm_knn3)
print('Macierz błędu dla: MLP: ', cm_mlp)
print('Macierz błędu dla: MLP2: ', cm_mlp2)
print('Macierz błędu dla: MLP3: ', cm_mlp3)

