# Hotel App

## Requirements

- Node 12.14.0+.
- Yarn 1.21.1+.
- Java SE 8+.
- Maven 3+.
- MySQL 8+.

## Database creation

```
Start Mysql server if not running (e.g. mysqld).

mysqladmin -u root create hotelapp -p
mysqladmin -u root create hotelapptest -p
mysqladmin -u root create hotelappprojet -p
mysqladmin -u root create hotelappprojecttest -p

mysql -u root
    CREATE USER 'hotelapp'@'localhost' IDENTIFIED BY 'hotelapp';
    GRANT ALL PRIVILEGES ON hotelappproject.* to 'hotelapp'@'localhost' WITH GRANT OPTION;
    GRANT ALL PRIVILEGES ON hotelappprojecttest.* to 'hotelapp'@'localhost' WITH GRANT OPTION;
    exit
```

## Run

```
cd backend
mvn sql:execute (only first time to create tables)
mvn spring-boot:run

cd frontend
yarn install (only first time to download libraries)
yarn start
```
