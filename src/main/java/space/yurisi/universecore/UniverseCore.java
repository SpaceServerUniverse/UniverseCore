package space.yurisi.universecore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import space.yurisi.universecore.command.CommandManager;
import space.yurisi.universecore.database.DatabaseConnector;

import space.yurisi.universecore.event.EventManager;
import space.yurisi.universecore.file.Config;

public final class UniverseCore extends JavaPlugin {

    private DatabaseConnector connector;
    private Config config;

    private UniverseCoreAPI api;

    @Override
    public void onEnable() {
        this.config = new Config(this);
        this.connector = new DatabaseConnector(
                getPluginConfig().getDBHost(),
                getPluginConfig().getDBPort(),
                getPluginConfig().getDBUserName(),
                getPluginConfig().getDBUserPassword()
        );
        this.api = new UniverseCoreAPI(this.connector);
        new EventManager(this);
        new CommandManager(this);
    }

    @Override
    public void onDisable() {
        connector.close();
    }

    public Config getPluginConfig() {
        return this.config;
    }
}
