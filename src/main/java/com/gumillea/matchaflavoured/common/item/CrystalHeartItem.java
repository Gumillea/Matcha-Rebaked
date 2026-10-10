package com.gumillea.matchaflavoured.common.item;

import com.gumillea.inlandport.core.util.utils.AttrUtil;
import com.gumillea.inlandport.core.util.utils.EffectUtil;
import com.gumillea.inlandport.core.util.utils.EntityUtil;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.MatchaFlavouredConfig;
import com.gumillea.matchaflavoured.core.reg.MFEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class CrystalHeartItem extends Item {

    public static final ResourceLocation CRYSTAL_HEART_BOOST = MatchaFlavoured.loc("crystal_heart_boost");

    public CrystalHeartItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if (entity instanceof ServerPlayer player) {
            Holder<Attribute> mh = Attributes.MAX_HEALTH;

            if (AttrUtil.has(player, mh)) {
                AttributeInstance inst = player.getAttribute(mh);
                AttributeModifier existing = inst.getModifier(CRYSTAL_HEART_BOOST);
                double hearts = existing != null ? existing.amount() : 0;

                if (hearts + 2 <= MatchaFlavouredConfig.Common.CRYSTAL_HEART_MAXIMUM.get()) {
                    inst.removeModifier(CRYSTAL_HEART_BOOST);
                    inst.addPermanentModifier(new AttributeModifier(CRYSTAL_HEART_BOOST, hearts + 2, AttributeModifier.Operation.ADD_VALUE));

                    player.heal(2.0F);
                } else {
                    EffectUtil.add(player, MFEffects.EXALTATION, 3600);
                }

                EntityUtil.playSound(player, SoundEvents.AMETHYST_BLOCK_BREAK);
                if (level instanceof ServerLevel server) {
                    server.sendParticles(ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER_OMINOUS, player.getX(), player.getY() + 0.5, player.getZ(), player.level().random.nextInt(6,12), 0, 0.5, 0.25, 0.05);
                }
                itemStack.consume(1, player);

                return itemStack;
            }
        }

        return itemStack;
    }

    public int getUseDuration(ItemStack p_40680_, LivingEntity p_345962_) {
        return 30;
    }

    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.BOW;
    }
}
