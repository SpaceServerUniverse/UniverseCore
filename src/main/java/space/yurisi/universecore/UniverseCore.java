package space.yurisi.universecore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import space.yurisi.universecore.command.CommandManager;
import space.yurisi.universecore.database.DatabaseConnector;
import space.yurisi.universecore.event.EventManager;
import space.yurisi.universecore.file.Config;
import space.yurisi.universecore.log.filter.PasswordFilter;
public final class UniverseCore extends JavaPlugin {

    private DatabaseConnector connector;
    private Config config;


    @Override
    public void onEnable() {
        ((Logger)LogManager.getRootLogger()).addFilter(new PasswordFilter());
        this.config = new Config(this);
        this.connector = new DatabaseConnector(
                getPluginConfig().getDBHost(),
                getPluginConfig().getDBPort(),
                getPluginConfig().getDBUserName(),
                getPluginConfig().getDBUserPassword()
        );
        new UniverseCoreAPI(this.connector);
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
