Tema POO - Magazin online
Costin Eugen, 324 CC
Timp de lucru: Aprox 60 de ore
Dificultate: Medie spre grea
Parte pozitiva: Consider ca tema a acoperit in amanunt fiecare detaliu important la materiei studiate
Parte negativa: Enuntul temei putea fi mai bine structurat, cu explicatii mai clare si fara greseli de exprimare.
Per total a fost o tema interesanta, cu aplicabilitate, si la care am lucrat cu placere.

Instructiuni de rulare:
Tema functioneaza atata timp cat in folderul test din arhiva proiectului exista fisierele store.txt,customers.txt,events.txt
Tema scrie rezultatul in fisierul output.txt

Descriere generala a implementarii:
-Store: Clasa principala pe care se bazeaza functionalitatea programului, implementata cu design pattern-ul singleton.
Are un vector de clienti si altul de produse, si un nume, si functii getter si setter.
Am creat o functie auxiliara care determina carui departament ii apartine un anumit produs.
-Customer: Clasa care modeleaza un client cu nume, cos de cumparaturi si wish list. 
Fiecare client are un vector de notificari pe care le acumuleaza.
Clasa customer implementeaza metoda update declarata in Observer, care are rolul de a adauga o noua notificare la colectia clientului.
Am implementat doua functii auxiliare care verifica daca un client mai este observer sau customer pentru un anumit departament.
-Departament: Clasa parinte pentru cele 4 tipuri de departamente.
Pentru un departament observerii sunt clientii care au cel putin un item din acel departament in wish list.
Pentru un departament vectorul customers cuprinde clientii care au in shoppingcart cel putin un produs din departament.
Department cuprinde metoda abstracta accept(Visitor visitor), care va fi implementata in subclase.
Am creat metode auxiliare pentru a returna cel mai ieftin, respectiv cel mai scump produs dintr-un departament.
-Item: Clasa care evidentiaza principiul incapsularii datelor, in sensul ca are membrii privati si fuctii getter si setter.
Este caracterizat de id, nume, pret si departament.
-ItemList: Clasa care modeleaza o lista dublu inlantuita de elemente de tip Node (clasa interna).
Un nod contine un item, si legaturile next si prev.
ItemList contine metode auxiliare pentru a adauga si scoate noduri, pentru a determina indicele unui nod, si pentru a vedea
daca list contine un nod sau un nod cu un anumit item.
ItemIterator implementeaza ListIterator<Item>, si contine un comparator (SCComparator) pentru a asigura sortarea nodurilor in functie de item.
Operatiile de add si remove din ListIterator au fost lasate neimplementate, deoarece daca se doreste adaugarea sau stergerea unui nod
se vor apela metodele add si remove din ItemList.
ShoppingCart si WishList: Clase care extind ItemList, singura diferenta intre ele fiind ca ShoppingCart are si un buget, si este necesar sa se
tina cont de acest buget la adaugarea de noi noduri. Astfel, functia de add in ShoppingCart este suprascrisa.
Functiile de addAll is removeAll parcurg o colectie si adauga elementele rand pe rand.
-Notification: Contine o enumerare de tip notificationType cu valorile "ADD", "REMOVE" si "MODIFY", data, id-ul departamentului si al produsului.
-Test: Clasa care citeste inputul si executa comenzile necesare.

Evenimente si cazuri speciale care pot aparea:
-addItem:
Se adauga itemul in shoppingcart sau wishlist-ul persoanei. Se verifica daca bugetul mai permite aceasta adaugare.
Daca este cazul, clientul va intra pe lista de observers sau customers a departamentului din care face parte produsul.
-delItem:
Se sterge itemul  din shoppingcart sau wishlist-ul persoanei. Se modifica bugetul.
Daca clientul nu mai are in shopping cart niciun produs din departamentul produsului scos, se elimina din customers.
Daca clientul nu mai are in wish list niciun produs din departmentul produsului scos, se elimina de la observers.
-addProduct
Se adauga produsul in magazin, se trimit notificari celor interesati.
-delProduct:
Se sterge complet produsul din magazin. Daca cineva avea produsul in shopping cart sau wish list se va elmina si de acolo.
Daca un client ramane dupa eliminare fara niciun produs de la un departament in shoppingcart sau wishlist, acesta este scos de pe lista de 
observers sau customers
-modifyProduct:
Se modifica pretul unui produs, si se verifica daca aceasta modificare afecteaza programul. Daca un client nu isi mai poate permite produsul 
dupa modificare, i se elimina din shoppinglist.
-accept:
Apeleaza metoda de accept corespunzatoare departamentului in care se afla produsul.
OBSERVATIE: Pentru orice eveniment de add remove sau modify se trimit notificari.














