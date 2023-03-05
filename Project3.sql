--CREATE A NEW DATABASE

IF NOT EXISTS (SELECT * FROM sys.databases db WHERE db.name = 'MANAGE_PARKING')
	CREATE DATABASE MANAGE_PARKING

GO
USE MANAGE_PARKING

CREATE TABLE dbo.Users(
	username	VARCHAR(100) PRIMARY KEY,
	[password]	VARCHAR(100) NOT NULL,
)

INSERT INTO dbo.Users
VALUES('Duc', '20102000')
INSERT INTO dbo.Users
VALUES('Doanh', '06012000')
INSERT INTO dbo.Users
VALUES('Bang', '11062000')
INSERT INTO dbo.Users
VALUES('Huan', '01012000')
INSERT INTO dbo.Users
VALUES('Nam', '13052000')

CREATE TABLE dbo.Managers(
	username	VARCHAR(100) PRIMARY KEY,
	[password]	VARCHAR(100) NOT NULL,
)

INSERT INTO dbo.Managers
VALUES('Duc', '20102000')

INSERT INTO dbo.Managers
VALUES('Huan', '01012000')

CREATE PROCEDURE addUser(
	@username	VARCHAR(100),
	@password	VARCHAR(100)
)
AS
BEGIN
	INSERT INTO dbo.Users
	VALUES(@username, @password)
END

EXEC addUser 'e','e'

CREATE PROCEDURE addManager(
	@username	VARCHAR(100),
	@password	VARCHAR(100)
)
AS
BEGIN
	INSERT INTO dbo.Managers
	VALUES(@username, @password)
END

CREATE TABLE dbo.Parking(
	[locate]	VARCHAR(100) PRIMARY KEY,
	area		VARCHAR(10) NOT NULL,
	status		BIT NOT NULL
)

INSERT INTO dbo.Parking
VALUES('A1', 'A', 0)
INSERT INTO dbo.Parking
VALUES('A2', 'A', 0)
INSERT INTO dbo.Parking
VALUES('A3', 'A', 0)
INSERT INTO dbo.Parking
VALUES('A4', 'A', 0)
INSERT INTO dbo.Parking
VALUES('A5', 'A', 0)
INSERT INTO dbo.Parking
VALUES('A6', 'A', 0)
INSERT INTO dbo.Parking
VALUES('A7', 'A', 0)
INSERT INTO dbo.Parking
VALUES('A8', 'A', 0)
INSERT INTO dbo.Parking
VALUES('A9', 'A', 0)
INSERT INTO dbo.Parking
VALUES('A10', 'A', 0)
INSERT INTO dbo.Parking
VALUES('B1', 'B', 0)
INSERT INTO dbo.Parking
VALUES('B2', 'B', 0)
INSERT INTO dbo.Parking
VALUES('B3', 'B', 0)
INSERT INTO dbo.Parking
VALUES('B4', 'B', 0)
INSERT INTO dbo.Parking
VALUES('B5', 'B', 0)
INSERT INTO dbo.Parking
VALUES('B6', 'B', 0)
INSERT INTO dbo.Parking
VALUES('B7', 'B', 0)
INSERT INTO dbo.Parking
VALUES('B8', 'B', 0)
INSERT INTO dbo.Parking
VALUES('B9', 'B', 0)
INSERT INTO dbo.Parking
VALUES('B10', 'B', 0)
INSERT INTO dbo.Parking
VALUES('C1', 'C', 0)
INSERT INTO dbo.Parking
VALUES('C2', 'C', 0)
INSERT INTO dbo.Parking
VALUES('C3', 'C', 0)
INSERT INTO dbo.Parking
VALUES('C4', 'C', 0)
INSERT INTO dbo.Parking
VALUES('C5', 'C', 0)
INSERT INTO dbo.Parking
VALUES('C6', 'C', 0)
INSERT INTO dbo.Parking
VALUES('C7', 'C', 0)
INSERT INTO dbo.Parking
VALUES('C8', 'C', 0)
INSERT INTO dbo.Parking
VALUES('C9', 'C', 0)
INSERT INTO dbo.Parking
VALUES('C10', 'C', 0)
INSERT INTO dbo.Parking
VALUES('C11', 'C', 0)
INSERT INTO dbo.Parking
VALUES('C12', 'C', 0)
INSERT INTO dbo.Parking
VALUES('D1', 'D', 0)
INSERT INTO dbo.Parking
VALUES('D2', 'D', 0)
INSERT INTO dbo.Parking
VALUES('D3', 'D', 0)
INSERT INTO dbo.Parking
VALUES('D4', 'D', 0)
INSERT INTO dbo.Parking
VALUES('D5', 'D', 0)
INSERT INTO dbo.Parking
VALUES('D6', 'D', 0)
INSERT INTO dbo.Parking
VALUES('D7', 'D', 0)
INSERT INTO dbo.Parking
VALUES('D8', 'D', 0)
INSERT INTO dbo.Parking
VALUES('D9', 'D', 0)
INSERT INTO dbo.Parking
VALUES('D10', 'D', 0)
INSERT INTO dbo.Parking
VALUES('D11', 'D', 0)
INSERT INTO dbo.Parking
VALUES('D12', 'D', 0)

CREATE TABLE dbo.EntryVehicle(			
	license_vehicle	VARCHAR(100) NOT NULL,
	entry_time		DATETIME NOT NULL,
		CONSTRAINT PK_EntryVehicle PRIMARY KEY(license_vehicle, entry_time),
	type_vehicle	VARCHAR(100) NOT NULL,
	username		VARCHAR(100) 
		CONSTRAINT FK_USERNAME1 FOREIGN KEY REFERENCES dbo.Users(username),
	color			VARCHAR(50),
	[image]			VARCHAR(255) NOT NULL,
	[locate]		VARCHAR(100) 
		CONSTRAINT FK_LOCATE1 FOREIGN KEY REFERENCES dbo.Parking([locate])
	
)

CREATE TABLE dbo.VehicleInParking(
	license_vehicle	VARCHAR(100) PRIMARY KEY,
	entry_time		DATETIME NOT NULL,
	type_vehicle	VARCHAR(100) NOT NULL,
	username		VARCHAR(100) 
		CONSTRAINT FK_USERNAME FOREIGN KEY REFERENCES dbo.Users(username),
	color			VARCHAR(50),
	[image]			VARCHAR(255) NOT NULL,
	[locate]		VARCHAR(100) 
		CONSTRAINT FK_LOCATE FOREIGN KEY REFERENCES dbo.Parking([locate]),
	
)

CREATE PROCEDURE addVehicleInParking(
	@license_vehicle	VARCHAR(100),
	@type_vehicle		VARCHAR(100),
	@username			VARCHAR(100),
	@color				VARCHAR(50),
	@image				VARCHAR(255),
	@locate				VARCHAR(100)
)
AS
BEGIN
	DECLARE @entry_time DATETIME = GETDATE()
	IF NOT EXISTS (SELECT * FROM dbo.VehicleInParking db WHERE db.license_vehicle = @license_vehicle)
	BEGIN
		INSERT INTO dbo.VehicleInParking
		VALUES(@license_vehicle, @entry_time, @type_vehicle, @username, @color, @image, @locate)
		INSERT INTO dbo.EntryVehicle
		VALUES(@license_vehicle, @entry_time, @type_vehicle, @username, @color, @image, @locate)
		UPDATE dbo.Parking
		SET status = 1
		WHERE locate = @locate
	END
END



CREATE TABLE dbo.Bill(

	bill_id			INT IDENTITY(1,1) PRIMARY KEY,
	license_vehicle VARCHAR(100),
	entry_time		DATETIME NOT NULL,
	username		VARCHAR(100) NOT NULL,
	out_time		DATETIME NOT NULL,
	cost			MONEY NOT NULL,
	FOREIGN KEY (license_vehicle, entry_time) REFERENCES dbo.EntryVehicle(license_vehicle, entry_time)
)

CREATE PROCEDURE addBill(
	@license_vehicle	VARCHAR(100)
)
AS
BEGIN
	DECLARE @entry_time	DATETIME 
		SET @entry_time = (SELECT v.entry_time FROM dbo.VehicleInParking AS v WHERE v.license_vehicle = @license_vehicle)
	DECLARE @username	VARCHAR(100)
		SET @username = (SELECT v.username FROM dbo.VehicleInParking AS v WHERE v.license_vehicle = @license_vehicle)
	DECLARE @out_time	DATETIME
		SET @out_time = GETDATE()
	DECLARE @cost		MONEY
	IF (DATEDIFF(DAY, @entry_time, @out_time) = 0)
		SET @cost = 5000
	ELSE
		SET @cost = 5000 + 10000 * (DATEDIFF(DAY, @entry_time, @out_time))
	
	INSERT INTO dbo.Bill(license_vehicle, entry_time, username, out_time, cost)
	VALUES(@license_vehicle, @entry_time, @username, @out_time, @cost)

	UPDATE dbo.Parking
	SET status = 0 WHERE locate = (SELECT locate FROM dbo.VehicleInParking AS v WHERE license_vehicle = @license_vehicle)

	DELETE dbo.VehicleInParking
	WHERE license_vehicle = @license_vehicle
END

EXEC addBill '13D2-0352'

CREATE PROCEDURE Login1(
	@username	VARCHAR(100),
	@password	VARCHAR(100)
)
AS
BEGIN
	SELECT * FROM dbo.Users WHERE username = @username AND password = @password
END

CREATE PROCEDURE GetBillByUsername(
	@username	VARCHAR(100)
)
AS
BEGIN
	SELECT * FROM dbo.Bill AS b WHERE b.username = @username
END

CREATE PROCEDURE GetVehicleInParking(
	@username	VARCHAR(100)
)
AS
BEGIN
	SELECT * FROM dbo.VehicleInParking AS b WHERE b.username = @username
END

CREATE PROCEDURE Statistic(
	@from	DATETIME,
	@to		DATETIME
)
AS
BEGIN
	SELECT COUNT(license_vehicle) AS number_vehicle, SUM(cost) AS revenue FROM dbo.Bill WHERE DATEDIFF(DAY, out_time, @from) <=0  AND DATEDIFF(DAY, out_time , @to) >=0 
END

EXEC Statistic '2023-01-04', '2023-01-04'

SELECT * FROM dbo.VehicleInParking AS b WHERE b.username = 'e'

SELECT p.area
FROM dbo.Parking as p
GROUP BY p.area

CREATE PROCEDURE						
AS
BEGIN
	SELECT p.area
	FROM dbo.Parking as p
	GROUP BY p.area
END

EXEC GetArea

CREATE PROCEDURE AddMap(
	@locate VARCHAR(255),
	@area	VARCHAR(255)
)
AS
BEGIN
	INSERT INTO Parking
	VALUES (@locate , @area, 0);
END

DELETE FROM dbo.Parking
WHere area ='I'







