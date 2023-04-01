# day39
Marvel Char App

TODO: 
1. CORS? 
check referrer for Marvel website (add new referrer)
https://developer.marvel.com/account#
Check if required whitelisting of SB API
https://developer.marvel.com/documentation/authorization
2. Map out Angular and SB Components flow

## PWA (can find file name in package.json)
ng add @angular/pwa --project <project-name>@0.0.0 

## Running Maven
mvn clean spring-boot:run

## Running Angular with proxy (base URL)
ng server --proxy-config src/proxy-config.js

## Marvel API
Sample api call url
https://gateway.marvel.com/v1/public/characters?ts=1&apikey=f886f6f8de8dcc526df360bf71f33db4&hash=df865deb69923f4d970c5f260ad27136
https://gateway.marvel.com/v1/public/characters?ts=1&apiKey=f886f6f8de8dcc526df360bf71f33db4&hash=df865deb69923f4d970c5f260ad27136&nameStartsWith=abomination

Base: https://gateway.marvel.com/v1/public/characters

```
{
    "id": 1009146,
    "name": "Abomination (Emil Blonsky)",
    "description": "Formerly known as Emil Blonsky, a spy of Soviet Yugoslavian origin working for the KGB, the Abomination gained his powers after receiving a dose of gamma radiation similar to that which transformed Bruce Banner into the incredible Hulk.",
    "modified": "2012-03-20T12:32:12-0400",
    "thumbnail": {
    "path": "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04",
    "extension": "jpg"
    }
}
```

## Optional
1. return empty value
    - return Optional.empty();

2. return value
    - return Optional.of(value);



Angular
SpringBoot
Redis (store chracter details - temporary)
MongoDB (store comments)
- key: id
- value: list of comments 

## Managing timestamp
Generate current timestamp
```
Timestamp ts = Timestamp.from(Instant.now());
```
Convert String to timestamp
```
Timestamp newTs = Timestamp.valueOf("2023-04-01 14:35:28.843888");
```
