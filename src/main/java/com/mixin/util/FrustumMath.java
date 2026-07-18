
package com.greeko.renderculling.util;

import net.minecraft.client.render.Frustum;
import net.minecraft.util.math.Box;

public class FrustumMath {
    private static Frustum currentFrustum;

    public static void setFrustum(Frustum frustum) {
        currentFrustum = frustum;
    }

    public static boolean isVisible(Box boundingBox) {
        if (currentFrustum == null || boundingBox == null) {
            return true; // Default to rendering if camera state is unknown to prevent invisible entities
        }
        return currentFrustum.isVisible(boundingBox);
    }
}
