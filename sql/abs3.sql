CREATE DATABASE abs3;

CREATE TABLE account_books(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	date DATE NOT NULL,
	classification INT,
	note VARCHAR(300),
	price INT NOT NULL,
	category_id INT NOT NULL
);

INSERT INTO account_books(id, date, classification, note, price, category_id)
VALUES('1', '2018-05-30', '1',  'ティッシュペーパー、歯磨き粉など', '740', '4');

INSERT INTO account_books(id, date, classification, note, price, category_id)
VALUES('2', '2018-05-30', '1',  'ランチ', '800', '2');

INSERT INTO account_books(id, date, classification, note, price, category_id)
VALUES('3', '2018-05-30', '1',  NULL , '6800', '3');

INSERT INTO account_books(id, date, classification, note, price, category_id)
VALUES('4', '2018-05-31', '2',  NULL, '120000', '5');

CREATE TABLE categories(
	category_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	category INT NOT NULL
);

INSERT INTO categories(category_id, category)
VALUES('1', '1');

INSERT INTO categories(category_id, category)
VALUES('2', '2');

INSERT INTO categories(category_id, category)
VALUES('3', '3');

INSERT INTO categories(category_id, category)
VALUES('4', '4');

INSERT INTO categories(category_id, category)
VALUES('5', '5');

INSERT INTO categories(category_id, category)
VALUES('6', '6');