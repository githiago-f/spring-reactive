CREATE TABLE IF NOT EXISTS sites
(
    id SERIAL PRIMARY KEY,
    domain VARCHAR(255) NOT NULL,
    alias VARCHAR(255) NOT NULL
);
