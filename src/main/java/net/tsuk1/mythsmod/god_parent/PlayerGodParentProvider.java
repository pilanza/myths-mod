package net.tsuk1.mythsmod.god_parent;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerGodParentProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerGodParent> PLAYER_GOD_PARENT = CapabilityManager.get(new CapabilityToken<PlayerGodParent>() { });

    private PlayerGodParent godParent = null;
    private final LazyOptional<PlayerGodParent> optional = LazyOptional.of(this::createPlayerGodParent);

    private <T> PlayerGodParent createPlayerGodParent() {
        if(this.godParent == null) {
            this.godParent = new PlayerGodParent();
        }

        return this.godParent;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_GOD_PARENT) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerGodParent().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerGodParent().loadNBTData(nbt);
    }


}
