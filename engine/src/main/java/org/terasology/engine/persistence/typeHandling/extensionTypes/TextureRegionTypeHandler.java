// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.engine.persistence.typeHandling.extensionTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.gestalt.assets.Asset;
import org.terasology.engine.rendering.assets.texture.TextureRegion;
import org.terasology.engine.rendering.assets.texture.TextureRegionAsset;
import org.terasology.engine.utilities.Assets;
import org.terasology.persistence.typeHandling.StringRepresentationTypeHandler;

import java.util.Optional;

public class TextureRegionTypeHandler extends StringRepresentationTypeHandler<TextureRegion> {
    private static final Logger logger = LoggerFactory.getLogger(TextureRegionTypeHandler.class);

    @Override
    public String getAsString(TextureRegion item) {
        if (item instanceof Asset) {
            return ((Asset) item).getUrn().toString();
        }
        return "";
    }

    @Override
    public TextureRegion getFromString(String representation) {
        Optional<TextureRegionAsset> region = Assets.getTextureRegion(representation);
        if (region.isPresent()) {
            return region.get();
        } else {
            logger.error("Failed to resolve texture region '{}'", representation);
            return Assets.getTextureRegion("engine:default").get();
        }
    }
}
