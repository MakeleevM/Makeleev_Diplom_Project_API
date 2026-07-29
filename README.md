# Проект по автоматизации тестирования API [reqres.in](https://reqres.in/)

<p align="center">  
<img alt="shop.proamk" src="/media/screenshots/reqres.in.png">



> Test your front-end against a real API


<a name="наверх"></a>

## :scroll: Содержание:

* <a href="#tools">Используемый стек</a>
* <a href="#cases">Примеры автоматизированных тест-кейсов</a>
* <a href="#console">Запуск из терминала</a>
* <a href="#jenkins">Сборка в Jenkins</a>
* <a href="#allure">Allure отчет</a>
* <a href="#allure-testops">Интеграция с Allure TestOps</a>
* <a href="#jira">Интеграция с Jira</a>
* <a href="#telegram">Уведомление в Telegram при помощи бота</a>


____
<a id="tools"></a>

## :computer:<a name="Используемый стек">**Используемый стек:**</a>

<p align="center">
<a href="https://www.java.com/"><img width="6%" title="Java" src="media/logo/Java.svg"></a>
<a href="https://rest-assured.io/"><img src="/media/logo/RestAssured.svg" width="50" height="50"  alt="RestAssured"/></a>
<a href="https://github.com/allure-framework/allure2"><img width="6%" title="Allure Report" src="media/logo/Allure.svg"></a>
<a href="https://qameta.io/"><img width="5%" title="Allure TestOps" src="media/logo/Allure_TO.svg"></a>
<a href="https://gradle.org/"><img width="6%" title="Gradle" src="media/logo/Gradle.svg"></a>
<a href="https://junit.org/junit5/"><img width="6%" title="JUnit5" src="media/logo/Junit5.svg"></a>
<a href="https://github.com/"><img width="6%" title="GitHub" src="media/logo/GitHub.svg"></a>
<a href="https://www.jenkins.io/"><img width="6%" title="Jenkins" src="media/logo/Jenkins.svg"></a>
<a href="https://web.telegram.org/a/"><img width="6%" title="Telegram" src="media/logo/Telegram.svg"></a>
<a href="https://www.atlassian.com/ru/software/jira/"><img width="5%" title="Jira" src="media/logo/Jira.svg"></a>
<a href="https://www.jetbrains.com/ru-ru/idea/"><img width="5%" title="IntelliJ" src="media/logo/idea.svg"></a>
</p>

____
<a id="cases"></a>

## <a name="Примеры автоматизированных тест-кейсов">**Примеры автоматизированных тест-кейсов:**</a>

____
- :white_check_mark: Тестирование запроса Patch c обновлением данных Users по полю job/name
- :white_check_mark: Тестирование запроса Post регистрация пользователя с незаполненными email/password
- :white_check_mark: Тестирование запроса Post регистрация пользователя
- :white_check_mark: Тестирование запроса Post c проверкой key/value
- :white_check_mark: Тестирование запроса Get получить пользователя по его id
- :white_check_mark: Тестирование запроса Delete удаление пользователя
- :white_check_mark: Тестирование запроса Get List Users ?page=2


<a id="console"></a>

## :keyboard: Запуск автотестов

***Команда запуска тестов из терминала:***

```
gradle clean test 
```

____
<a id="jenkins"></a>

## <img alt="Jenkins" height="25" src="media/logo/Jenkins.svg" width="25"/></a><a name="Сборка"></a>Сборка в [Jenkins](https://jenkins.autotests.cloud/job/igafarov_reqres.in_api_autotests/)</a>

<p align="center">  
<a href="https://jenkins.autotests.cloud/job/igafarov_reqres.in_api_autotests/"><img src="media/screenshots/jenkins.png" alt="Jenkins"/></a>  
</p>

<a id="allure"></a>

## <img src="media/logo/Allure.svg" width="25" height="25"  alt="Allure"/></a> Allure <a target="_blank" href="https://jenkins.autotests.cloud/job/igafarov_reqres.in_api_autotests/allure/">отчёт</a>

### *Пример отчёта о прохождении тестов*

<p align="center">  
<img title="Allure Overview Dashboard" src="media/screenshots/allure.jpg">  
</p> 

<a id="allure-testops"></a>

## <img src="media/logo/Allure_TO.svg" width="25" height="25"  alt="Allure"/></a>Интеграция с <a target="_blank" href="https://allure.autotests.cloud/project/4093/dashboards">Allure TestOps</a>

### *Allure TestOps Dashboard*

<p align="center">  
<img title="Allure TestOps Dashboard" src="media/screenshots/testOps.jpg">  
</p> 

<a id="jira"></a>

## <img src="media/logo/Jira.svg" width="25" height="25"  alt="Allure"/></a>Интеграция с <a target="_blank" href="https://jira.autotests.cloud/browse/HOMEWORK-1140">Jira</a>

<p align="center">  
<img title="Jira" src="media/screenshots/jira.jpg">  
</p>

<a id="telegram"></a>

## <img src="media/logo/Telegram.svg" width="25" height="25"  alt="Allure"/></a>Уведомление в Telegram

<p align="center">  
<img title="Allure Overview Dashboard" src="media/screenshots/telegram.jpg">  
</p>

 

[Наверх ⬆](#наверх)