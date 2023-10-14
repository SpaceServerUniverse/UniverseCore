use SpaceServerUniverse;

CREATE TABLE IF NOT EXISTS lands (
      id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
      uuid VARCHAR(255) NOT NULL,
      start_x INT NOT NULL,
      start_z INT NOT NULL,
      end_x INT NOT NULL,
      end_z INT NOT NULL,
      world_name VARCHAR(255) NOT NULL,
      created_at DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS land_permissions (
     id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
     land_id BIGINT UNSIGNED NOT NULL,
     uuid VARCHAR(255) NOT NULL,
     created_at DATETIME NOT NULL,
     FOREIGN KEY (land_id) REFERENCES lands(id) ON DELETE CASCADE
)