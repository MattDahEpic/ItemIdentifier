package com.mattdahepic.itemider.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Name {
    public static String getName (ItemStack item) {
        String name = Item.itemRegistry.getNameForObject(item);
        return name;
    }
}
