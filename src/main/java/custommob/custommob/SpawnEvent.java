package custommob.custommob;

import org.bukkit.Bukkit;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Arrays;

import static custommob.custommob.SettingLoad.drops;
import static custommob.custommob.SettingLoad.replaceChance;

public class SpawnEvent {
    public void spawnMain(CreatureSpawnEvent event){
        if(replaceChance.containsKey(event.getEntityType())){
            //contains

            //debug
            Bukkit.broadcastMessage("UUID:"+event.getEntity().getUniqueId());
            Bukkit.broadcastMessage("chance and drop:"+replaceChance.get(event.getEntityType()));
            Bukkit.broadcastMessage(drops.get((int)replaceChance.get(event.getEntityType()).get(0)).toString());

        }
    }
}
