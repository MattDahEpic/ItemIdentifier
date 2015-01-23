package com.mattdahepic.itemider.command;

import com.mattdahepic.itemider.item.Damage;
import com.mattdahepic.itemider.item.NBT;
import com.mattdahepic.itemider.item.Name;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class CommandConfig implements ICommand {
    private List aliases, tabCompleteOptions;
    public CommandConfig () {
        this.aliases = new ArrayList();
        this.aliases.add("itemider");
        this.tabCompleteOptions = new ArrayList();
        this.tabCompleteOptions.add("name");
        this.tabCompleteOptions.add("nbt");
        this.tabCompleteOptions.add("help");
    }
    @Override
    public int compareTo (Object arg0) {
        return 0;
    }
    @Override
    public String getCommandName () {
        return "itemider";
    }
    @Override
    public String getCommandUsage (ICommandSender iCommandSender) {
        return EnumChatFormatting.RED + "/itemider <command>";
    }
    @Override
    public List getCommandAliases () {
        return this.aliases;
    }
    @Override
    public void processCommand (ICommandSender iCommandSender, String[] inputString) {
        ChatComponentText returnText = new ChatComponentText("");
        if (inputString.length == 0) { //no input command
            iCommandSender.addChatMessage(new ChatComponentText("§cUse \"§6/itemider help§c\" for useage help.§r"));
            return;
        } else { //message contains data
            ItemStack item = null;
            if (iCommandSender instanceof EntityPlayer) {
                item = ((EntityPlayer) iCommandSender).getHeldItem();
            }
            if (inputString[0].equalsIgnoreCase("name")) {
                if (item != null) {
                    iCommandSender.addChatMessage(new ChatComponentText("The held item\'s name is: \"§d" + Name.getName(item) + "§r\" with a damage value of §d" + Damage.getDamage(item) + "§r."));
                    return;
                } else {
                    iCommandSender.addChatMessage(new ChatComponentText("§cYou\'re not holding an item!§r"));
                    return;
                }
            } else if (inputString[0].equalsIgnoreCase("nbt")) {
                if (item != null) {
                    iCommandSender.addChatMessage(new ChatComponentText("The held item\'s name is: \"§d" + Name.getName(item) + "§r\" with a damage value of §d" + Damage.getDamage(item) + "§r."));
                    iCommandSender.addChatMessage(new ChatComponentText("The NBT data of the held item is as follows:§r"));
                    iCommandSender.addChatMessage(new ChatComponentText("§d" + NBT.getNBT(item)));
                    return;
                }
            } else if (inputString[0].equalsIgnoreCase("help")) {
                iCommandSender.addChatMessage(new ChatComponentText("§aTo get the item name of the currently held item, use \"§6/itemider name§a\" while holding an item.§r"));
                iCommandSender.addChatMessage(new ChatComponentText("§aTo get the NBT data of the currently held item, use \"§6/itemider nbt§a\" while holding an item.§"));
                return;
            }
        }
    }
    @Override
    public boolean canCommandSenderUseCommand (ICommandSender iCommandSender) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if (iCommandSender instanceof EntityPlayer) {
                return iCommandSender.canCommandSenderUseCommand(0,this.getCommandName());
            } else {
                return true;
            }
        }
        return false;
    }
    @Override
    public List addTabCompletionOptions (ICommandSender iCommandSender, String[] inputString) {
        return this.tabCompleteOptions;
    }
    @Override
    public boolean isUsernameIndex (String[] inputString, int i) {
        return false;
    }
}
