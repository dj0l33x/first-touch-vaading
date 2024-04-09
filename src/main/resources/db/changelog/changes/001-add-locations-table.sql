CREATE TABLE IF NOT EXISTS locations
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    path VARCHAR(255) NOT NULL,
    is_active  BOOLEAN      NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL
);
