# import matplotlib.pyplot as plt
# import numpy as np
# methods = ['DenseMatrixA1', 'BandMatrixA1', 'SparseMatrixA1', 'DenseMatrixA2', 'BandMatrixA2', 'SparseMatrixA2']
# times = [2.4257826899009464E-12, 4.0908308932983885E-15, 1.1370227818512004E-10, 2.3699340635162088E-14, 1.0029500389647629E-15, 1.188577489963296E-13]

# x_pos = np.arange(len(methods))

# plt.bar(x_pos, times, align='center', alpha=0.5)
# plt.xticks(x_pos, methods)
# plt.ylabel('Błąd')
# plt.yscale('log')
# plt.title('Średni błąd algorytmów dla różnych typów macierzy')

# plt.show()

# data = {
#     "DenseMatrixA1": 3.473042171641366E-12,
#     "BandMatrixA1": 1.917789068352151E-15,
#     "SparseMatrixA1": 1.8861272319340628E-11,
#     "DenseMatrixA2": 4.2464911327369225E-14,
#     "BandMatrixA2": 1.6117839800046077E-15,
#     "SparseMatrixA2": 1.4039918621756432E-14
# }


# avg_a1 = (data["DenseMatrixA1"] + data["BandMatrixA1"] + data["SparseMatrixA1"]) / 3
# avg_a2 = (data["DenseMatrixA2"] + data["BandMatrixA2"] + data["SparseMatrixA2"]) / 3

# methods = ['A1', 'A2']
# times = [avg_a1, avg_a2]

# x_pos = np.arange(len(methods))

# plt.bar(x_pos, times, align='center', alpha=0.5)
# plt.xticks(x_pos, methods)
# plt.ylabel('Błąd')
# plt.yscale('log')
# plt.title('Średnie błędy dla różnych metod')

# plt.show()

# import pandas as pd
import matplotlib.pyplot as plt

# df = pd.read_csv('../resultValues.csv', delimiter=';')

# plt.plot(df['Size'], df['DenseMatrixA2'], label='DenseMatrixA2')

# plt.xlabel('Wielkość')
# plt.ylabel('Błąd')
# plt.title('DenseMatrixA2')

# plt.legend()

# plt.show()

# data = pd.read_csv("../resultValues.csv", delimiter=';')
# plt.plot(df['Size'], df['BandMatrixA2'], label='BandMatrixA2')
# plt.xlabel('Wielkość')
# plt.ylabel('Błąd')
# plt.title('BandMatrixA2')

# plt.legend()

# plt.show()

# data = pd.read_csv("../resultValues.csv", delimiter=';')
# plt.plot(df['Size'], df['SparseMatrixA2'], label='SparseMatrixA2')
# plt.xlabel('Wielkość')
# plt.ylabel('Błąd')
# plt.title('SparseMatrixA2')

# plt.legend()

# plt.show()

data1 = [    (50, 152565),    (100, 759830),    (150, 1077745),    (200, 1561925),    (250, 2352525),    (300, 3774090),    (350, 6020330),    (400, 10002260),    (450, 14892820),    (500, 21211030),    (550, 29245055),    (600, 39931320),    (650, 53398639),    (700, 70696039),    (750, 92044894),    (800, 119420269),    (850, 153556984),    (900, 197124669),    (950, 321133179),    (1000, 429154644)]

data2 = [    (50, 202534),    (100, 810294),    (150, 2194024),    (200, 3574544),    (250, 4588604),    (300, 6269604),    (350, 8762174),    (400, 12626840),    (450, 18068034),    (500, 25840234),    (550, 35839724),    (600, 50133489),    (650, 66327049),    (700, 86000259),    (750, 110841304),    (800, 142614599),    (850, 186673709),    (900, 242739979),    (950, 322876129),    (1000, 410736819)]


x1, y1 = zip(*data1)
x2, y2 = zip(*data2)

fig, ax = plt.subplots()

ax.plot(x1, y1, label="A2")
ax.plot(x2, y2, label="A1")

ax.set_xlabel("Rozmiar macierzy")
ax.set_ylabel("Czas [ns]")
ax.legend()

plt.show()