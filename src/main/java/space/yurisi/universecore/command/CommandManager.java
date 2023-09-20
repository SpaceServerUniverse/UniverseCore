package space.yurisi.universecore.command;

import org.bukkit.Bukkit;
import space.yurisi.universecore.UniverseCore;

public class CommandManager {

    private final UniverseCore main;

    public CommandManager(UniverseCore main){
        this.main = main;
        init();
    }

    private void init(){
        main.getCommand("password").setExecutor(new passwordCommand());
    }
}
