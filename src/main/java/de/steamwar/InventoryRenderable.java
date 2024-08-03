package de.steamwar;

import java.util.function.Supplier;

public abstract class InventoryRenderable<Config extends Object> {
  protected Config config;
  protected Supplier<Config> renderFunction;

  public void rerender() {
    Config conf = renderFunction.get();

    if (conf.equals(config)) {
      return;
    } else {
      applyConfig();
    }
  }

  protected abstract void applyConfig();
}
