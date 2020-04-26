CREATE TABLE auctions (
    auction_id SERIAL PRIMARY KEY,
    ad_id INTEGER NOT NULL,
    participants_id INTEGER NOT NULL,
    last_status_id INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE participants (
    participants_id SERIAL PRIMARY KEY,
    auction_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    message_to_giver VARCHAR(255),
    submit_at TIMESTAMP NOT NULL
);

CREATE TABLE action_history (
    action_id SERIAL PRIMARY KEY,
    num_of_action_in_auction INTEGER NOT NULL,
    new_status_id INTEGER NOT NULL,
    changed_at TIMESTAMP NOT NULL
);