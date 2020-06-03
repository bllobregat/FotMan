drop database fotman;
create DATABASE IF NOT EXISTS fotman;
USE fotman;


create TABLE IF NOT EXISTS player (
    idPlayer INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    address VARCHAR(100) NOT NULL,
    email VARCHAR(50),
    phoneNumber VARCHAR(30),
    weight VARCHAR(10),
    height VARCHAR(39),
    nationality VARCHAR(20),
    personalNotes VARCHAR(200),
    UNIQUE KEY idPlayer_uq (idPlayer)
)  ENGINE=INNODB;

create TABLE IF NOT EXISTS coach (
    idCoach INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    address VARCHAR(100) NOT NULL,
    email VARCHAR(50),
    phoneNumber VARCHAR(30),
    licence VARCHAR (30),
    UNIQUE KEY idCoach_uq (idCoach)
)  ENGINE=INNODB;


create TABLE IF NOT EXISTS team (
    idTeam INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    division VARCHAR(45) NOT NULL,
    stadium VARCHAR(45) NOT NULL,
    UNIQUE KEY idTeam_uq (idTeam)
)  ENGINE=INNODB;

create TABLE IF NOT EXISTS user (
    idUser INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30),
    surname VARCHAR(50) ,
    age INT NOT NULL,
    address VARCHAR(100),
    email VARCHAR(50),
    phoneNumber VARCHAR(30),
    role varchar(10),
    password varchar(100),
    enabled boolean,
    UNIQUE KEY idUser_uq (idUser)
)  ENGINE=INNODB;

create TABLE IF NOT EXISTS training (
    idPlayer INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(45) NOT NULL,
    duration VARCHAR(45),
    exercises VARCHAR(200),
    personal_notes VARCHAR(200),
    FOREIGN KEY (idPlayer)
        REFERENCES player (idPlayer)
        ON delete CASCADE
        ON update CASCADE
)  ENGINE=INNODB;

create TABLE IF NOT EXISTS record (
    idPlayer INT NOT NULL PRIMARY KEY,
    seasons INT,
    matches INT,
    goals INT,
    FOREIGN KEY (idPlayer)
        REFERENCES player (idPlayer)
        ON delete CASCADE
)  ENGINE=INNODB;


create TABLE IF NOT EXISTS tactic (
    idTeam INT PRIMARY KEY,
    formation VARCHAR(45) not null,
    type VARCHAR(45) not null,
    personal_notes VARCHAR(200),
    FOREIGN KEY (idTeam)
        REFERENCES team (idTeam)
        ON delete CASCADE
)  ENGINE=INNODB;

create TABLE IF NOT EXISTS player_compose_team (
    idPlayer INT PRIMARY KEY,
    idTeam INT,
    position VARCHAR(45),
    number INT,
    contract_starts VARCHAR(45) NOT NULL,
    contract_ends VARCHAR(45) NOT NULL,
    FOREIGN KEY (idPlayer)
        REFERENCES player (idPlayer)
        ON delete CASCADE,
    FOREIGN KEY (idTeam)
        REFERENCES team (idTeam)
        ON delete CASCADE
)  ENGINE=INNODB;

create TABLE IF NOT EXISTS coach_manage_team (
    idCoach INT PRIMARY KEY,
    idTeam INT,
    contract_starts VARCHAR(45) NOT NULL,
    contract_ends VARCHAR(45) NOT NULL,
    FOREIGN KEY (idCoach)
        REFERENCES coach (idCoach)
        ON delete CASCADE,
    FOREIGN KEY (idTeam)
        REFERENCES team (idTeam)
        ON delete CASCADE
)  ENGINE=INNODB;

