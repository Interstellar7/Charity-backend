/*  Удаляем лишний столбец id и делаем уникальность по 2-м полям  */
ALTER TABLE participants DROP COLUMN participants_id;
ALTER TABLE participants ADD CONSTRAINT participants_pkey PRIMARY KEY (auction_id, user_id);

/* Т.к. отношение к participants - один ко иногим, поле participants_id не нужно. Зато нужно поле winner_id */
ALTER TABLE auctions RENAME participants_id TO winner_id;
ALTER TABLE auctions ALTER COLUMN winner_id DROP NOT NULL;
ALTER TABLE auctions DROP COLUMN "last_status_id";
ALTER TABLE auctions ADD COLUMN is_alive boolean NOT NULL;
ALTER TABLE auctions RENAME created_at TO opened_at;
ALTER TABLE auctions RENAME updated_at TO closed_at;
