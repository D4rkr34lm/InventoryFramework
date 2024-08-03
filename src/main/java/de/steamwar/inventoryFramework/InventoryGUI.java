package de.steamwar.inventoryFramework;

import java.util.function.Consumer;

import org.bukkit.inventory.Inventory;

public abstract class InventoryGUI implements Inventory {

  private Consumer<Inventory> renderFunction;

}
