package com.greeko.renderculling.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import com.greeko.renderculling.util.FrustumMath;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onRenderEntity(Entity entity, double x, double y, double z, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();

        // New Feature: Disable local player skin rendering in 1st person world view, but keep it in Inventory
        if (entity instanceof PlayerEntity && client.player != null && entity.getUuid().equals(client.player.getUuid())) {
            boolean isFirstPerson = client.options.getPerspective().isFirstPerson();
            boolean isInventoryOpen = client.currentScreen != null; // True if any UI/Inventory is open

            // Cancel rendering if in 1st person and no inventory screen is open
            if (isFirstPerson && !isInventoryOpen) {
                ci.cancel();
                return;
            }
        }

        // Existing Feature: Frustum Culling for all entities
        if (!FrustumMath.isVisible(entity.getBoundingBox())) {
            ci.cancel();
        }
    }
}
