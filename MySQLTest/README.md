## Creating a new record in the database MySQL on my site [Agrobuilding.com](https://agrobuilding.com/). Clearing the database after checking  

With the use  
#### Java+JDBC+Selenium+JUnit  
#### Page Object - PageFactory  
  
*** 
  
### Steps  
Go to the home page Agrobuilding.com  
Click on the first news in the sidebar  
Fill in the fields in the form of a comment  
* TextArea - Пример комментария  
* Name - Олег  
* Email - oleg@gmail.com  
* Press the button - Отправить комментарий  
  
### Expected Result  
Email - oleg@gmail.com - is stored in the database with the comment_author identifier   
  
### Post condition  
Delete the record in the database about the new comment  
