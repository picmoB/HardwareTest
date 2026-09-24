CREATE TABLE Hardware (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    price int CHECK ( int > 0 ) NOT NULL,
    code int NOT NULL
)
