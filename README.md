# LuckyLanes
This project is for the LuckyLanes bowling alley to keep track of specific athlete data entered by a user and stored in a local
database. The user can view athlete's data, manipulate athlete's data, view printable forms for data gathering, and 
print out athlete's data. The application uses Java and JavaFX. 

## Setup For IDE Use
You'll need to install the following libraries into your project (these libraries are in the google drive)
   1. pdfblox-2.0.17.jar
        1. This allows you to work with PDF documents, such as creation, manipulation, and extraction
        2. This is within the project by default but you may have to relink it on downloading the project from github
        
   2. boxable-1.5.jar
        1. This used to create tables on top of Apache pdfbox
        2. This is within the project by default but you may have to relink it on downloading the project from github
        3. This can be done through the properties settings of the project. 
    
   3. h2-1.4.197.jar
        1. This is a open source JDBC API
        2. Embedded and server modes; in-memory databases
        3. Browser base console application
        4. This is within the project by default but you may have to relink it on downloading the project from github
        5. This can be done through the properties settings of the project. 
        
   4. Java SDK 8.1.161 
        1. This is the development Environment we have standardized for the project
        2. This is up on the google drive for the project if you don't have a development environment that runs JavaFX and JavaFXML.
        3. This can be done through the properties settings of the project. 
 
[Setup Video for netbeans](https://www.youtube.com/watch?v=RXoIOWlP0dY)<br>
[Setup Video for Intellij](https://youtu.be/BkzNg47k8DQ)

## Setup For JAR File Use
   1. Download the zipped folder titled LuckyLanes. 
   2. Make sure that the folders titled src with folders title main and DocumentsPDF inside the src folder.
   3. Make sure that the src folder and the JAR File are inside the LuckyLanes Folder.

[Setup Video for Jar File Use](https://drive.google.com/drive/u/3/folders/1Hl_rNmPjIei2WYsT1fumofl10czmwujY)
        
## Usage
 ```java
import org.apache.pdfbox.*;
import be.quodlibet.boxable.*;
```

## On launch
A prompt will appear asking you to connect to a database, you have the option to dismiss this for the time being and add 
one later. There are a couple databases that are inside the the project files from the repository named "TheFinalTest.mv.db" and "TestDatabases.mv.db". TestDatabases file has group members testing the program with data from themselves and TheFinalTest database has random people entered already. You can at any point switch databases or create new ones by using the settings
 drop down and clicking create new database or open existing database.

## Complete documentation
[Documentation Station](https://docs.google.com/document/d/1CYt3Xl13mugHgATuaXxcm7oLQJDwlJRIyBNsAmsLcEI/edit#)

## Known problems 
1. when using the included TheFinalTest.mv.db database there is a problem creating questions for the test. they do not
show up in update questions.
    1. This does not happen when you create a new database on your computer through the program.
2. The Add New Athlete section adds another bowler profile when you click add new baseball athlete. 



## License
[Apache License](http://www.apache.org/licenses/)
