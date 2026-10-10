package com.gumillea.matchaflavoured.mixin;

import com.gumillea.inlandport.core.api.record.IPEntityTagPacket;
import com.gumillea.inlandport.core.util.utils.RegUtil;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.common.item.SecretRecipeItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingMenu.class)
public abstract class CraftingMenuMixin {

    @Inject(method = "slotChangedCraftingGrid", at = @At("RETURN"))
    private static void matchaFlavoured$checkSecretRecipe(AbstractContainerMenu menu, Level level, Player player, CraftingContainer craftSlots, ResultContainer resultSlots, RecipeHolder<CraftingRecipe> recipe, CallbackInfo ci) {
        RecipeHolder<?> used = resultSlots.getRecipeUsed();

        if (used == null) return;
        if (!used.value().getGroup().equals(SecretRecipeItem.GROUP)) return;

        ItemStack result = resultSlots.getItem(0);
        if (result.isEmpty()) return;

        if (!SecretRecipeItem.hasTag(player, result.getItem())) {
            resultSlots.setItem(0, ItemStack.EMPTY);
        }
    }
}