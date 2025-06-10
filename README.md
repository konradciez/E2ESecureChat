JAVA E2E Secure


---JavaFX app---

Single Stage, Multiple Scenes
Each scene class exposes a method getScene() and takes Main as an argument to call back to it.

- Main zarządza currently shown sceną

- LoginScene sprawdzamy czy user znajduje się w "serwerze". Jeszcze nie jest zaimplementowane sprawdzanie hasła - dla każdego zarejestrowanego użytkowanika domyślne hasło to "a".

- RegisterScene generowanie kluczy RSA, zapis klucza prywatnego lokalnie (np. do pliku), oraz zapis danych użytkownika wraz z jego publicznym kluczem na serwerze

ProfileScene wyświetlamy wszystkich zarejestrowanych w systemie użytkowników. Trzeba dodać search box, osoby aktywne będą oznaczane zielonym kółkiem


---zarządzanie danymi---

User.java: Represents a user with ID, login, RSA publicKey
UserService.java: A singleton service that holds the current user and supports login/logout.
ProfileScene now reads from the logged-in User and supports logout.


---RSA key generation---

KeyGeneratorUtil with generateKeyPairBase64() method
Stored keys in User and passed them to UserService

generate RSA key pair during registration
Klucz prywatny zapisywany do pliku lokalnego, np. private_key.pem
KeyStorageUtil.java – zapis prywatnego klucza


---klient-serwer---

ServerData przetrzymuje listę użytkowników (id, login, publicKey)
udostępnia metodę registerUser(User user)
ServerData.java – baza użytkowników
