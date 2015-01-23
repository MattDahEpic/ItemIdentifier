package com.mattdahepic.itemider;

import com.mattdahepic.itemider.command.CommandConfig;
import com.mattdahepic.itemider.config.Config;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = ItemIdentifier.MODID, name = ItemIdentifier.NAME, version = ItemIdentifier.VERSION)
public class ItemIdentifier {
    @Mod.Instance("ItemIdentifier")
    public static ItemIdentifier instance;

    public static final String MODID = "ItemIdentifier";
    public static final String NAME = "Item Identifier";
    public static final String VERSION = "0.5";

    @SidedProxy(clientSide = "com.mattdahepic.itemider.ClientProxy",serverSide = "com.mattdahepic.itemider.CommonProxy")
    public static CommonProxy proxy;

    public static Configuration configFile;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(instance);
        Config.load(event);
    }

    @Mod.EventHandler
    public void init (FMLInitializationEvent event) {}

    @Mod.EventHandler
    public void postInit (FMLPostInitializationEvent event) {}

    @Mod.EventHandler
    public void serverStarting (FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandConfig());
    }
}
