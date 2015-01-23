package com.mattdahepic.itemider.item;

import net.minecraft.item.ItemStack;

public class Damage {
    public static String getDamage (ItemStack item) {
        int numberDamage = item.getItemDamage();
        return Integer.toString(numberDamage);
    }
}
