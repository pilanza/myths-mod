package net.tsuk1.mythsmod.item.client;

import net.tsuk1.mythsmod.item.custom.YankeeCapItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class YankeeCapRenderer extends GeoArmorRenderer<YankeeCapItem> {
    public YankeeCapRenderer() {
        super(new YankeeCapModel());
    }
}
