package custommob.custommob;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class Events implements Listener {
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event){
        new SpawnEvent().spawnMain(event);
    }

    public void onEntityDeath(EntityDeathEvent event){
        new DeathEvent().deathMain(event);
    }
}
