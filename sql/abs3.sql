CREATE DATABASE abs3;

CREATE TABLE account_books(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	date DATE NOT NULL,
	class VARCHAR(2) NOT NULL,
	category VARCHAR(100) NOT NULL,
	note VARCHAR(300),
	price INT NOT NULL
);

INSERT INTO account_books(id, date, class, category, note, price)
VALUES('1', '2018-05-30', '支出', '日用品', 'ティッシュペーパー、歯磨き粉など', '740');

INSERT INTO account_books(id, date, class, category, note, price)
VALUES('2', '2018-05-30', '支出', '食費', 'ランチ', '800');

INSERT INTO account_books(id, date, class, category, note, price)
VALUES('3', '2018-05-30', '支出', '交際費', NULL , '6800');

INSERT INTO account_books(id, date, class, category, note, price)
VALUES('4', '2018-05-31', '収入', 'アルバイト代', NULL, '120000');
