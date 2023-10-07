use SpaceServerUniverse;

CREATE TABLE IF NOT EXISTS mywarp(
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    name VARCHAR(20) NOT NULL,
    x BIGINT NOT NULL,
    y BIGINT NOT NULL,
    z BIGINT NOT NULL,
    world_name VARCHAR(255) NOT NULL,
    is_private BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
);