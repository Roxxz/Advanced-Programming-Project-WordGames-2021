# PA-Project-WordGames-

**Specificatii:**

-Crearea unor servicii pentru implementarea unor jocuri cu cuvinte (Scrabble sau altele) pentru limba română

-Serviciile pot fi: gestionarea unor dictionare, anagrame, accesarea unor servicii pentru obtinerea definitilor, sinonimelor (Dex), suport pentru anumite jocuri, etc.

-Implementarea unui joc care foloseste aceste servicii cu interfata JavaFX

**Membrii echipei:**

*Benchea Laura Malina*

*Portase Roxana Nicoleta*

***Ce am realizat?***

  Am implementat un joc clasic de "Spanzuratoarea" cu urmatoarele specificatii:
  **Baza de date**
    - conexiunea la o baza de date in care se va trece numele fiecarui jucator, scorul final si data la care acesta a fost stabilit;
    - aceasta va fi actualizata cu data si noul scor de fiecare data cand jucatorul isi va intrece recordul.
  **Jocul pe dificultati**
    - Jucatorul are optiunea de a alege ce dificultate vrea, aceasta constand in numarul de litere ale cuvintelor de ghicit;
    - Avem trei trepte de dificultate: incepator(pana in 4 litere), mediu(6) si avansat(8);
    - Cu cat nivelul este mai dificil, cu atat punctajele vor creste mai mult;
   **Scor**
    - Utilizatorul va putea oricand sa vizualizeze un istoric al scorurilor, cu data, ora si punctajul final al jocului;
   **Podium**
    - Acest buton duce jucatorul pe o noua pagina, in care se vor vedea top 3 cei mai buni jucatori, luati din baza de date in functie de scor;
    **Dictionare**
    - prin intermediul librariei jsoup, am gestionat dictionarul https://www.dexonline.ro/, pentru optiunea jucatorului de a cere definitia unui cuvant(care va costa 10 puncte);
    - am gestionat dictionarul online de sinonime https://www.dictionardesinonime.ro/, pentru optiunea de a cere un sinonim (15 puncte).
    
