CREATE TABLE school(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL
);

CREATE TABLE student(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    school_id INT,
    CONSTRAINT fk_school 
        FOREIGN KEY(school_id)
            REFERENCES school(id)
);

INSERT INTO school (name, city) VALUES ('OSAO', 'Oulu');
INSERT INTO school (name, city) VALUES ('University of Helsinki', 'Helsinki');
INSERT INTO school (name, city) VALUES ('Haaga-Helia', 'Helsinki');
INSERT INTO school (name, city) VALUES ('Savonia', 'Kuopio');

INSERT INTO student (name, school_id) VALUES ('John Deo', 1);
INSERT INTO student (name, school_id) VALUES ('James Bond', 2);
INSERT INTO student (name, school_id) VALUES ('Lisa Simpson', 3);
INSERT INTO student (name, school_id) VALUES ('Sharon Stone', 4);
INSERT INTO student (name, school_id) VALUES ('David Beckham', 3);
INSERT INTO student (name, school_id) VALUES ('Simon Lizotte', 2);
