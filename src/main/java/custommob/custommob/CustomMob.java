package custommob.custommob;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomMob extends JavaPlugin {

    public static FileConfiguration FC;

    public void load(){
        new SettingLoad().loadMain(getConfig());
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.load();
        getServer().getPluginManager().registerEvents(new Events(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
