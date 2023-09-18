package space.yurisi.universecore.database.tables;


import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class UserTable extends BaseTable {

    public UserTable() {
        connection = getConnection();
    }

    public void createTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS " +
                    "users(" +
                    "id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "uuid VARCHAR(255) NOT NULL UNIQUE," +
                    "password VARCHAR(255)," +
                    "remember_token VARCHAR(255)," +
                    "created_at DATETIME NOT NULL," +
                    "updated_at DATETIME NOT NULL" +
                    ")";
            execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean create(Player player) {
        try {
            String now = getNowTime();
            String sql = "INSERT INTO `users`(`name`,`uuid`,`created_at`,`updated_at`) VALUES (" +
                    getInsertStringFormat(player.getName(), false) +
                    getInsertStringFormat(player.getUniqueId().toString(), false) +
                    getInsertStringFormat(now, false) +
                    getInsertStringFormat(now, true) +
                    ")";
            return execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean exists(Player player) {
        try {
            String sql = "SELECT COUNT(uuid) FROM users WHERE uuid = '" + player.getUniqueId() + "'";
            ResultSet result = executeQuery(sql);
            while (result.next()) {
                return result.getInt("COUNT(uuid)") > 0;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public HashMap<String, String> get(Player player){
        try {
            String sql = "SELECT * FROM users WHERE uuid = '" + player.getUniqueId() + "'";
            ResultSet resultSet = executeQuery(sql);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
