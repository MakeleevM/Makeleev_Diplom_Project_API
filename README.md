# Автоматизация API-тестирования [ReqRes](https://reqres.in/)

<p align="center">
  <img alt="reqres.in" src="media/screenshots/reqres.in.png">
</p>

Проект дипломной работы: автотесты REST API на Java + RestAssured + JUnit 5 + Allure.

## Содержание

* [Используемый стек](#computer-используемый-стек)
* [Покрытый функционал](#white_check_mark-покрытый-функционал)
* [Структура проекта](#структура-проекта)
* [Запуск тестов](#keyboard-запуск-автотестов)
* [Сборка в Jenkins](#сборка-в-jenkins)
* [Allure отчёт](#allure-отчёт)
* [Allure TestOps](#интеграция-с-allure-testops)
* [Jira](#интеграция-с-jira)
* [Telegram](#уведомление-в-telegram)

## :computer: Используемый стек

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

- **Java**, **Gradle**, **JUnit 5**
- **RestAssured** — HTTP-запросы, specs, JSON Schema
- **Lombok** — модели request/response (`@Data`)
- **Owner** — конфиг из `auth.properties`
- **Allure** + `allure-rest-assured` с custom FreeMarker templates (`tpl/`)
- **AssertJ**, **Hamcrest**

## :white_check_mark: Покрытый функционал

### Автотесты
- GET список пользователей (`/users?page=2`) — статус, path, JSON Schema
- GET пользователь по id — параметризованный тест (`@ValueSource`: 1, 2, 3), десериализация в модель
- POST создание пользователя — DTO body + проверка модели ответа
- PATCH обновление пользователя
- DELETE удаление пользователя
- POST регистрация (успех / ошибка без email/password)

### Ручные тест-кейсы (`@Manual`, tag `manual`)
- Проверка документации reqres.in
- Поведение при невалидном `x-api-key`
- Проверка rate limit

## Структура проекта

```
src/test/java/
  config/       # ApiConfig (Owner)
  helpers/      # Specification, CustomApiListener, TestData
  model/        # DTO request/response (Lombok)
  tests/        # TestBase + автотесты + ручные кейсы
src/test/resources/
  auth.properties.example
  schemas/
  tpl/          # Allure request.ftl / response.ftl
```

Перед запуском скопируйте `auth.properties.example` → `auth.properties` и укажите свой `API_KEY` с [app.reqres.in](https://app.reqres.in/api-keys).

## :keyboard: Запуск автотестов

Локально:

```bash
gradlew clean test
gradlew allureServe
```

С параметрами (как в Jenkins):

```bash
gradlew clean test -DbaseUri=https://reqres.in -DbasePath=/api -DAPI_KEY=your_key
```

Ручные кейсы по умолчанию исключены (`excludeTags 'manual'`).

> **Selenoid** в API-проекте не используется: это Selenium Grid для UI (Selenide).  
> API-тесты ходят в REST напрямую через RestAssured. В Jenkins достаточно job с `gradlew clean test` и параметрами выше; Selenoid нужен только в UI-проекте (`remoteUrl`, browser, VNC/video).

## Сборка в Jenkins

### 1. Запушьте код в GitHub

Jenkins берёт код из репозитория. Локальные изменения должны быть в `origin`:

```bash
git add .
git commit -m "Prepare API project for Jenkins"
git push origin master
```

`auth.properties` в git не коммитьте (там ключ). В Jenkins передайте `API_KEY` параметром.

### 2. Создайте Freestyle job

1. Откройте [jenkins.autotests.cloud](https://jenkins.autotests.cloud/)
2. **New Item** → имя, например `41-Mace133v-API` → **Freestyle project**
3. **Source Code Management** → Git  
   - Repository URL: `https://github.com/MakeleevM/Makeleev_Diplom_Project_API.git`  
   - Branch: `*/master`
4. **This project is parameterized** → добавьте String Parameter:
   - `API_KEY` — ключ с [app.reqres.in](https://app.reqres.in/api-keys)
   - (опционально) `BASE_URI` = `https://reqres.in`
   - (опционально) `BASE_PATH` = `/api`
5. **Build** → **Invoke Gradle script** (или Execute shell):

```text
clean
test
-DbaseUri=${BASE_URI}
-DbasePath=${BASE_PATH}
-DAPI_KEY=${API_KEY}
-DEMAIL=eve.holt@reqres.in
-DPASSWORD=pistol
```

Если параметра `BASE_URI` нет, достаточно:

```text
clean
test
-DAPI_KEY=${API_KEY}
```

6. **Post-build Actions** → **Allure Report**:
   - Path: `build/allure-results`
7. **Save** → **Build with Parameters** → вставьте `API_KEY` → Build

> После создания job замените ссылку ниже на актуальную.

<a href="https://jenkins.autotests.cloud/view/java_students/job/41-Mace133v-API/">Jenkins Job</a>

<p align="center">
  <a href="https://jenkins.autotests.cloud/view/java_students/job/41-Mace133v-API/">
    <img src="media/screenshots/jenkins.png" alt="Jenkins"/>
  </a>
</p>

## Allure отчёт

<a href="https://jenkins.autotests.cloud/view/java_students/job/41-Mace133v-API/allure/">Allure Report в Jenkins</a>

<p align="center">
  <img title="Allure Overview" src="media/screenshots/allure.jpg">
</p>

## Интеграция с Allure TestOps

> Укажите ссылку на свой проект в Allure TestOps после настройки интеграции.

<p align="center">
  <img title="Allure TestOps Dashboard" src="media/screenshots/testOps.jpg">
</p>

## Интеграция с Jira

<p align="center">
  <img title="Jira" src="media/screenshots/jira.jpg">
</p>

## Уведомление в Telegram

<p align="center">
  <img title="Telegram" src="media/screenshots/telegram.jpg">
</p>
