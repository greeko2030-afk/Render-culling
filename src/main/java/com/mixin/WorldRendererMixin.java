package com.greeko.renderculling.mixin;

import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.WorldRenderer;
import com.greeko.renderculling.util.FrustumMath;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Inject(method = "setupFrustum", at = @At("TAIL"))
    private void onSetupFrustum(Frustum frustum, CallbackInfo ci) {
        FrustumMath.setFrustum(frustum);
    }
}

