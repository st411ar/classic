# classic
Sandbox project contains classic web frameworks (hibernate, spring) examples

## Study materials
http://java-course.ru/student/book2/

## Requirements
* java 7
* maven 3
* mysql 5
  * server host is `127.0.0.1` or `localhost`
  * server port is `3306`
  * server root password is `qwerty`
  * db name will be `classic`


## Usage
### deploy
`mysql --user=root --password=qwerty < classic.sql`
### build
`mvn clean package`
### run
`java -jar target/classic-0.0.1-SNAPSHOT-jar-with-dependencies.jar`