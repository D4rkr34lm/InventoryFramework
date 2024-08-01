package de.steamwar.inventoryFramework.item;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;

import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.CheckForNull;

public abstract class InventoryItem {
    private final ItemStack item;

    @NotNull
    private InventoryItemConfig config;

    @NotNull
    private final Consumer<InventoryItem> onClick;

    @CheckForNull
    private final Function<InventoryItemConfig, InventoryItemConfig> renderFunction;

    public InventoryItem(@NotNull InventoryItemConfig config, Consumer<InventoryItem> onClick) {
        this.onClick = onClick;
        renderFunction = null;

        item = new ItemStack(config.material(), config.amount());
        applyConfig(config);
    }

    public InventoryItem(@NotNull InventoryItemConfig config,
            @NotNull Function<InventoryItemConfig, InventoryItemConfig> renderFunction,
            Consumer<InventoryItem> onClick) {

        this.onClick = onClick;
        this.renderFunction = renderFunction;

        item = new ItemStack(config.material(), config.amount());
        applyConfig(config);
    }

    public void rerender() {
        if (renderFunction == null)
            return;
        InventoryItemConfig newConfig = renderFunction.apply(config);

        if (!config.equals(newConfig)) {
            applyConfig(newConfig);
        }
    }

    private void applyConfig(InventoryItemConfig config) {
        ItemMeta meta = item.getItemMeta();
        if (config.isEnchanted()) {
            meta.addEnchant(Enchantment.UNBREAKING, 10, true);
        }
        config.labelsTranslationKeys().stream().forEach(key -> meta.lore().add(Component.translatable(item)));
        item.setItemMeta(meta);

        this.config = config;
    }

}
