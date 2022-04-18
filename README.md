# Project Code



## Getting started

### Setup MySQL 
```mysql
create database meal_time_tinder;
create user 'mtt'@'localhost' identified by 'Mtt../12045';
grant all on meal_time_tinder.* to 'mtt'@'localhost';
```
### Specify your project path
In backend/src/main/resources/application.yml -> static-locations:

To get your path, use following command:
```sh
pwd
```


### Default accounts

Admin: (can also register new one, but need to log in as admin first) 
username: damon
password: 1204578616

Hunter: (can also register new one)
username: damon
password: 1204578616

Restaurant: (can also register new one)
username: damon
password: 1204578616

### API Documentation

link: http://localhost:8080/swagger-ui.html