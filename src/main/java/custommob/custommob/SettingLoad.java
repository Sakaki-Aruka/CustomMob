package custommob.custommob;


import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.*;

import static custommob.custommob.CustomMob.FC;

public class SettingLoad {

    /* variables

    ArrayList<ItemStack> drops -> drop items list
    -> 0 : ItemStack : drop items (get from config.yml)

    HashMap<UUID,ItemStack> enemiesAndDrop -> Drop items that are related with an enemy(UUID).
    -> 0: UUID : enemies UUID.
    -> 1: ItemStack : drop item(itemStack) from arrayList (target arrayList is "drops")

    HashMap<EntityType,ArrayList<Object>> replaceChance -> mob's type and drop item(from ArrayList->drops)
   -> 0: EntityType : enemies type
   -> 1:
   --> 1-even : Integer : ArrayList's index (target arraylist is "drops".)
   --> 1-odd : Double : Drop chance
     */


    public static ArrayList<ItemStack> drops = new ArrayList<>();
    public static Map<UUID,ItemStack> enemiesAndDrop = new HashMap<>();
    public static Map<EntityType,ArrayList<Object>> replaceChance = new HashMap<>();

    public void loadMain(FileConfiguration fileConfiguration){

        FC = fileConfiguration;
        int count = 0;

        // collect items from config.yml
        while(true){
            String path = "drop.item"+count+".";

            if(!(FC.contains("drop.item"+count))){
                //doesn't find next settings
                break;
            }

            try{

                ItemStack itemStack = new ItemStack(Material.getMaterial(FC.getString(path+"material").toUpperCase(Locale.ROOT)));
                itemStack.setAmount(FC.getInt(path+"amount"));

                //set name
                if(FC.contains(path+"name")){
                    itemStack.getItemMeta().setDisplayName(FC.getString(path+"name"));
                }

                //set lore
                if(FC.contains(path+"lore")){
                    itemStack.getItemMeta().setLore(Arrays.asList(FC.getString(path+"lore").split("!&!")));
                }

                //set enchantments
                if(FC.contains(path+"enchant")){
                    for(String loop : FC.getString(path+"enchant").split(",")){
                        Enchantment enchant = Enchantment.getByName(FC.getString(Arrays.asList(loop.split(";")).get(0).toUpperCase(Locale.ROOT)));
                        int level = Integer.valueOf(Arrays.asList(loop.split(";")).get(1));

                        itemStack.getItemMeta().addEnchant(enchant,level,true);
                    }
                }

                //set unbreakable
                if(FC.contains(path+"unbreak")){
                    itemStack.getItemMeta().setUnbreakable(true);
                }

                //set ItemFlags
                if(FC.contains(path+"flags")){
                    for(String loop : FC.getString(path+"flags").toUpperCase(Locale.ROOT).split(",")){
                        itemStack.getItemMeta().addItemFlags(ItemFlag.valueOf(loop));
                    }
                }

                drops.add(itemStack);

            }catch (Exception e){
                e.printStackTrace();
            }

            count++;
        }

        count = 0;
        while(true){
            // collect drop chance
            String path = "mob.table"+count+".";

            while(true){

                if(!(FC.contains("mob.table"+count))){
                    break;
                }

                try{

                    String type = FC.getString(path+"type");
                    EntityType eType = EntityType.valueOf(type.toUpperCase(Locale.ROOT));

                    ArrayList<Object> temporary = new ArrayList<>();
                    String drop = FC.getString(path+"drop");
                    for(String loop : drop.split(",")){
                        int index = Integer.valueOf(Arrays.asList(loop.split(";")).get(0));
                        double chance = Double.valueOf(Arrays.asList(loop.split(";")).get(1));

                        temporary.add(index);
                        temporary.add(chance);
                    }

                    replaceChance.put(eType,temporary);


                }catch (Exception e){
                    e.printStackTrace();
                }
                count++;
            }

        }
    }
}
