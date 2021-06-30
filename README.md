# PA-Project-WordGames-
# Computer Science, Al.I.Cuza Univeristy

## Specifications:

-Creating services for implementing word games (Scrabble or others) with romanian words.

-Those services could be: working with dictionaries, anagrams, accessing services for obtaining definitions, synonyms (Dex), etc.

-Implementing a game that makes use of those services with JavaFX interface.

## Team members:

*Benchea Laura Malina*

*Portase Roxana Nicoleta*

## What did we accomplish?

  We implemented a classic Hangman game containing:
  
  **A database**
  
    1. database connection that contains the name of every player, final score and date at which it was achieved;
    
    2. it would eventually be updated with the date and the new score every time a player beats his own score.
    
  **Three levels of difficulty**
  
    1. The player can choose the difficulty of the game, which is depending on the number of letters the searched word contains;
    
    2. Three levels of difficulty: beginner(from 2 to 4 letters), medium(6) and advanced(8);
    
    3. Points received differ according to the difficulty of the level;
    
   **Score**
   
    1. The player can always see a score list containing all scores ever achieved along with the date and hour;
    
   **Podium**
   
    1. This button shows the player a podium displaying the top 3 best players retrieved from the database depending on the score;
    
    **Dictionaries**
    
    1. using jsoup library, we managed the https://www.dexonline.ro/ dictionary, in order to show the player the definition of a word in case he chooses it(which will cost him 10 points);
    
    2. we also used the online synonyms dictionary https://www.dictionardesinonime.ro/, for showing the synonym of a word in case the player chooses so (15 points).


-------------------------------------------------------------------------------------------------------------------------------------------

## Specificatii:

-Crearea unor servicii pentru implementarea unor jocuri cu cuvinte (Scrabble sau altele) pentru limba română

-Serviciile pot fi: gestionarea unor dictionare, anagrame, accesarea unor servicii pentru obtinerea definitilor, sinonimelor (Dex), suport pentru anumite jocuri, etc.

-Implementarea unui joc care foloseste aceste servicii cu interfata JavaFX

## Membrii echipei:

*Benchea Laura Malina*

*Portase Roxana Nicoleta*

## Ce am realizat?

  Am implementat un joc clasic de "Spanzuratoarea" cu urmatoarele specificatii:
  
  **Baza de date**
  
    1. conexiunea la o baza de date in care se va trece numele fiecarui jucator, scorul final si data la care acesta a fost stabilit;
    
    2. aceasta va fi actualizata cu data si noul scor de fiecare data cand jucatorul isi va intrece recordul.
    
  **Jocul pe dificultati**
  
    1. Jucatorul are optiunea de a alege ce dificultate vrea, aceasta constand in numarul de litere ale cuvintelor de ghicit;
    
    2. Avem trei trepte de dificultate: incepator(pana in 4 litere), mediu(6) si avansat(8);
    
    3. Cu cat nivelul este mai dificil, cu atat punctajele vor creste mai mult;
    
   **Scor**
   
    1. Utilizatorul va putea oricand sa vizualizeze un istoric al scorurilor, cu data, ora si punctajul final al jocului;
    
   **Podium**
   
    1. Acest buton duce jucatorul pe o noua pagina, in care se vor vedea top 3 cei mai buni jucatori, luati din baza de date in functie de scor;
    
    **Dictionare**
    
    1. prin intermediul librariei jsoup, am gestionat dictionarul https://www.dexonline.ro/, pentru optiunea jucatorului de a cere definitia unui cuvant(care va costa 10 puncte);
    
    2. am gestionat dictionarul online de sinonime https://www.dictionardesinonime.ro/, pentru optiunea de a cere un sinonim (15 puncte).
    
