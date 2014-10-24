INSERT INTO Role(Name) VALUES ( 'Customer' ); 
INSERT INTO Role(Name) VALUES ( 'Administrator' ); 

INSERT INTO Product( Description, Price, Qty, ImageUrl ) VALUES ( 'Milk', 1, 1000, 'thumbs/1.jpg'); 
INSERT INTO Product(Description, Price, Qty, ImageUrl ) VALUES ( 'Beef', 4, 20, 'thumbs/3.jpg'); 
INSERT INTO Product(Description, Price, Qty, ImageUrl ) VALUES ( 'Vodka', 10, 5, 'thumbs/2.jpg'); 
INSERT INTO Product( Description, Price, Qty, ImageUrl ) VALUES ( 'Apple', 2, 30, 'thumbs/4.jpg'); 

SELECT * FROM Role;
SELECT * FROM Product;

INSERT INTO AppUser( Name, Password, RoleId ) SELECT 'joe', '', RoleId FROM Role WHERE Name = 'Customer'; 
INSERT INTO AppUser( Name, Password, RoleId ) SELECT 'administrator', '', RoleId FROM Role WHERE Name = 'Administrator';  
INSERT INTO AppUser( Name, Password, RoleId ) SELECT 'test1', '', RoleId FROM Role WHERE Name = 'Customer';   
INSERT INTO AppUser( Name, Password, RoleId ) SELECT 'test2', '', RoleId FROM Role WHERE Name = 'Administrator';   
INSERT INTO AppUser( Name, Password, RoleId ) SELECT 'test3', '', RoleId FROM Role WHERE Name = 'Administrator';   
INSERT INTO AppUser( Name, Password, RoleId ) SELECT 'test4', '', RoleId FROM Role WHERE Name = 'Administrator';   

INSERT INTO Log( UserId, Message ) SELECT UserId, 'Message1' FROM AppUser WHERE Name = 'joe'; 
INSERT INTO Log( UserId, Message ) SELECT UserId, 'Message2' FROM AppUser WHERE Name = 'administrator'; 
INSERT INTO Log( UserId, Message ) SELECT UserId, 'Message3' FROM AppUser WHERE Name = 'test2'; 
INSERT INTO Log( UserId, Message ) SELECT UserId, 'Message4' FROM AppUser WHERE Name = 'test3'; 
INSERT INTO Log( UserId, Message ) SELECT UserId, 'Message5' FROM AppUser WHERE Name = 'test2'; 
