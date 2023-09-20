use SpaceServerUniverse;

CREATE TABLE IF NOT EXISTS money(
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL UNIQUE,
    money BIGINT NOT NULL DEFAULT 1000,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
);