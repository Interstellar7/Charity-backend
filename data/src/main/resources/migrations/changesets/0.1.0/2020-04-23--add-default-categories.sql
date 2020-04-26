INSERT INTO categories (name, parent_id, created_at) VALUES
    ('Одежда', 1, CURRENT_TIMESTAMP),
	('Мебель', 1, CURRENT_TIMESTAMP),
	('Мотоэкипировка', 1, CURRENT_TIMESTAMP);

INSERT INTO categories (name, parent_id, created_at) VALUES
	('Куртки', 2, CURRENT_TIMESTAMP),
	('Шляпки', 2, CURRENT_TIMESTAMP),
	('Столы', 3, CURRENT_TIMESTAMP),
	('Стулья', 3, CURRENT_TIMESTAMP),
	('Шлемы', 4, CURRENT_TIMESTAMP),
	('Куртки', 4, CURRENT_TIMESTAMP),
	('Перчатки', 4, CURRENT_TIMESTAMP);

INSERT INTO categories (name, parent_id, created_at) VALUES
	('Кожаные', 10, CURRENT_TIMESTAMP),
	('Текстильные', 10, CURRENT_TIMESTAMP);