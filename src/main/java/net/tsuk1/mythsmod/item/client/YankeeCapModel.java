package net.tsuk1.mythsmod.item.client;

import net.minecraft.resources.ResourceLocation;
import net.tsuk1.mythsmod.MythsMod;
import net.tsuk1.mythsmod.item.custom.YankeeCapItem;
import software.bernie.geckolib.model.GeoModel;

public class YankeeCapModel extends GeoModel<YankeeCapItem> {
    @Override
    public ResourceLocation getModelResource(YankeeCapItem animatable) {
        return new ResourceLocation(MythsMod.MOD_ID, "geo/yankee_cap.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(YankeeCapItem animatable) {
        return new ResourceLocation(MythsMod.MOD_ID, "textures/mythical_item/yankee_cap.png");
    }

    @Override
    public ResourceLocation getAnimationResource(YankeeCapItem animatable) {
        return new ResourceLocation(MythsMod.MOD_ID, "animations/yankee_cap.animation.json");
    }
}
