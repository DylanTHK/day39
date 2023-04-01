# Day39 - Marvel Char App

TODO: 
1. CORS? 
check referrer for Marvel website (add new referrer)
https://developer.marvel.com/account#
Check if required whitelisting of SB API
https://developer.marvel.com/documentation/authorization
2. Map out Angular and SB Components flow

# Angular
## Running Angular with proxy (base URL)
ng server --proxy-config src/proxy-config.js


<br>

# SpringBoot
## Running Maven
mvn clean spring-boot:run

## Available Endpoints
BASE_URL="localhost:8080"
### Query all characters related to {name} from Marvel API (returns List<Character>)
GET => {BASE_URL}/api/characters?name={name}

### Query one characters related to {id} from Marvel API (returns Character)
GET => {BASE_URL}/api/chrarcter/{id}

### Submit comment into MongoDB linked to ID (returns Comment)
POST => {BASE_URL}/api/chrarcter/{id}
- Add content to Request Body

### Query all comments by ID (returns JsonArray)
GET => {BASE_URL}/api/comments/{id}

## Marvel API
API call (From SpringBoot -> Marvel API)
https://gateway.marvel.com/v1/public/characters
https://gateway.marvel.com/v1/public/characters/{characterId}

## Optional
1. return empty value
```
Optional.empty();
```
2. return value
```
Optional.of(value);
```
3. check if Optional present (return boolean)
```
opt.isPresent();
```



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
