CREATE DATABASE abs3;

CREATE TABLE account_books(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	date DATE NOT NULL,
	classification VARCHAR(2) NOT NULL,
	note VARCHAR(300),
	price INT NOT NULL
);

INSERT INTO account_books(id, date, classification, note, price)
VALUES('1', '2018-05-30', '支出',  'ティッシュペーパー、歯磨き粉など', '740');

INSERT INTO account_books(id, date, classification, category, note, price)
VALUES('2', '2018-05-30', '支出',  'ランチ', '800');

INSERT INTO account_books(id, date, classification, category, note, price)
VALUES('3', '2018-05-30', '支出',  NULL , '6800');

INSERT INTO account_books(id, date, classification, category, note, price)
VALUES('4', '2018-05-31', '収入',  NULL, '120000');

CREATE TABLE categories(
	category_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	category VARCHAR(100) NOT NULL
);

INSERT INTO categories(category_id, category)
VALUES('0', '選択してください');

INSERT INTO categories(category_id, category)
VALUES('1', '食費');

INSERT INTO categories(category_id, category)
VALUES('2', '交際費');

INSERT INTO categories(category_id, category)
VALUES('3', '日用品');

INSERT INTO categories(category_id, category)
VALUES('4', 'アルバイト代');

INSERT INTO categories(category_id, category)
VALUES('5', 'その他');