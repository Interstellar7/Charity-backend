CREATE TABLE categories
(
    category_id SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    parent_id INTEGER NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    created_by INTEGER NULL,
    updated_by INTEGER NULL,
    CONSTRAINT fk__categories__categories FOREIGN KEY (parent_id) REFERENCES categories (category_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ad_statuses
(
    status_id INTEGER PRIMARY KEY,
    name VARCHAR(32) NOT NULL
);

CREATE TABLE properties
(
    property_id SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL
);

CREATE TABLE ads
(
    ad_id SERIAL PRIMARY KEY,
    title VARCHAR(256) NOT NULL,
    description VARCHAR NULL,
    category_id INTEGER NOT NULL,
    ad_status_id INTEGER NOT NULL,
    expired_at TIMESTAMP NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    created_by INTEGER NULL,
    updated_by INTEGER NULL,
    CONSTRAINT fk__ads__categories FOREIGN KEY (category_id) REFERENCES categories (category_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk__ads__ad_statuses FOREIGN KEY (ad_status_id) REFERENCES ad_statuses (status_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ad_properties
(
    ad_id INTEGER NOT NULL,
    property_id INTEGER NOT NULL,
    value VARCHAR,
    CONSTRAINT fk__ad_properties__ads FOREIGN KEY (ad_id) REFERENCES ads (ad_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk__ad_properties__properties FOREIGN KEY (property_id) REFERENCES properties (property_id) ON UPDATE CASCADE ON DELETE CASCADE
);