package de.steamwar.inventoryFramework;

import org.bukkit.Material;

import javax.annotation.Nullable;

public record InventoryItemConfig(Material material, int amount, boolean isEnchanted, String[] labels) {
}
