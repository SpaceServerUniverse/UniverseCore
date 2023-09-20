package space.yurisi.universecore.command;

import at.favre.lib.crypto.bcrypt.BCrypt;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import space.yurisi.universecore.UniverseCoreAPI;
import space.yurisi.universecore.database.models.User;
import space.yurisi.universecore.database.repositories.UserRepository;

import java.util.regex.Pattern;

public class passwordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)){
            return false;
        }

        Player player = (Player) commandSender;

        if (strings.length == 0){
            commandSender.sendMessage(Component.text("Usage: /password <password 大文字小文字数字含む8~36文字>"));
            return false;
        }

        UserRepository userRepo = UniverseCoreAPI.api().getDatabaseManager().getUserRepository();

        if(!userRepo.existsUserFromUUID(player.getUniqueId())){
            userRepo.createUser(player);
        }

        User user = userRepo.getUserFromUUID(player.getUniqueId());

        //大文字小文字数字含む8~32文字
        //記号も可
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,32}$";
        Pattern pattern = Pattern.compile(regex);
        String password = strings[0];

        if(!pattern.matcher(password).matches()){
            commandSender.sendMessage(Component.text("パスワードが条件を満たしていません: 大文字小文字数字含む8~36文字"));
            return false;
        }

        String hashed_password = BCrypt.withDefaults().hashToString(8, password.toCharArray());

        user.setPassword(hashed_password);

        userRepo.updateUser(user);
        commandSender.sendMessage(Component.text("パスワードを設定しました。(webからログインできるようになります)"));
        return true;
    }
}
