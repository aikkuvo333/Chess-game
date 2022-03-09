#Shakkisovellus

##Tekijät:
OTP-ryhmä 5: Oliver Hamberg, Santeri Kuusisto, Elmo Vahvaselkä ja Ai Van Vo

##Viso:
Tavoitteena on kehittää graafisenkäyttöliittymän omaava sovellus, jossa kaksi käyttäjää voi pelata shakkia samalta tietokoneelta tehden siirtoja vuorotellen koneen hiirellä. Tarkoituksena on antaa mahdollisuus pelata tilastoituja tai tilastoimattomia pelejä. Tilastoitujen pelien tiedot tallennetaan tietokantaan.

Tilastoidun pelin alussa pelaaja voi joko luoda uuden käyttäjätunnuksen tai valita jo olemassa olevan tunnuksen. Kun peli päättyy tiedot tallentuvat tietokantaan. 

Käyttäjälle tarjotaan mahdollisuus tarkastella leaderboardia, jossa pelaajat voidaan järjestää paremmuusjärjestykseen joko voittojen tai voittoprosentin mukaan. Lisäksi käyttäjän on mahdollista tarkistella yksittäisen pelaajan tilastoja ja pelihistoriaa.

##Kehitysympäristö:
Sovellus on toteutettu Maven-projektina ja ohjelmoitu Javalla. Graafinen käyttöliittymä on luotu hyödyntäen JavaFX-kirjastoa. Modelin testauksessa on toteutettu JUnit testeillä. Tietokantana toimii MariaDB ja sitä käytetään Hibernate ORM:in avulla. 

##Käyttöönotto ja konfigurointi
1.	Lataa projektin repo
2.	Avaa repo ohjelmointi ympäristössä
3.	Aja tiedosto Main.java pakkauksesta view.src.application
