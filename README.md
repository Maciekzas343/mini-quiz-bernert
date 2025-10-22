# Mini Quiz

Prosta mobilna aplikacja edukacyjna (Android, Java) z quizem wielokrotnego wyboru.

## Funkcje
- Start quizu (losuje 5 pytań z puli)
- Odpowiadanie na pytania A/B/C
- Liczenie poprawnych odpowiedzi na bieżąco
- Komunikat końcowy: "Koniec quizu! Twój wynik: X / 5"
- Reset quizu (wynik = 0, można zacząć od nowa)

## Wymagania
- Android Studio (Electric Eel+)
- Java
- Min SDK 21+

## Jak uruchomić
1. `git clone https://github.com/<twoje_konto>/mini-quiz-bernert.git`
2. Otwórz w Android Studio → poczekaj na Gradle sync.
3. Uruchom na emulatorze lub urządzeniu.

## Zrzuty ekranu
1. **Stan początkowy** – tytuł, przycisk START, wynik=0  
2. **Pytanie w trakcie** – treść + trzy odpowiedzi  
3. **Wynik końcowy** – toast "Koniec quizu! Twój wynik: X / 5"

*(Wklej obrazy do `/screenshots` i podlinkuj poniżej)*

![Start](screenshots/start.png)
![Pytanie](screenshots/question.png)
![Koniec](screenshots/end.png)

## Struktura
- `app/src/main/res/layout/activity_main.xml` – interfejs (XML)
- `app/src/main/java/.../MainActivity.java` – logika aplikacji
- `app/src/main/java/.../Question.java` – model pytania

## Licencja
MIT

