ALTER TABLE categories ADD COLUMN points INTEGER DEFAULT 10;

INSERT INTO categories (name, parent_id, created_at) VALUES
    ('Все категории', currval('categories_category_id_seq'::regclass), CURRENT_TIMESTAMP);