# Дипломный проект по профессии «Тестировщик»
[![Build status](https://ci.appveyor.com/api/projects/status/2216p48d5m6rtdcm?svg=true)](https://ci.appveyor.com/project/persikfloro/diplomaqa)
## Документация
* [Текст задания](https://github.com/netology-code/qa-diploma/blob/master/README.md)
* [План автоматизации](Plan.md)
* Отчёт о проведенном тестировании
* Отчет о проведённой автоматизации тестирования
## Инструкция для запуска автотестов
1. Клонировать проект: `https://github.com/persikfloro/diplomaQA`
2. Открыть проект в IntelliJ IDEA
3. Запустить Docker Desktop командой в терминале IntelliJ IDEA `docker-compose up -d`
### Подключение SUT к MySQL
1. В терминале 1 в корне проекта запустить контейнеры: `docker-compose up -d`
2. В терминале 2 запустить приложение: `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/mysql -jar artifacts/aqa-shop.jar`
3. Проверить запуск приложение в браузере Сhrome:`http://localhost/8080`
4. В терминале 3 запустить тесты: `.\gradlew clean test -DdbUrl=jdbc:mysql://localhost:3306/mysql`
5. Создать отчёт Allure и открыть в браузере `.\gradlew allureServe`
6. Закрыть отчёт в терминале 3: `CTRL + C` -> `y` -> `Enter`
7. Остановить приложение в терминале 2: `CTRL + C`
8. Остановить контейнеры в терминале 1:`docker-compose down`

### Подключение SUT к PostgreSQL
1. В терминале 1 в корне проекта запустить контейнеры: `docker-compose up -d`
2. В терминале 2 запустить приложение: `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/postgres -jar artifacts/aqa-shop.jar`
3. Проверить запуск приложение в браузере Сhrome:`http://localhost/8080`
5. В терминале 3 запустить тесты: `.\gradlew clean test -DdbUrl=jdbc:postgresql://localhost:5432/postgres`
6. Создать отчёт Allure и открыть в браузере `.\gradlew allureServe`
7. Закрыть отчёт в терминале 3: `CTRL + C` -> `y` -> `Enter`
8. Остановить приложение в терминале 2: `CTRL + C`
9. Остановить контейнеры в терминале 1:`docker-compose down`
