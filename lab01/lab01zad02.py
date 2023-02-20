import pandas as pd
#Wczytywanie danych z pliku csv
miasta = pd.read_csv('miasta.csv')
print(miasta)
print(miasta.values)

#Dodawanie nowego wiersza z ludnością w 2010 roku
row = pd.Series([2010, 460, 555, 405], index=miasta.columns)
miasta = miasta.append(row, ignore_index=True)
print(miasta)

#Stworzenie wykresu dla ludności w Gdańsku
import matplotlib.pyplot as plt
plt.plot(miasta['Rok'], miasta['Gdansk'], 'r.-')
plt.title('Ludność w Gdańsku w latach 1900-2010')
plt.xlabel('Lata')
plt.ylabel('Liczba ludności [w tys.]')
plt.show()

#Stworzenie wykresu dla ludności w Gdańsku, Poznaniu i Szczecinie w latach 1900-2010 wraz z legendą
plt.plot(miasta['Rok'], miasta['Gdansk'], 'r.-', label='Gdańsk')
plt.plot(miasta['Rok'], miasta['Poznan'], 'g.-', label='Poznań')
plt.plot(miasta['Rok'], miasta['Szczecin'], 'b.-', label='Szczecin')
plt.title('Ludność w Gdańsku, Poznaniu i Szczecinie w latach 1900-2010')
plt.xlabel('Lata')
plt.ylabel('Liczba ludności [w tys.]')
plt.legend()
plt.show()



#Standaryzacja danych bez roku, oraz podanie średniej i odchylenia standardowego po standaryzacji
miasta2 = miasta.set_index('Rok')
miasta2 = (miasta2 - miasta2.mean()) / miasta2.std()
print(miasta2)
print(miasta2.mean())
print(miasta2.std())


#Normalizacja danych bez roku, oraz podanie maksymalnej i minimalnej wartości po normalizacji
miasta3 = miasta.set_index('Rok')
miasta3 = (miasta3 - miasta3.min()) / (miasta3.max() - miasta3.min())
print(miasta3)
print(miasta3.max())
print(miasta3.min())


