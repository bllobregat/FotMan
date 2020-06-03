insert into player (name,surname,age,address,email,phoneNumber,weight,height,nationality,personalNotes) values
("Victor","Valdes",27,"C/Sant Pere Nº2","victorvaldes@email.com","65998655",85.6,185.3, "Spaniard", "Great Goalkepeer");
insert into player (name,surname,age,address,email,phoneNumber,weight,height,nationality,personalNotes) values
("Carlos","Puyol",31,"C/Major Nº34","carlespuyol@email.com","65321315",78.9,179, "Spaniard", "Brave Defender");
insert into player (name,surname,age,address,email,phoneNumber,weight,height,nationality,personalNotes) values
("Andres","Iniesta",28,"C/Diagonal Nº89","andresiniesta@email.com","656110115",70.5,171, "Spaniard", "Skillest Player");
insert into player (name,surname,age,address,email,phoneNumber,weight,height,nationality,personalNotes) values
("Andrea","Pirlo",33,"C/Maggiore","andreapirlo@email.com","656110115",79.5,173, "Italian", "Brightest mind");
insert into player (name,surname,age,address,email,phoneNumber,weight,height,nationality,personalNotes) values
("Andriy","Schevcenko",29,"Piazza Poppolo 31","schevcenko@email.com","656110115",80.6,173, "Ukranian", "Striker with pure skill");
insert into player (name,surname,age,address,email,phoneNumber,weight,height,nationality,personalNotes) values
("Ryan","Giggs",26," 35 Handbridge ","ryangiggs@email.com","6567575",77.2,170, "Wells", "Makes football easy");

INSERT INTO coach (name, surname, age, address, email, phoneNumber,licence) VALUES
('Jose','Guardiola',45,'44 Warrington Street','joseguardiola@gmail.com','645645656',"A-325589");
INSERT INTO coach (name, surname, age, address, email, phoneNumber,licence) VALUES
('Carlo','Ancelotti',50,'Piazza Poppolo 23','carloancelotti@gmail.com','6365421332',"A-365421");
INSERT INTO coach (name, surname, age, address, email, phoneNumber,licence) VALUES
('Alex','Ferguson',60,'96 Poplar Long Road ','alexferguson@gmail.com','633324566',"S+-123092");

INSERT INTO team (name,division,stadium) VALUES("FC Barcelona", "Liga BBVA", "Nou Camp");
INSERT INTO team (name,division,stadium) VALUES("Milan AC", "Calcio", "San Siro");
INSERT INTO team (name,division,stadium) VALUES("Manchester United", "Premier League", "Old Trafford");

INSERT INTO user (name, surname, age, address, email, phoneNumber, role,password,enabled) VALUES
('admin','admin',10,'admin','admin','admin','admin',"$2a$10$wo7946N3Pg4jujeLet8YHO4hOcDPz3UpjBlzZ9rqBQ0aIX5owXHw.",true);

INSERT INTO player_compose_team (idPlayer,idTeam,position,number,contract_starts,contract_ends) VALUES (2,1,"Defender",2,"30/8/2018","8/10/2023");
INSERT INTO player_compose_team (idPlayer,idTeam,position,number,contract_starts,contract_ends) VALUES (3,1,"Midfielder",5,"24/5/2017","8/10/2020");
INSERT INTO player_compose_team (idPlayer,idTeam,position,number,contract_starts,contract_ends) VALUES (4,2,"Midfielder",4,"10/9/2017","7/12/2022");

INSERT INTO coach_manage_team (idCoach,idTeam,contract_starts,contract_ends) VALUES (1,1,"24/9/2017","7/12/2022");
INSERT INTO coach_manage_team (idCoach,idTeam,contract_starts,contract_ends) VALUES (2,2,"10/7/2020","7/10/2021");
INSERT INTO coach_manage_team (idCoach,idTeam,contract_starts,contract_ends) VALUES (3,3,"1/2/2019","30/5/2021");




