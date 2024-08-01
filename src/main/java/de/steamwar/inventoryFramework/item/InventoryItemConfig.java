package de.steamwar.inventoryFramework.item;

import java.util.List;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public record InventoryItemConfig(@NotNull Material material, int amount, boolean isEnchanted,
        List<String> labelsTranslationKeys) {
}
