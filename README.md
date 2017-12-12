# classic
Sandbox project contains classic web frameworks (hibernate, spring) examples

## Study materials
http://java-course.ru/student/book2/

## Requirements
* java 7
* maven 3
  * add to conf/settings.xml
```
<server>
	<id>tomcat</id>
	<username>manager</username>
	<password>manager</password>
</server>
```

* tomcat 7
  * add to conf/tomcat-users.xml
```
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<user username="manager" password="manager" roles="manager-gui, manager-script"/>
```

* mysql 5
  * server host is `127.0.0.1` or `localhost`
  * server port is `3306`
  * server root password is `qwerty`
  * db name will be `papers`


## Usage
### deploy db
`mysql --user=root --password=qwerty < deploy.sql`
### deploy web
`mvn tomcat7:undeploy clean tomcat7:deploy`
### run
`http://localhost:8080/papers-0.0.1-SNAPSHOT`
