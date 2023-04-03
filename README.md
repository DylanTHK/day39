# Day39 - Marvel Char App

TODO: 
1. Map out Angular and SB Components flow

# Angular

## Create new project
ng new <project_name>

## Generate new component (add --flat for flat structure in folder)
ng g c components/<comp_name> --skip-tests

## Generate new service
ng g service services/<service_name> --skip-tests

## Running Angular with proxy (base URL)
ng server --proxy-config src/proxy-config.js

## Steps
1. update app.module.ts
 - add service to app.module.ts(under providers)
 - add necessary imports
2. add paths for routing (app-routing.module.ts)

## Add proxy (Allow for local testing)
Command to run angular with proxy
```
ng serve --proxy-config src/proxy.config.js
```

## PWA
1. add images and manifest.webmanifest
2. update angular.json > assets
```
"src/manifest.webmanifest"
"src/images"
```
3. update index.html
```
<link rel="manifest" href="/manifest.webmanifest">
```

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

## Managing timestamp
Generate current timestamp
```
Timestamp ts = Timestamp.from(Instant.now());
```
Convert String to timestamp
```
Timestamp newTs = Timestamp.valueOf("2023-04-01 14:35:28.843888");
```

## RAILWAY DEPLOYMENT
1. add redis / Mongo / SQL 
2. manually add database & tables(SQL) / collections(Mongo)
3. setup project ENV VARS (copy from storages)
    - Redis 
        - REDISHOST
        - REDISPASSWORD
        - REDISPORT
        - REDISUSER
    - MongoDB
        - MONGO_URL
    - SQL
        - 

** For Springboot call (Must include https)
```
example: 
https://marvel-server-production.up.railway.app
```

### 1. Fill in template 
### 2. Copy into RAW Editor (On railway project)
MONGO_URL=
REDISHOST=
REDISPASSWORD=
REDISPORT=
REDISUSER=