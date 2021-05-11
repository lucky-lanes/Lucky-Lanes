# LuckyLanes
This project is for the LuckyLanes bowling alley to keep track of specific athlete data entered by users and stored in a local
database. A user can create an account, log in, view athlete's data, manipulate athlete's data, view printable forms for data gathering, and 
print out athlete's data. The application uses Java and JavaFX. 

## Setup For IDE Use
You'll need to install the following libraries into your project (these libraries are in the google drive)
(Note: libraries besides Java 8 are included in the github repository)

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
A prompt will appear asking you to connect to a database if the program has not been run before. There is a test database called "TestDatabase.mv.db", or alternatively, a new database can be created. You can at later switch databases or create new ones by using the settings drop down and clicking create new database or open existing database. When a new database is created, a default admin account with the username "admin", and password "defaultPassword" is created. It is highly reccomended to change this password. (Note: UI functionality for changing password is not yet implemented) After setting up the database, you will then be prompted with the login screen. Here, you can login with a previously created account or the admin account. You can also switch to the Create Account screen. Depending on what account type you login as, you will be presented with different options.

## Complete documentation
[Documentation Station](https://docs.google.com/document/d/1CYt3Xl13mugHgATuaXxcm7oLQJDwlJRIyBNsAmsLcEI/edit#)

## Dynamo Proposal
https://onedrive.live.com/view.aspx?resid=CAA42815A644A89C!2621&ithint=file%2cdocx&authkey=!APhi9NG0GIarfG4

## Known problems, incomplete features
- When creating a new acount, if you back out before finishing filling out the forms, the half created account is still added to the authorization table and should be removed.
- When creating a new account, an attempt was made to lock data entry until the medical form was filled out. This does not work properly.
- The delete account button is disabled, as it deletes rows from the Authorization table based off of Athlete table id. As of right now there is no consistent feature between the tables to align them. This also breaks the reassignment of authorization level of accounts. Ideally, deleting an account should remove ALL their data (not just in the Auth table or Athlete table, but all the associated form tables, which also may be presently unalignable.) 
- The ability to change a password is not implemented (a function in AuthorizationController is built for it)
- The system to handle coach accounts is not built. (There is code prepared in the TeamController class to handle some of this)
- Need follow this link to set up database pointers —> [Guide for AWS setup] (https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/UsingWithRDS.IAMDBAuth.Connecting.Java.html)
- Entire system is vulnerable to SQL injections, next team will have to use prepared statements to check for injections.  However, we have already accomplished this in some of the classes. 

## License
[Apache License](http://www.apache.org/licenses/)
