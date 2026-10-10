package com.gumillea.matchaflavoured.mixin;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CampfireBlock.class)
public class CampfireBlockMixin {

    @Inject(method = "getStateForPlacement", at = @At("RETURN"), cancellable = true)
    private void matchaflavoured$getStateForPlacement(BlockPlaceContext p_51240_, CallbackInfoReturnable<BlockState> cir) {
        BlockState state = cir.getReturnValue();
        if (state.is(Blocks.CAMPFIRE) && state.hasProperty(CampfireBlock.LIT)) {
            cir.setReturnValue(state.setValue(CampfireBlock.LIT, false));
        }
    }

}