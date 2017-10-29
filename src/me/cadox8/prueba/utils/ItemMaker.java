package me.cadox8.prueba.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemMaker {

    private ItemStack item;

    public ItemMaker(Material mat) {
        item = new ItemStack(mat);
    }

    public ItemMaker(Material mat, int data) {
        item = new ItemStack(mat, 1, (short)data);
    }

    public ItemMaker setName(String name) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(Utils.colorize(name));
        item.setItemMeta(im);
        return this;
    }

    public ItemMaker setLore(String... lore) {
        ItemMeta im = item.getItemMeta();
        List<String> ll = new ArrayList<>();

        for (String s : lore) ll.add(Utils.colorize(s));
        im.setLore(ll);
        item.setItemMeta(im);
        return this;
    }

    public ItemMaker addEnchantment(Enchantment e, int level) {
        item.addUnsafeEnchantment(e, level);
        return this;
    }

    public ItemMaker setItemFlags() {
        ItemMeta im = item.getItemMeta();
        im.addItemFlags(ItemFlag.values());
        return this;
    }

    public ItemStack build() {
        return item;
    }
}
