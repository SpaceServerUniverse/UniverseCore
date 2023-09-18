package space.yurisi.universecore.event.player;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import space.yurisi.universecore.database.tables.UserTable;

public class LoginEvent implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent event){
        Player player = event.getPlayer();
        UserTable userTable = new UserTable();
        if(userTable.exists(player)){
        }
        if(!new UserTable().create(player)){
            player.kick(Component.text("ユーザーデータが作成できませんでした。\nDiscordかTwitterでオーナーに報告してください。"));
        }
    }
}
