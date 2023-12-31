use SpaceServerUniverse;

CREATE TABLE IF NOT EXISTS player_levels(
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL UNIQUE,
    total_exp BIGINT UNSIGNED NOT NULL DEFAULT 0,
    level_mode INTEGER UNSIGNED NOT NULL DEFAULT 0,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS player_normal_levels(
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL UNIQUE,
    exp BIGINT UNSIGNED NOT NULL DEFAULT 0,
    level INTEGER UNSIGNED NOT NULL DEFAULT 1,
    level_mode_total_exp INTEGER NOT NULL DEFAULT 0,
    exp_for_next_level INTEGER NOT NULL DEFAULT 0,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
);