# Проект по автоматизации тестирования API [reqres.in](https://reqres.in/)

<p align="center">
<img alt="reqres.in" src="media/screenshots/reqres.in.png">
</p>

> Test your front-end against a real API

## Содержание

* <a href="#tools">Используемый стек</a>
* <a href="#cases">Примеры автоматизированных тест-кейсов</a>
* <a href="#console">Запуск из терминала</a>
* <a href="#jenkins">Сборка в Jenkins</a>
* <a href="#allure">Allure отчёт</a>
* <a href="#allure-testops">Интеграция с Allure TestOps</a>
* <a href="#jira">Интеграция с Jira</a>
* <a href="#telegram">Уведомление в Telegram</a>

____
<a id="tools"></a>

## <a name="Используемый стек">**Используемый стек**</a>

<p align="center">
<a href="https://www.java.com/"><img width="6%" title="Java" src="media/logo/Java.svg"></a>
<a href="https://rest-assured.io/"><img src="media/logo/RestAssured.svg" width="50" height="50" alt="RestAssured"/></a>
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

## <a name="Примеры автоматизированных тест-кейсов">**Примеры автоматизированных тест-кейсов**</a>

____
- :white_check_mark: Тестирование запроса PATCH с обновлением данных Users по полю job/name
- :white_check_mark: Тестирование запроса POST регистрация пользователя с незаполненными email/password
- :white_check_mark: Тестирование запроса POST регистрация пользователя
- :white_check_mark: Тестирование запроса POST создание пользователя с проверкой ответа
- :white_check_mark: Тестирование запроса GET получить пользователя по его id
- :white_check_mark: Тестирование запроса DELETE удаление пользователя
- :white_check_mark: Тестирование запроса GET List Users ?page=2

____
<a id="console"></a>

## Запуск автотестов

***Команда запуска тестов из терминала:***

```
gradle clean test
```

***Запуск с параметрами (как в Jenkins):***

```
gradle clean test -DbaseUri=https://reqres.in -DbasePath=/api -DAPI_KEY=your_key
```

____
<a id="jenkins"></a>

## <img alt="Jenkins" height="25" src="media/logo/Jenkins.svg" width="25"/> <a name="Сборка">Сборка в [Jenkins](https://jenkins.qa.guru/job/41-Mace133v-HW25_API/)</a>

<p align="center">
<a href="https://jenkins.qa.guru/job/41-Mace133v-HW25_API/"><img src="media/screenshots/jenkins.png" alt="Jenkins"/></a>
</p>

***Параметры сборки:***
- `API_KEY` — ключ API с [app.reqres.in](https://app.reqres.in/api-keys)
- `BASE_URI` — `https://reqres.in`
- `BASE_PATH` — `/api`

***Команда в Jenkins:***

```
clean test
-DbaseUri=$BASE_URI
-DbasePath=$BASE_PATH
-DAPI_KEY=$API_KEY
-DEMAIL=eve.holt@reqres.in
-DPASSWORD=pistol
```

____
<a id="allure"></a>

## <img src="media/logo/Allure.svg" width="25" height="25" alt="Allure"/> Allure <a target="_blank" href="https://jenkins.qa.guru/job/41-Mace133v-HW25_API/5/allure-report/">отчёт</a>

### *Пример отчёта о прохождении тестов*

<p align="center">
<a href="https://jenkins.qa.guru/job/41-Mace133v-HW25_API/5/allure-report/"><img title="Allure Overview Dashboard" src="media/screenshots/allure.png"></a>
</p>

____
<a id="allure-testops"></a>

## <img src="media/logo/Allure_TO.svg" width="25" height="25" alt="Allure TestOps"/> Интеграция с <a target="_blank" href="https://allure.qa.guru/project/5303/test-cases?treeId=0">Allure TestOps</a>

### *Allure TestOps Dashboard*

<p align="center">
<a href="https://allure.qa.guru/project/5303/test-cases?treeId=0"><img title="Allure TestOps Dashboard" src="media/screenshots/testOps.jpg"></a>
</p>

____
<a id="jira"></a>

## <img src="media/logo/Jira.svg" width="25" height="25" alt="Jira"/> Интеграция с <a target="_blank" href="https://jira.qa.guru/browse/REF-10">Jira</a>

<p align="center">
<a href="https://jira.qa.guru/browse/REF-10"><img title="Jira" src="media/screenshots/jira.jpg"></a>
</p>

____
<a id="telegram"></a>

## <img src="media/logo/Telegram.svg" width="25" height="25" alt="Telegram"/> Уведомление в Telegram

<p align="center">
<img title="Telegram notification" src="media/screenshots/telegram.jpg">
</p>
