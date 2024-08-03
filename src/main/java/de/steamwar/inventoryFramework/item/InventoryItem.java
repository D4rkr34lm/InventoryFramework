package de.steamwar.inventoryFramework.item;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import de.steamwar.InventoryRenderable;
import net.kyori.adventure.text.Component;

import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.annotation.CheckForNull;

public abstract class InventoryItem extends InventoryRenderable<InventoryItemConfig> {
    private final ItemStack item;

    @CheckForNull
    private final Consumer<InventoryItemClickEvent> onClick;

    public InventoryItem(@NotNull InventoryItemConfig config,
            Supplier<InventoryItemConfig> renderFunction,
            Consumer<InventoryItemClickEvent> onClick) {

        item = new ItemStack(config.material(), config.amount());
        applyConfig(config);

        this.onClick = onClick;
        this.renderFunction = renderFunction;
    }

    public void rerender() {
        if (renderFunction == null)
            return;
        InventoryItemConfig newConfig = renderFunction.get();

        if (!config.equals(newConfig)) {
            applyConfig(newConfig);
        }
    }

    private void applyConfig(InventoryItemConfig config) {
        ItemMeta meta = item.getItemMeta();
        if (config.isEnchanted()) {
            meta.addEnchant(Enchantment.UNBREAKING, 10, true);
        }

        if (config.labelTranslationKeys() != null) {
            config.labelTranslationKeys().stream().forEach(key -> meta.lore().add(Component.translatable(item)));
        }
        item.setItemMeta(meta);

        this.config = config;
    }

}
