CREATE TABLE usuario (
id SERIAL PRIMARY KEY,
username VARCHAR(100) NOT NULL UNIQUE,
password VARCHAR(100) NOT NULL,
role VARCHAR(50) NOT NULL 
);

INSERT INTO usuario(id, username, password, role) VALUES
(default, 'nathanfaria', 'mysecret', 'ADMIN');