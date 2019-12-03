INSERT INTO Administrator VALUES ('Admin Kenan',False,'kenan.grent@hotmail.com',SHA1('Kenan123'),'Kenan','Sönmez');

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

INSERT INTO Office VALUES ('Kadikoy Office',False,'kadikoy.grent@hotmail.com','Kadikoy','03224323307','08504323348','Pazartesi-Cuma','07.30-23.45','Istanbul','Turkey');
INSERT INTO Office VALUES ('Sinanaga Office',False,'sislihane.grent@hotmail.com','Sinanaga','04228523347','08504323347','Pazartesi-Cuma','07.30-23.45','Ankara','Turkey');
INSERT INTO Office VALUES ('Izmir Office',False,'izmir.grent@hotmail.com','Balsova','04251895162','08504323347','Pazartesi-Cuma','07.30-23.45','Izmir','Turkey');
INSERT INTO Office VALUES ('Bursa Office',False,'bursa.grent@hotmail.com','Keles','04582979375','0850852917','Pazartesi-Cuma','07.30-23.45','Bursa','Turkey');
INSERT INTO Office VALUES ('Erzurum Office',False,'erzurum.grent@hotmail.com','askale','04252862586','08504323347','Pazartesi-Cuma','07.30-23.45','Erzurum','Turkey');

INSERT INTO OfficeUser VALUES ('Ahmet Isik',False,'ahmet.isik.grent@hotmail.com',SHA1('Ahmet123'),'Ahmet','Isik','1985-10-29 23:59:59','03242621708','Male','Kadikoy','Istanbul','Turkey','Kadikoy Office');
INSERT INTO OfficeUser VALUES ('Duygu Celik',False,'duygu.celik.grent@hotmail.com',SHA1('Duygu123'),'Duygu','Celik','1982-12-18 23:59:59','03242626590','Female','Sinanaga','Ankara','Turkey','Sinanaga Office');
INSERT INTO OfficeUser VALUES ('Ayhan Tek',False,'ayhan.tek.grent@hotmail.com',SHA1('Ayhan123'),'Ayhan','Tek','1983-02-02 23:59:59','03262726532','Male','Balsova','Izmir','Turkey','Izmir Office');
INSERT INTO OfficeUser VALUES ('Kardelen Aktan',False,'kardelen.aktan.grent@hotmail.com',SHA1('Kardelen123'),'Kardelen','Aktan','1982-12-18 23:59:59','03256122368','Female','Keles','Bursa','Turkey','Bursa Office');
INSERT INTO OfficeUser VALUES ('Berat Saygılı',False,'berat.saygılı.grent@hotmail.com',SHA1('Berat123'),'Berat','Saygılı','1986-07-06 23:59:59','03289237810','Female','Askale','Erzurum','Turkey','Erzurum Office');

INSERT INTO Vehicle VALUES ('35BC173',False,'Good','Rented','50','Medium','Manual','Fuel','SUV','5','25kg','1','yes','yes','Izmir Office','GLS 580 SUV 2018','Mercedes-Benz',1120190117,'2019-12-02 21:59:59','2019-12-07 12:59:59');
INSERT INTO Vehicle VALUES ('35PS154',False,'Good','Not rented','50','Medium','Manual','Fuel','SUV','5','25kg','1','yes','yes','Izmir Office','GLS 580 SUV 2018','Mercedes-Benz',1120190117,null,null);
INSERT INTO Vehicle VALUES ('25AK304',False,'Good','Rented','50','Medium','Manual','Fuel','SUV','5','25kg','1','yes','yes','Erzurum Office','GLS 580 SUV 2018','Mercedes-Benz',1120190117,'2019-12-01 11:59:59','2019-12-15 18:59:59');
INSERT INTO Vehicle VALUES ('16AP589',False,'Good','Not Rented','75','Luxury','Auto','Fuel','Hatchbak','5','15kg','2','yes','yes','Bursa Office','Ford Figo 2018','Ford',2220180515,null,null);
INSERT INTO Vehicle VALUES ('16CH915',False,'Good','Not Rented','75','Luxury','Auto','Fuel','Hatchbak','5','15kg','2','yes','yes','Bursa Office','Ford Figo 2018','Ford',2220180515,null,null);
INSERT INTO Vehicle VALUES ('34TA462',False,'Good','Rented','80','Luxury','Auto','Fuel','Hatchbak','5','20kg','2','yes','yes','Kadikoy Office','Renault KWID 2019','Renault',3220170327,'2019-11-27 14:59:59','2019-12-08 19:59:59');
INSERT INTO Vehicle VALUES ('34SK274',False,'Good','Rented','55','Economic','Manual','Fuel','Sedan','5','23kg','2','yes','yes','Kadikoy Office','Mercedes-Benz CLA 2012','Mercedes-Benz',1320190502,'2019-11-24 19:59:59','2019-12-09 10:59:59');
INSERT INTO Vehicle VALUES ('34ZN134',False,'Good','Rented','60','Medium','Auto','Diesel','Sedan','5','22kg','2','yes','yes','Kadikoy Office','Mercedes-Benz E-Class 2014','Mercedes-Benz',1320111111,'2019-11-25 14:59:59','2019-12-09 19:59:59');
INSERT INTO Vehicle VALUES ('34AJ189',False,'Good','Not Rented','60','Medium','Auto','Diesel','Sedan','5','22kg','2','yes','yes','Kadikoy Office','Mercedes-Benz E-Class 2017','Mercedes-Benz',1320180202,null,null);
INSERT INTO Vehicle VALUES ('34GU581',False,'Good','Rented','45','Economic','Auto','Diesel','SUV','5','24kg','2','yes','yes','Kadikoy Office','Ford EcoSport 2017','Ford',2120190405,'2019-11-30 16:59:59','2019-12-09 16:59:59');
INSERT INTO Vehicle VALUES ('34SB341',False,'Good','Not Rented','70','Luxury','Auto','Diesel','SUV','5','21kg','2','yes','yes','Kadikoy Office','Ford Endeavour 2014','Ford',2120190913,null,null);
INSERT INTO Vehicle VALUES ('06ST189',False,'Good','Not Rented','60','Luxury','Manual','Fuel','Sport','4','10kg','2','yes','yes','Sinanaga Office','AMG C 43 Coupe 2018','Mercedes-Benz',1420151222,null,null);
INSERT INTO Vehicle VALUES ('06VY671',False,'Good','Rented','45','Medium','Auto','Diesel','Truck','5','15kg','2','yes','yes','Sinanaga Office','Ford F-250 Super Duty 2019','Ford',2620106415,'2019-11-29 16:59:59','2019-12-05 16:59:59');
INSERT INTO Vehicle VALUES ('06KG256',False,'Good','Not Rented','70','Medium','Manual','Diesel','Hybrid/Electric','5','17kg','2','yes','yes','Sinanaga Office','Kia Niro 2019','Kia',4520100518,null,null);
