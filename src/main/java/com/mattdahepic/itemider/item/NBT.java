package com.mattdahepic.itemider.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBT {
    public static String getNBT (ItemStack item) {
        NBTTagCompound nbt = item.stackTagCompound;
        if (nbt != null) {
            return item.stackTagCompound.toString();
        } else {
            return "null";
        }
    }
}
