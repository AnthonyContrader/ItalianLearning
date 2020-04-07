USE sampledb;

/* Elimino tabelle database se esistono */
DROP TABLE IF EXISTS  statistic;

DROP TABLE IF EXISTS hangman;
DROP TABLE IF EXISTS findMistake;
DROP TABLE IF EXISTS quiz;
DROP TABLE IF EXISTS guessPicture;
DROP TABLE IF EXISTS organizeSentence;
DROP TABLE IF EXISTS findAWord;

DROP TABLE IF EXISTS category;

CREATE TABLE category (
	id int NOT NULL AUTO_INCREMENT,
    title varchar(32) NOT NULL,
    description varchar(512) DEFAULT NULL,
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE hangman (
	id int NOT NULL AUTO_INCREMENT,
	solution varchar(32) NOT NULL,
    definition varchar(255) NOT NULL, 	/* descrizione della parola */
    sentence varchar(255) NOT NULL,		/* indizio */
    score int NOT NULL,
    idCategory int NOT NULL,
	FOREIGN KEY (idCategory) REFERENCES category (id),
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE findMistake (
	id int NOT NULL AUTO_INCREMENT,
	solution varchar(32) NOT NULL,
    definition varchar(255) NOT NULL, 	/* descrizione della parola */
    sentence varchar(255) NOT NULL,		/* frase iniziale */
    optionA varchar(50) NOT NULL,
	optionB varchar(50) NOT NULL,
    optionC varchar(50) NOT NULL,
    score int NOT NULL,
    idCategory int NOT NULL,
	FOREIGN KEY (idCategory) REFERENCES category (id),
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE quiz (
	id int NOT NULL AUTO_INCREMENT,
	solution varchar(32) NOT NULL,
    definition varchar(255) NOT NULL, 	/* spiegazione della soluzione  */
    sentence varchar(255) NOT NULL,		/* domanda */
    score int NOT NULL,
    idCategory int NOT NULL,
	FOREIGN KEY (idCategory) REFERENCES category (id),
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE guessPicture (
	id int NOT NULL AUTO_INCREMENT,
	solution varchar(32) NOT NULL,
    image text(65535) NOT NULL,
    score int NOT NULL,
    idCategory int NOT NULL,
	FOREIGN KEY (idCategory) REFERENCES category (id),
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE organizeSentence (
	id int NOT NULL AUTO_INCREMENT,
	solution varchar(32) NOT NULL,
	definition varchar(255) NOT NULL, 	/* spiegazione della soluzione  */
    sentence varchar(255) NOT NULL,		/* frase disordinata */
    score int NOT NULL,
    idCategory int NOT NULL,
	FOREIGN KEY (idCategory) REFERENCES category (id),
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE findAWord (
	id int NOT NULL AUTO_INCREMENT,
	solution varchar(32) NOT NULL,
	definition varchar(255) NOT NULL, 	/* spiegazione della soluzione  */
    sentence varchar(255) NOT NULL,		/* frase disordinata */
    score int NOT NULL,
    idCategory int NOT NULL,
	FOREIGN KEY (idCategory) REFERENCES category (id),
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE statistic (
	id int NOT NULL AUTO_INCREMENT,
    idUser int NOT NULL,
	idGame int NOT NULL,
    typeGame varchar(32) NOT NULL,
	score int NOT NULL,
	FOREIGN KEY (idUser) REFERENCES user (id),    
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
