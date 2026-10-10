package com.gumillea.matchaflavoured.common.client;

import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.core.reg.MFItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.*;

public class WardingShieldRender extends BlockEntityWithoutLevelRenderer {

    private final ShieldModel model;
    public static final Material TEXTURE = new Material(TextureAtlas.LOCATION_BLOCKS, MatchaFlavoured.loc("entity/warding_shield"));

    public WardingShieldRender(BlockEntityRenderDispatcher dispatcher, EntityModelSet set) {
        super(dispatcher, set);
        this.model = new ShieldModel(set.bakeLayer(ModelLayers.SHIELD));
    }

    public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (stack.is(MFItems.WARDING_SHIELD.get())) {
            poseStack.pushPose();
            poseStack.scale(1.0F, -1.0F, -1.0F);
            Material material = TEXTURE;
            VertexConsumer vertexconsumer = material.sprite().wrap(ItemRenderer.getFoilBufferDirect(buffer, model.renderType(material.atlasLocation()), true, stack.hasFoil()));
            model.handle().render(poseStack, vertexconsumer, packedLight, packedOverlay);
            model.plate().render(poseStack, vertexconsumer, packedLight, packedOverlay);
            poseStack.popPose();
        }
    }
}
