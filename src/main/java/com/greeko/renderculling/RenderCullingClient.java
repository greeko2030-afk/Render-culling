package com.greeko.renderculling;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RenderCullingClient implements ClientModInitializer {
    public static final String MOD_ID = "renderculling";
    public static final Logger LOGGER = LoggerFactory.getLogger("Greeko Render Culling");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Greeko Render Culling initialized for Minecraft 1.21.x!");
        LOGGER.info("Optimizing entities and block entities for maximum FPS...");
    }
}
