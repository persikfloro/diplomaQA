# Дипломный проект по профессии «Тестировщик»
## Документация
* [Текст задания](https://github.com/netology-code/qa-diploma/blob/master/README.md)
* [План автоматизации](Plan.md)
* Отчёт о проведенном тестировании
* Отчет о проведённой автоматизации тестирования
## Инструкция для запуска автотестов
1. Клонировать проект: `https://github.com/persikfloro/diplomaQA`
2. Открыть проект в IntelliJ IDEA
3. Запустить Docker Desktop командой в терминале IntelliJ IDEA `docker-compose up`

### Подключение SUT к MySQL
1. В терминале 2 запустить приложение: ` java -jar artifacts/aqa-shop.jar --spring.profiles.active=mysql `
2. Проверить запуск приложение в браузере Chrome:`http://localhost:8080`
3. В терминале 3 запустить тесты: `./gradlew clean test -DdbUrl=jdbc:mysql://localhost:3306/mysql` 
4. Создать отчёт Allure и открыть в браузере `.\gradlew allureServe`
5. Закрыть отчёт в терминале 3: `CTRL + C` -> `y` -> `Enter`
6. Остановить приложение в терминале 2: `CTRL + C`
7. Остановить контейнеры в терминале 1:`docker-compose down`

### Подключение SUT к PostgreSQL
1. В терминале 2 запустить приложение: `java -jar artifacts/aqa-shop.jar --spring.profiles.active=postgresql`
2. Проверить запуск приложение в браузере Chrome:`http://localhost:8080`
3. В терминале 3 запустить тесты: `./gradlew clean test -DdbUrl=jdbc:postgresql://localhost:5432/postgresql`
4. Создать отчёт Allure и открыть в браузере `.\gradlew allureServe`
5. Закрыть отчёт в терминале 3: `CTRL + C` -> `y` -> `Enter`
6. Остановить приложение в терминале 2: `CTRL + C`
7. Остановить контейнеры в терминале 1:`docker-compose down`