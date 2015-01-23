package com.mattdahepic.itemider.config;

import com.mattdahepic.itemider.ItemIdentifier;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class Config {
    public static boolean enableTooltips = true;
    public static Configuration config;

    public static void load (FMLPreInitializationEvent event) {
        ItemIdentifier.configFile = new Configuration(event.getSuggestedConfigurationFile());
        syncConfig();
    }
    public static void syncConfig () {
        try {
            Config.processConfig(ItemIdentifier.configFile);
        } catch (Exception e) {
            //TODO: log
        } finally {
            if (ItemIdentifier.configFile.hasChanged()) {
                //TODO: log
                ItemIdentifier.configFile.save();
            }
        }
    }
    @SubscribeEvent
    public void onConfigChanged (ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(ItemIdentifier.MODID)) {
            //TODO: log
            syncConfig();
        }
    }
    public static void processConfig (Configuration config) {
        enableTooltips = config.getBoolean("enableTooltips",Configuration.CATEGORY_GENERAL,enableTooltips,"Enable item name tooltips?");
    }
}
