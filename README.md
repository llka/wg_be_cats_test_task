# Тестовое задание WG Forge (Backend)

Выполнено на Java

## 1-е и 2-е задание
Решение: https://github.com/llka/wg_be_cats_test_task/tree/master/wg-forge-be-test

Main class - [ru.ilka.wgforge.testtask.Main](https://github.com/llka/wg_be_cats_test_task/blob/master/wg-forge-be-test/src/main/java/ru/ilka/wgforge/testtask/Main.java)

Параметры базы данных: [wg-forge-be-test/src/main/resources/properties/database.properties](https://github.com/llka/wg_be_cats_test_task/blob/master/wg-forge-be-test/src/main/resources/properties/database.properties)

### Как запустить?
  - you must have Maven and Java installed on your machine.
  - check database properties 
  - run your docker container with cats database (or start database)
  - run in terminal: **mvn clean install**
  - run in terminal: **mvn exec:java -Dexec.mainClass="ru.ilka.wgforge.testtask.Main"**

## 3-е - 6-е задание (сервис)

Решение -  https://github.com/llka/wg_be_cats_test_task/tree/master/wgforge.cats.service

Spring Boot REST Api

Параметры сервера и базы данных: [wgforge.cats.service/src/main/resources/application.properties](https://github.com/llka/wg_be_cats_test_task/blob/master/wgforge.cats.service/src/main/resources/application.properties)


