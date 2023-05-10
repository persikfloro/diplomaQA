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
1. В терминале 2 запустить приложение: ` java -jar artifacts\aqa-shop.jar --spring.datasource.url=jdbc:mysql://localhost:3306/mysql `
2. Проверить запуск приложение в браузере Chrome:`http://localhost:8080`
3. В терминале 3 запустить тесты: `./gradlew clean test -D db.url=jdbc:mysql://localhost:3306/mysql`
4. Создать отчёт Allure и открыть в браузере `.\gradlew allureServe`
5. Закрыть отчёт в терминале 3: `CTRL + C` -> `y` -> `Enter`
6. Остановить приложение в терминале 2: `CTRL + C`
7. Остановить контейнеры в терминале 1:`docker-compose down`

### Подключение SUT к PostgreSQL
1. Настроить `application.properties`:
* Закомментировать 2 строку `spring.datasource.url=jdbc:mysql://localhost:3306/mysql`
* Убрать комментарий с 3 строки `spring.datasource.url=jdbc:postgresql://localhost:5432/postgresql`
2. Настроить `builde.gradle`:
* Закомментировать 48 строку `systemProperty 'db.url', System.getProperty('db.url', "jdbc:mysql://localhost:3306/mysql")`
* Убрать комментарий с 50 строки `systemProperty 'db.url', System.getProperty('db.url', 'jdbc:postgresql://localhost:5432/postgresql')`
* Обновить сборку
3. В терминале 2 запустить приложение: `java -jar artifacts\aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/postgresql`
4. Проверить запуск приложение в браузере Chrome:`http://localhost:8080`
5. В терминале 3 запустить тесты: `./gradlew clean test -D db.url=jdbc:postgresql://localhost:5432/postgresql`
6. Создать отчёт Allure и открыть в браузере `.\gradlew allureServe`
7. Закрыть отчёт в терминале 3: `CTRL + C` -> `y` -> `Enter`
8. Остановить приложение в терминале 2: `CTRL + C`
9. Остановить контейнеры в терминале 1:`docker-compose down`