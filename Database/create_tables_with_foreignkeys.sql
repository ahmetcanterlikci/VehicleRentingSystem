CREATE TABLE RegisteredUser(
username varchar(50),
isDeleted boolean,
email varchar(50),
password varchar(50),
name varchar(50),
surname varchar(50),
birthdate datetime,
phone varchar(19),
gender varchar(50),
address varchar(200),
city varchar(50),
country varchar(50),
driverLicenseDate datetime,
discountId bigint,
primary key(username),
unique (discountId)
);

CREATE TABLE OfficeUser(
username varchar(50),
isDeleted boolean,
email varchar(50),
password varchar(50),
name varchar(50),
surname varchar(50),
birthdate datetime,
phone varchar(19),
gender varchar(50),
address varchar(200),
city varchar(50),
country varchar(50),
officeName varchar(100),
primary key(username)
);

CREATE TABLE Administrator(
username varchar(50),
isDeleted boolean,
email varchar(50),
password varchar(50),
name varchar(50),
surname varchar(50),
primary key(username)
);

CREATE TABLE Office(
name varchar(50),
isDeleted boolean,
email varchar(50),
address varchar(200),
phone varchar(19),
fax varchar(50),
workingDays varchar(50),
workingHours varchar(50),
city varchar(50),
country varchar(50),
primary key(name)
);

CREATE TABLE Vehicle(
plateNumber varchar(50),
isDeleted boolean,
physicalStatus varchar(50),
rentingStatus varchar(50),
dailyPrice bigint,
class varchar(50),
gearType varchar(50),
fuelType varchar(50),
type varchar(50),
numberOfSeats varchar(50),
avaliableLuggage varchar(50),
minimumYearsOfLicense varchar(50),
airbags varchar(20),
airConditioning varchar(20),
currentOfficeName varchar(100),
name varchar(50),
brand varchar(50),
modelNumber bigint,
rentStart datetime,
rentEnd datetime,
primary key(plateNumber)
);

CREATE TABLE Renting(
id bigint AUTO_INCREMENT,
returningDate datetime,
receivingDate datetime,
returningOffice varchar(100),
receivingOffice varchar(100),
rentingDate datetime,
userName varchar(50),
userSurname varchar(50),
userAddress varchar(200),
userUserName varchar(50),
userPhone varchar(50),
paymentType varchar(50),
totalPrice bigint,
socialSecurityNo varchar(50),
vehicleName varchar(50),
vehicleBrand varchar(50),
vehiclePlateNumber varchar(50),
status varchar(50),
primary key(id)
);

CREATE TABLE Chart(
userUserName varchar(50),
returningDate datetime,
receivingDate datetime,
returningOffice varchar(100),
receivingOffice varchar(100),
userName varchar(50),
userSurname varchar(50),
userAddress varchar(200),
userPhone varchar(50),
totalPrice bigint,
vehicleName varchar(50),
vehicleBrand varchar(50),
vehiclePlateNumber varchar(50),
discountId bigint,
primary key(userUserName),
unique (discountId)
);

CREATE TABLE Discount(
id bigint AUTO_INCREMENT,
percentage bigint,
isDeleted boolean,
primary key(id)
);

ALTER TABLE registereduser
ADD FOREIGN KEY(discountId) references discount(id);

ALTER TABLE officeuser
ADD FOREIGN KEY(OfficeName) references office(name);

ALTER TABLE vehicle
ADD FOREIGN KEY(currentOfficeName) references office(name);

ALTER TABLE renting
ADD FOREIGN KEY(userUserName) references registereduser(username),
ADD FOREIGN KEY(vehiclePlateNumber) references vehicle(plateNumber);

ALTER TABLE chart
ADD FOREIGN KEY(userUserName) references registereduser(username),
ADD FOREIGN KEY(vehiclePlateNumber) references vehicle(plateNumber),
ADD FOREIGN KEY(discountId) references discount(id);
