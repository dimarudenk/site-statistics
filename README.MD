1. Собрать проект
```
 mvn clean install
```
2. Запустить
```
java -jar target\site-statistics-1.0.0-SNAPSHOT.jar

```
3. Иницилизация
При запуске создается
    * 10 записей для user1( с датами от 2018-01-01T01:00:00 до 2018-01-01T10:00:00)
    * 10 записей для user2( с датами от 2018-01-01T01:00:00 до 2018-01-01T10:00:00)
    * 8 записей для user3( с датами от 2018-01-01T01:00:00 до 2018-01-01T08:00:00)
        
4. Проверка
При первом запросе GET http://localhost:8282/services/statistics/2018-01-01 00:00:00/2018-04-02 00:00:00
результат должен быть
```
{
    "numberOfVisits": 28,
    "numberOfUniqueUsers": 3,
    "numberOfRegularUsers": 2
}
```
После того как вызовится метод http://localhost:8282/services/event
```
{
"clientId":"user3",
"pageId":"page11"
}
```
результат на http://localhost:8282/services/statistics/2018-01-01 00:00:00/2018-04-02 00:00:00 должен стать
```
{
    "numberOfVisits": 29,
    "numberOfUniqueUsers": 3,
    "numberOfRegularUsers": 2
}
```
После того как вызовится метод http://localhost:8282/services/event
```
{
    "clientId":"user3",
    "pageId":"page12"
}
```
результат на http://localhost:8282/services/statistics/2018-01-01 00:00:00/2018-04-02 00:00:000 должен стать
```
{
    "numberOfVisits": 30,
    "numberOfUniqueUsers": 3,
    "numberOfRegularUsers": 3
}
```
