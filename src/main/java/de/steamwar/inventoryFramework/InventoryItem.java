package de.steamwar.inventoryFramework;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;


public abstract class InventoryItem {
    private final ItemStack item;

    @NotNull
    private final InventoryItemConfig config;

    private final Consumer<InventoryItem> onClick;

    private final Supplier<InventoryItemConfig> renderFunction;

    public InventoryItem(@NotNull InventoryItemConfig config, Consumer<InventoryItem> onClick) {
        this.config = config;
        this.onClick = onClick;
        renderFunction = null;
    }

    public InventoryItem(@NotNull Supplier<InventoryItemConfig> renderFunction, Consumer<InventoryItem> onClick) {
        config = renderFunction.get();
        this.onClick = onClick;
        this.renderFunction = renderFunction;
    }

    public void rerender() {

    }


}
