package space.yurisi.universecore.command;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import space.yurisi.universecore.UniverseCoreAPI;
import space.yurisi.universecore.database.DatabaseManager;
import space.yurisi.universecore.database.models.Position;
import space.yurisi.universecore.database.models.User;
import space.yurisi.universecore.database.models.UserPosition;
import space.yurisi.universecore.exception.PositionNotFoundException;
import space.yurisi.universecore.exception.UserNotFoundException;
import space.yurisi.universecore.exception.UserPositionNotFoundException;

public class pplayerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        DatabaseManager manager = UniverseCoreAPI.getInstance().getDatabaseManager();

        try {
            User user = manager.getUserRepository().getUserFromUUID(player.getUniqueId());
            UserPosition userPosition = manager.getUserPositionRepository().getUserPositionFromUser(user);
            String position = manager.getPositionRepository().getNameFromUserPosition(userPosition);
            player.sendMessage(Component.text(player.getName()+"さんの役職は"+position+"です。"));
        } catch (UserPositionNotFoundException e) {
            player.sendMessage(Component.text(player.getName()+"さんの役職はなしです。"));
        } catch (UserNotFoundException e) {
            player.sendMessage(Component.text("エラーが発生しました ユーザーデータが存在しません。 エラーコード:UP0001"));
        } catch (PositionNotFoundException e) {
            player.sendMessage(Component.text("エラーが発生しました 役職データが存在しません。 エラーコード:UP0002"));
        }
        return true;
    }
}
