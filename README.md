# LuckyLanes
This project is for the LuckyLanes bowling alley. Using Java and JavaFX. 

## Setup
You'll need to install the following libraries into your project (these libraries are in the google drive)
   1. pdfblox-2.0.17.jar
        1. This allows you to work with PDF documents, such as creation, manipulation, and extraction
        2. This is within the project by default but you may have to relink it on downloading the project from github
        
   2. boxable-1.5.jar
        1. This used to create tables on top of Apache pdfbox
        2. This is within the project by default but you may have to relink it on downloading the project from github
    
   3. h2-1.4.197.jar
        1. This is a open source JDBC API
        2. Embedded and server modes; in-memory databases
        3. Browser base console application
        4. This is within the project by default but you may have to relink it on downloading the project from github
        
   4. Java SDK 8.1.161 
        1. This is the development Environment we have standardized for the project
        2. This is up on the google drive for the project if you don't have a development environment that runs JavaFX and JavaFXML.
 
[Setup Video for netbeans](https://www.youtube.com/watch?v=RXoIOWlP0dY)<br>
[Setup Video for Intellij](https://youtu.be/BkzNg47k8DQ)
        
## Usage
 ```java
import org.apache.pdfbox.*;
import be.quodlibet.boxable.*;
```

## On launch
A prompt will appear asking you to connect to a database you have the option to dismiss this for the time being and add 
one later. There is a database that is inside the the project files from the repository named "The Final Test.mv.db". 
That database has people entered already. You can at any point switch databases or create new ones by using the settings
 drop down.

## Complete documentation
[Documentation Station](https://docs.google.com/document/d/1VRP-lc39cR5_L7LiJJJuGJgJt4UlZgB-YhaaTuhSCf8/edit#)

## Known problems 
1. when using the included The Final Test.mv.db database there is a problem creating questions for the test. they do not
show up in update questions.
    1. This does not happen when you create a new database on your computer through the program.



## License
[Apache License](http://www.apache.org/licenses/)
