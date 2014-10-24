
CREATE SCHEMA app13199358;

SET SCHEMA app13199358;

CREATE TABLE Product ( 
	ProductId          integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	Description        varchar(200) NOT NULL  ,
	Price              decimal(6) NOT NULL DEFAULT 0 ,
	Qty                decimal(5) NOT NULL DEFAULT 0 ,
	ImageUrl	   varchar(100) NOT NULL DEFAULT 'thumbs/noimage.jpg' ,		
	Timestamp          timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	CONSTRAINT PK_PRODUCT PRIMARY KEY ( ProductId )
 );

CREATE TABLE Role ( 
	RoleId             integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	Name               varchar(100) NOT NULL  ,
	CONSTRAINT PK_ROLE PRIMARY KEY ( RoleId )
 );

CREATE TABLE AppUser ( 
	UserId             integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	Name               varchar(100) NOT NULL  ,
	Password           varchar(100) NOT NULL  ,
	RoleId             integer NOT NULL  ,
	Timestamp          timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	CONSTRAINT PK_USER PRIMARY KEY ( UserId )
 );

CREATE TABLE Comment ( 
	CommentId          integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	UserId             integer NOT NULL  ,
	ProductId          integer NOT NULL  ,
	Text               varchar(250) NOT NULL  ,
	Timestamp          timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	CONSTRAINT PK_COMMENT PRIMARY KEY ( CommentId )
 );

CREATE INDEX SQL141003162219760 ON Comment ( UserId );

CREATE INDEX SQL141003162246430 ON Comment ( ProductId );

CREATE TABLE Log ( 
	LogId              integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	UserId             integer NOT NULL  ,
	Message            varchar(1000)   ,
	Timestamp          timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	CONSTRAINT PK_LOG PRIMARY KEY ( LogId )
 );

CREATE INDEX SQL141003162328820 ON Log ( UserId );



ALTER TABLE Comment ADD CONSTRAINT fk_comment_user FOREIGN KEY ( UserId ) REFERENCES AppUser( UserId ) ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE Comment ADD CONSTRAINT fk_comment_product FOREIGN KEY ( ProductId ) REFERENCES Product( ProductId ) ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE Log ADD CONSTRAINT fk_log_user FOREIGN KEY ( UserId ) REFERENCES AppUser( UserId ) ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE User ADD CONSTRAINT fk_user_role FOREIGN KEY ( RoleId ) REFERENCES Role( RoleId ) ON DELETE CASCADE ON UPDATE NO ACTION;

