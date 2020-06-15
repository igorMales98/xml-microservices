# XML - MICROSERVICES
---

### 1. Authentication service

Funkcionalnosti:

* POST: Registracija korisnika na sistem.
* POST: Prijava korisnika na sistem.
* PUT: Blokiranje običnih korisnika.
* PUT: Aktiviranje običnih korisnika.
* DELETE: Uklanjanje običnih korisnika iz sistema.
* POST: Registrovanje agenata na sistem.

| Modeli |
| --- |
| Administrator, Agent, Authority
  Customer, Permission, RegistrationRequest, User
  UserTokenState |


### 2. Advertisement service

Funkcionalnosti:
* POST: Kreiranje oglasa za automobil koji se može iznajmiti tako što će uneti slike i sve neophodne informacije.
* GET: Pretraživanje automobila - korisnik unosi mesto odakle želi da preuzme automobil, kao i datum preuzimanja i datum povratka automobila.
* GET: Napredna pretraga automobila - korisnik unosi dodatne parametre u kriterijum pretrage (marka automobila, model automobila, vrsta goriva, tip menjača, klasu automobila, cenu, pređenu kilometražu, kilometražu koju planira da pređe, CDW i broj sedišta za decu).
* GET: Prikaz svih dostupnih vozila po navedenim kriterijumima pretrage, kao i mogućnost sortiranja rezultata po ceni, oceni i kilometraži.
* GET: Detaljan prikaz automobila koji podrazumeva prikaz svih podataka i fotografija automobila.
* POST: Nakon isteka vremena korišćenja automobila korisniku je omogućeno da ostavi ocenu za dati oglas.
* POST: Svaki registrovani korisnik može da postavi ukupno tri oglasa.
* GET: Agentima/firmama je omogućeno da pregledaju ocene i komentare.
* POST: Agentima/firmama je omogućeno definisanje popusta gde bi pri dužem rentiranju vozila cena po danu bila umanjena za neki procenat.
* GET: Agentima/firmama je omogućeno da zatraže statistiku ocena svojih vozila.
* GET: Agenti/firme imaju uvid u to koliko je puta izdat automobil.
* POST: Objavljivanje komentara korisnika.
* DELETE: Brisanje komentara korisnika.

| Modeli |
| --- |
| Advertisment, Car, Comment |
### 3. Rent request service

Funkcionalnosti:

* POST: Kreiranje zahteva za iznajmljivanje automobila - moguće je iznajmiti jedan ili više automobila. Svaki ovakav zahtev se kreira sa statusom _pending_.
* PUT: Automobil može zatražiti više korisnika ali može biti prihvaćen samo jedan od strane korisnika ili agenta/firme. Prilikom prihvatanja zahteva zahtev iz stanja _pending_ prelazi u stanje _reserved_. Korisnik ima 12h vremena da izvrši online plaćanje, pri čemu zahtev iz stanja _reserved_ prelazi u stanje _paid_. Ukoliko korisnik ne izvrši plaćanje, zahtev iz stanja _reserved_ prelazi u stanje _canceled_. Zahtev koji nije obrađen 24h iz stanja _pending_ automatski prelazi u stanje _canceled_.
* GET: Korisnik ima mogućnost da pregleda svoju istoriju zahteva.
* PUT: Korisnik pre plaćanja može u bilo kom trenutku da otkaže zahtev.
* POST: Prilikom završetka rentiranja, korisnici unose izveštaj o broju pređenih kilometara i po potrebi unesu neke dodatne informacije (kao slobodan tekst).
* GET: Agentima/firmama je omogućen uvid u statistiku kilometraže automobila.

| Modeli |
| --- |
| Additional Bill, RentRequest, Report |

### 4. Message service

Funkcionalnosti:

* POST: Ukoliko je zahtev za iznajmljivanje automobila prihvaćen (stanje _reserved_) korisnik ima mogućnost da razmenjuje poruke sa vlasnikom.
* POST: Agentima/firmama je omogućena komunikacija sa korisnicima čije su zahteve prihvatili.
* GET: Korisnik ima mogućnost da pregleda sve razmenjene poruke sa vlasnikom automobila.

| Modeli |
| --- |
| Message |
### 5. Codebook service

Funkcionalnosti:
* POST: Agenti/firme imaju mogućnost definisanja cenovnika. U cenovniku je moguće definisati cenu automobila za svaki dan. Dodatno, u cenovniku je potrebno definisati cenu po kilometru za automobile za koje postoji ograničenje u kilometraži, kao i cena za Collision Damage Waiver za oglase u kojima se ta opcija nudi.
* POST: Mogućnost kreiranja novog modela automobila, klase automobila, tipa goriva.
* PUT: Mogućnost izmene određenog modela automobula, klase automobila, tipa goriva i cenovnika.
* DELETE: Mogućnost brisanja određenog modela automobula, klase automobila, tipa goriva i cenovnika.
* GET: Prikaz svih modela automobula, klasa automobila, tipova goriva i cenovnika.

| Model |
| --- |
| CarBrand, CarClass, CarModel, FuelType,
  Pricelist, TransmissionType |

### 6. Location service

Funkcionalnosti:

* GET: Agentima/firmama je omogućeno praćenje vozila putem mape ukoloko može da emituje svoju lokaciju.

### 7. Eureka service


### 8. Zuul Api Gateway Service
