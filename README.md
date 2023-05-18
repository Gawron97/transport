# transport

Przemyślenia i obserwacje:

# zad. 1
Zrobiłem CRUDa, ale nie umożliwiłem wszystkim encją na wszystkie operacje CRUD. Jeśli chodzi o usuwanie, na pewno nie daje takiej możliwości dla encji Delivery, która po dostarczeniu otrzymuje status DELIVERED.
Myśle, że w przypadku większych projektów usuwanie niektórych encji doprowadziłoby do wielu konfliktów i zamiast usuwania lepiej ustawić status na np. INACTIVE.
Dopuszczam na przykład usuwanie encji Truck jak i Driver, co wymusza przejrzenie wszystkich encji Delivered i usunięcia powiązań z usuwaną encja, przez co niektóre przesyłki po usunięciu objektu np. Truck zostają
z bez powiazania. Tutaj nie chciałem zeby usunięcie objektu Truck, czy Driver usunęlo Delivery. Objekt Garage usuwam dopiero kiedy nie agreguje on żadnych objektów Truck/Driver, rozważałem kaskadowe usuwanie
Truck i Driver razem z usunięciem objektu Garage, ale uznałem to za niezbyt pożadane rozwiązanie.

Do zwracania i przyjmowania danych użyłem objektów DTO, w przypadku encji Garage tworze DTO niezawierające powiazania z encjami Truck i Driver, jak i zawierające ich liste w zależności od zapotrzebowania.

Co do edytowania encji wykorzystałem ządanie Patch i nie umożliwiam zmiany wszystkich danych encji, np. w przypadku Truck status nie może byc zmieniony bezpośrednio, a dopiero jeśli nie jest przypisany do żadnej
encji Delivery, która nie ma statusu DELIVERED.

Wszytskie dane przed dodaniem waliduje, w szczególności Delivery, aby np. przypisywany Truck był dostępny aktualnie.


zad. 2
Nigdy nie miałem styczności z typeScriptem, czy angularem, ale postanowiłem spróbować i zrobiłem to zadanie. Styl i wygląd myśle ze pozostawia wiele do życzenia :)
