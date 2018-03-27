# Websites-Ranking
The backend application uses the following technologies
- Java8
-SpringBoot
-MySQL

The application contains 2 main java services
1# Is the file upload service which is running i a seprate JVM waiting for a file to arrive and then uploaded to DB.
2# Is the main application service running as service container

# File Upload Service
This service run using the following jar
siterank-upload-service-0.0.1-SNAPSHOT.jar

Which basically runs a java program that listes to incoming csv file to a file system specified by you. Once the file arrives, It loads it to the DB and delete it.

To run that service you need to pas in the DB parameters and the location of the file system
Example
java -jar -DDB_DRIVER=com.mysql.jdbc.Driver -DDB_CONNECTION=jdbc:mysql://localhost:3306/SiteRank -DDB_USER=root -DDB_PASSWORD=root -Dfile=./data.csv siterank-upload-service-0.0.1-SNAPSHOT.jar

# Websites-Ranking Service
To run the applicaiton you need to have the following
MYSQL database
Java 8
#Database to start you need to create the following table in the DB
create table Ranks
(
  rank_dt date not null,
  website varchar(200) Not Null,
  visits bigint,
  PRIMARY KEY (website, rank_dt)
);

#Application The application is a using Springboot

to start the service you can run the following jar
siterank-0.0.1-SNAPSHOT.jar.original

You can run the jar in the command line. You need to pass in as System variables the Database url,user and password for your mysql database

For Example:
run command : java -jar -DDB_CONNECTION=jdbc:mysql://localhost:3306/SiteRank -DDB_USER=root -DDB_PASSWORD=root siterank-0.0.1-SNAPSHOT.jar

#Application Functionality

The Application asks to get the top 5 ranked websites from a file. 

I have created two approaches to this and broke them into 2 seperate rest service. 
So The application Exposes 2 main restful services and can be invoked using postman:

ranksite-1 service :
This services sorts and return the top 5 directly from the database. 
assuming that the data will live in a table in the DB. rely on the database to query and sort the ranking for you

This service takes a date string in the format yyyy-dd-mm which is the rank date, 

URL: http://localhost:8080/ranksite-1/2016-01-27

It returns application/json as content-type header

It takes application/json as content-type header

It returns a JSON of the top 5 sites and its ranking

{"google.com.au":171842376,"facebook.com":123831275,"youtube.com":69327140,"google.com":29422150,"ninemsn.com.au":24521168}

ranksite-2 service :
This services sorts and return the top 5 directly in memory. 
assuming that the data will live in memory or a DB hat is not reliable to sort or the amount of data is so large that it might live in distributed DB or Chache.

This service takes also a date string in the format yyyy-dd-mm which is the rank date, 

URL: hhttp://localhost:8080/ranksite-2/2016-01-27

It takes application/json as content-type header

It returns the same results as previous service a JSON of the top 5 sites and its ranking

{"google.com.au":171842376,"facebook.com":123831275,"youtube.com":69327140,"google.com":29422150,"ninemsn.com.au":24521168}

Excluded sites:
Each service will call another servicec called execludedSitesService to fetch the excluded sites within the date provided and will be excluded from the the list.
