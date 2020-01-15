INSERT INTO Administrator VALUES ('Admin Kenan',False,'kenan.grent@hotmail.com',SHA1('Kenan123'),'Kenan','Sonmez');

INSERT INTO Discount(percentage,isDeleted) VALUES ('15',False);
INSERT INTO Discount(percentage,isDeleted) VALUES ('20',False);
INSERT INTO Discount(percentage,isDeleted) VALUES ('25',False);
INSERT INTO Discount(percentage,isDeleted) VALUES ('30',False);
INSERT INTO Discount(percentage,isDeleted) VALUES ('35',False);

INSERT INTO RegisteredUser VALUES ('Kazuci',False,'kazuci@hotmail.com',SHA1('Kazuci123'),'Kaan','Tetik','1994-05-15 23:59:59','05310511205','Male','Uskudar','Istanbul','Turkey','2017-09-19 23:59:59',1);
INSERT INTO RegisteredUser VALUES ('Muffin',False,'muffin@hotmail.com',SHA1('Muffin123'),'Halit','Cakmak','1996-04-01 23:59:59','05357623176','Male','Kadikoy','Istanbul','Turkey','2015-03-15 23:59:59',2);
INSERT INTO RegisteredUser VALUES ('Myolneer',False,'myolneer@hotmail.com',SHA1('Myolneer123'),'Selim','Basmaz','1989-08-06 23:59:59','05336170876','Male','Taksim','Istanbul','Turkey','2012-04-02 23:59:59',3);
INSERT INTO RegisteredUser VALUES ('Fortified',False,'fortfied@hotmail.com',SHA1('Fortified123'),'Ramazan','Korkusuz','1992-02-13 23:59:59','05376717218','Male','Besiktas','Istanbul','Turkey','2002-12-26 23:59:59',4);
INSERT INTO RegisteredUser VALUES ('Arcadius',False,'arcadius@hotmail.com',SHA1('Arcadius123'),'Ilayda','Durmaz','1998-01-20 23:59:59','05393639237','Female','Cekmekoy','Istanbul','Turkey','2006-11-30 23:59:59',5);


INSERT INTO Office VALUES ('Kadikoy Office',False,'kadikoy.grent@hotmail.com','Kadikoy','03224323307','08504323348','Monday-Friday','07.30-23.45','Istanbul','Turkey');
INSERT INTO Office VALUES ('Sinanaga Office',False,'sislihane.grent@hotmail.com','Sinanaga','04228523347','08504323347','Monday-Friday','07.30-23.45','Ankara','Turkey');
INSERT INTO Office VALUES ('Izmir Office',False,'izmir.grent@hotmail.com','Balsova','04251895162','08504323347','Monday-Friday','07.30-23.45','Izmir','Turkey');
INSERT INTO Office VALUES ('Bursa Office',False,'bursa.grent@hotmail.com','Keles','04582979375','0850852917','Monday-Friday','07.30-23.45','Bursa','Turkey');
INSERT INTO Office VALUES ('Erzurum Office',False,'erzurum.grent@hotmail.com','askale','04252862586','08504323347','Monday-Friday','07.30-23.45','Erzurum','Turkey');

INSERT INTO Office VALUES ('Dortmund Office',False,'dortmund.grent@hotmail.com','Dortmund Airport','01682582589','01684292329','Monday-Friday','07.30-23.45','Dortmund','Germany');
INSERT INTO Office VALUES ('Kavala Office',False,'kavala.grent@hotmail.com','Kavala Airport','02581241248','02585178258','Monday-Friday','07.30-23.45','Kavala','Greece');

INSERT INTO OfficeUser VALUES ('Ahmet Isik',False,'ahmet.isik.grent@hotmail.com',SHA1('Ahmet123'),'Ahmet','Isik','1985-10-29 23:59:59','03242621708','Male','Kadikoy','Istanbul','Turkey','Kadikoy Office');
INSERT INTO OfficeUser VALUES ('Duygu Celik',False,'duygu.celik.grent@hotmail.com',SHA1('Duygu123'),'Duygu','Celik','1982-12-18 23:59:59','03242626590','Female','Sinanaga','Ankara','Turkey','Sinanaga Office');
INSERT INTO OfficeUser VALUES ('Ayhan Tek',False,'ayhan.tek.grent@hotmail.com',SHA1('Ayhan123'),'Ayhan','Tek','1983-02-02 23:59:59','03262726532','Male','Balsova','Izmir','Turkey','Izmir Office');
INSERT INTO OfficeUser VALUES ('Kardelen Aktan',False,'kardelen.aktan.grent@hotmail.com',SHA1('Kardelen123'),'Kardelen','Aktan','1982-12-18 23:59:59','03256122368','Female','Keles','Bursa','Turkey','Bursa Office');
INSERT INTO OfficeUser VALUES ('Berat Saygili',False,'berat.saygili.grent@hotmail.com',SHA1('Berat123'),'Berat','Saygili','1986-07-06 23:59:59','03289237810','Male','Askale','Erzurum','Turkey','Erzurum Office');

INSERT INTO OfficeUser VALUES ('Ecem Yalcın',False,'ecem.yalcın.grent@hotmail.com',SHA1('ecemyalcın123'),'Ecem','Yalcın','1981-01-10 23:59:59','01685285147','Female','Dortmund Airport','Dortmund','Germany','Dortmund Office');
INSERT INTO OfficeUser VALUES ('Ecem Tunalı',False,'ecem.tunalı.grent@hotmail.com',SHA1('ecemtunalı123'),'Ecem','Tunalı','1983-01-20 23:59:59','02586167810','Female','Kavala Airport','Kavala','Greece','Kavala Office');


INSERT INTO Vehicle VALUES ('35BC173',False,'Good','Rented','125','Medium','Manual','Fuel','SUV','5','25kg','1','yes','yes','Izmir Office','GLS 580 SUV 2018','Mercedes-Benz',1120190117,'2019-12-14 21:59:59','2020-02-07 12:59:59');
INSERT INTO Vehicle VALUES ('35PS154',False,'Good','Not rented','125','Medium','Manual','Fuel','SUV','5','25kg','1','yes','yes','Izmir Office','GLS 580 SUV 2018','Mercedes-Benz',1120190117,null,null);
INSERT INTO Vehicle VALUES ('25AK304',False,'Good','Rented','125','Medium','Manual','Fuel','SUV','5','25kg','1','yes','yes','Erzurum Office','GLS 580 SUV 2018','Mercedes-Benz',1120190117,'2019-12-15 11:59:59','2020-02-15 18:59:59');
INSERT INTO Vehicle VALUES ('16AP589',False,'Good','Not Rented','75','Luxury','Auto','Fuel','Hatchback','5','15kg','2','yes','yes','Bursa Office','Ford Figo 2018','Ford',2220180515,null,null);
INSERT INTO Vehicle VALUES ('16CH915',False,'Good','Not Rented','75','Luxury','Auto','Fuel','Hatchback','5','15kg','2','yes','yes','Bursa Office','Ford Figo 2018','Ford',2220180515,null,null);
INSERT INTO Vehicle VALUES ('34TA462',False,'Good','Rented','180','Luxury','Auto','Fuel','Hatchback','5','20kg','2','yes','yes','Kadikoy Office','Renault KWID 2019','Renault',3220170327,'2019-12-16 14:59:59','2020-02-08 19:59:59');
INSERT INTO Vehicle VALUES ('34SK274',False,'Good','Rented','55','Economic','Manual','Fuel','Sedan','5','23kg','2','yes','yes','Kadikoy Office','Mercedes-Benz CLA 2012','Mercedes-Benz',1320190502,'2019-12-17 19:59:59','2020-02-09 10:59:59');
INSERT INTO Vehicle VALUES ('34ZN134',False,'Good','Rented','60','Medium','Auto','Diesel','Sedan','5','22kg','2','yes','yes','Kadikoy Office','Mercedes-Benz E-Class 2014','Mercedes-Benz',1320111111,'2019-12-18 14:59:59','2020-02-09 19:59:59');
INSERT INTO Vehicle VALUES ('34AJ189',False,'Good','Not Rented','110','Medium','Auto','Diesel','Sedan','5','22kg','2','yes','yes','Kadikoy Office','Mercedes-Benz E-Class 2017','Mercedes-Benz',1320180202,null,null);
INSERT INTO Vehicle VALUES ('34GU581',False,'Good','Rented','45','Economic','Auto','Diesel','SUV','5','24kg','2','yes','yes','Kadikoy Office','Ford EcoSport 2017','Ford',2120190405,'2019-12-19 16:59:59','2020-02-09 16:59:59');
INSERT INTO Vehicle VALUES ('34SB341',False,'Good','Not Rented','165','Luxury','Auto','Diesel','SUV','5','21kg','2','yes','yes','Kadikoy Office','Ford Endeavour 2014','Ford',2120190913,null,null);
INSERT INTO Vehicle VALUES ('06ST189',False,'Good','Not Rented','240','Luxury','Manual','Fuel','Sport','4','10kg','2','yes','yes','Sinanaga Office','AMG C 43 Coupe 2018','Mercedes-Benz',1420151222,null,null);
INSERT INTO Vehicle VALUES ('06VY671',False,'Good','Rented','45','Medium','Auto','Diesel','Truck','5','15kg','2','yes','yes','Sinanaga Office','Ford F-250 Super Duty 2019','Ford',2620106415,'2019-12-20 16:59:59','2020-02-05 16:59:59');
INSERT INTO Vehicle VALUES ('06KG256',False,'Good','Not Rented','70','Medium','Manual','Diesel','Hybrid/Electric','5','17kg','2','yes','yes','Sinanaga Office','Kia Niro 2019','Kia',4520100518,null,null);

INSERT INTO Vehicle VALUES ('DOGO876',False,'Good','Rented','210','Luxury','Manual','Fuel','SUV','5','37kg','2','yes','yes','Dortmund Office','GLA 200 2018','Mercedes-Benz',1112558069,'2019-12-20 21:59:59','2020-02-07 12:59:59');
INSERT INTO Vehicle VALUES ('DOBA282',False,'Good','Not rented','210','Luxury','Manual','Fuel','SUV','5','37kg','2','yes','yes','Dortmund Office','GLA 200 2018','Mercedes-Benz',1112558069,null,null);
INSERT INTO Vehicle VALUES ('KBP1812',False,'Good','Not rented','170','Medium','Auto','Diesel','Sedan','5','12kg','1','yes','yes','Kavala Office','A 180 d Sedan Style 2019','Mercedes-Benz',1369226169,null,null);
INSERT INTO Vehicle VALUES ('KBP2671',False,'Good','Rented','125','Economic','Auto','Diesel','Hatchback','5','29kg','1','yes','yes','Kavala Office','A 180 d Progressive 2018','Mercedes-Benz',1212625258,'2019-12-19 11:59:59','2020-02-15 18:59:59');


INSERT INTO Renting(returningDate,receivingDate,returningOffice,receivingOffice,rentingDate,userName,userSurname,userAddress,userUserName,userPhone,paymentType,totalPrice,socialSecurityNo,vehicleName,vehicleBrand,vehiclePlateNumber,status,orderNumber) VALUES ('2020-02-07 12:59:59','2019-12-14 21:59:59','Izmir Office','Izmir Office','2019-12-13 11:59:59','Kaan','Tetik','Uskudar','Kazuci','05310511205','Credit Card','6750','57191500172','GLS 580 SUV 2018','Mercedes-Benz','35BC173','Active','ORD10001');
INSERT INTO Renting(returningDate,receivingDate,returningOffice,receivingOffice,rentingDate,userName,userSurname,userAddress,userUserName,userPhone,paymentType,totalPrice,socialSecurityNo,vehicleName,vehicleBrand,vehiclePlateNumber,status,orderNumber) VALUES ('2020-02-15 18:59:59','2019-12-15 11:59:59','Izmir Office','Erzurum Office','2019-12-14 09:59:59','Halit','Cakmak','Kadikoy','Muffin','05357623176','Debit Card','7625','28158027812','GLS 580 SUV 2018','Mercedes-Benz','25AK304','Active', 'ORD10002');
INSERT INTO Renting(returningDate,receivingDate,returningOffice,receivingOffice,rentingDate,userName,userSurname,userAddress,userUserName,userPhone,paymentType,totalPrice,socialSecurityNo,vehicleName,vehicleBrand,vehiclePlateNumber,status,orderNumber) VALUES ('2020-02-08 19:59:59','2019-12-16 14:59:59','Izmir Office','Kadikoy Office','2019-12-15 09:59:59','Selim','Basmaz','Taksim','Myolneer','05336170876','Credit Card','9540','92855126169','Renault KWID 2019','Renault','34TA462','Active', 'ORD10003');
INSERT INTO Renting(returningDate,receivingDate,returningOffice,receivingOffice,rentingDate,userName,userSurname,userAddress,userUserName,userPhone,paymentType,totalPrice,socialSecurityNo,vehicleName,vehicleBrand,vehiclePlateNumber,status,orderNumber) VALUES ('2020-02-09 10:59:59','2019-12-17 19:59:59','Kadikoy Office','Kadikoy Office','2019-12-16 09:59:59','Ramazan','Korkusuz','Besiktas','Fortified','05376717218','Credit Card','2915','15809618096','Mercedes-Benz CLA 2012','Mercedes-Benz','34SK274','Active', 'ORD10004');
INSERT INTO Renting(returningDate,receivingDate,returningOffice,receivingOffice,rentingDate,userName,userSurname,userAddress,userUserName,userPhone,paymentType,totalPrice,socialSecurityNo,vehicleName,vehicleBrand,vehiclePlateNumber,status,orderNumber) VALUES ('2020-02-09 19:59:59','2019-12-18 14:59:59','Kadikoy Office','Kadikoy Office','2019-12-17 09:59:59','Ilayda','Durmaz','Cekmekoy','Arcadius','05393639237','Debit Card','3120','51789527612','Mercedes-Benz E-Class 2014','Mercedes-Benz','34ZN134','Active', 'ORD10005');
INSERT INTO Renting(returningDate,receivingDate,returningOffice,receivingOffice,rentingDate,userName,userSurname,userAddress,userUserName,userPhone,paymentType,totalPrice,socialSecurityNo,vehicleName,vehicleBrand,vehiclePlateNumber,status,orderNumber) VALUES ('2020-02-09 16:59:59','2019-12-19 16:59:59','Kadikoy Office','Kadikoy Office','2019-12-18 09:59:59','Kaan','Tetik','Uskudar','Kazuci','05310511205','Credit Card','2295','57191500172','Ford EcoSport 2017','Ford','34GU581','Active', 'ORD10006');
INSERT INTO Renting(returningDate,receivingDate,returningOffice,receivingOffice,rentingDate,userName,userSurname,userAddress,userUserName,userPhone,paymentType,totalPrice,socialSecurityNo,vehicleName,vehicleBrand,vehiclePlateNumber,status,orderNumber) VALUES ('2020-02-05 16:59:59','2019-12-20 16:59:59','Kadikoy Office','Sinanaga Office','2019-12-19 09:59:59','Halit','Cakmak','Kadikoy','Muffin','05357623176','Debit Card','2070','28158027812','Ford F-250 Super Duty 2019','Ford','06VY671','Active', 'ORD10007');

INSERT INTO Renting(returningDate,receivingDate,returningOffice,receivingOffice,rentingDate,userName,userSurname,userAddress,userUserName,userPhone,paymentType,totalPrice,socialSecurityNo,vehicleName,vehicleBrand,vehiclePlateNumber,status, orderNumber) VALUES ('2020-02-09 16:59:59','2019-12-21 16:59:59','Dortmund Office','Dortmund Office','2019-12-20 09:59:59','Kaan','Tetik','Uskudar','Kazuci','05310511205','Credit Card','8190','57191500172','GLA 200 2018','Mercedes-Benz','DOGO876','Active', 'ORD10008');
INSERT INTO Renting(returningDate,receivingDate,returningOffice,receivingOffice,rentingDate,userName,userSurname,userAddress,userUserName,userPhone,paymentType,totalPrice,socialSecurityNo,vehicleName,vehicleBrand,vehiclePlateNumber,status, orderNumber) VALUES ('2020-02-05 16:59:59','2019-12-22 16:59:59','Kavala Office','Kavala Office','2019-12-21 09:59:59','Halit','Cakmak','Kadikoy','Muffin','05357623176','Debit Card','5500','28158027812','A 180 d Progressive 2018','Mercedes-Benz','KBP2671','Active', 'ORD10009');

INSERT INTO Chart VALUES ('Kazuci','2020-02-07 12:59:59','2019-12-30 21:59:59','Izmir Office','Izmir Office','Kaan','Tetik','Uskudar','05310511205','125','4750','GLS 580 SUV 2018','Mercedes-Benz','35PS154',1);
INSERT INTO Chart VALUES ('Muffin','2020-02-15 18:59:59','2019-12-18 11:59:59','Izmir Office','Bursa Office','Halit','Cakmak','Kadikoy','05357623176','75','4350','Ford Figo 2018','Ford','16AP589',2);
INSERT INTO Chart VALUES ('Myolneer','2020-02-08 19:59:59','2019-12-16 14:59:59','Kadikoy Office','Bursa Office','Selim','Basmaz','Taksim','05336170876','75','3975','Ford Figo 2018','Ford','16CH915',3);
INSERT INTO Chart VALUES ('Fortified','2020-02-02 10:59:59','2019-12-19 19:59:59','Bursa Office','Kadikoy Office','Ramazan','Korkusuz','Besiktas','05376717218','3740','55','Mercedes-Benz E-Class 2017','Mercedes-Benz','34AJ189',4);
INSERT INTO Chart VALUES ('Arcadius','2020-02-19 19:59:59','2019-12-21 14:59:59','Kadikoy Office','Sinanaga Office','Ilayda','Durmaz','Cekmekoy','05393639237','14160','65','AMG C 43 Coupe 2018','Mercedes-Benz','06ST189',5);

