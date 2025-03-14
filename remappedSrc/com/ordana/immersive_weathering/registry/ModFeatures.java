package com.ordana.immersive_weathering.registry;

import com.ordana.immersive_weathering.registry.features.IcicleClusterFeature;
import com.ordana.immersive_weathering.registry.features.IcicleClusterFeatureConfig;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.BlockPileFeature;
import net.minecraft.world.gen.feature.BlockPileFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModFeatures {
    public static void registerFeatures() {
        Registry.register(Registry.FEATURE, "immersive_weathering:icicle_cluster", new IcicleClusterFeature(IcicleClusterFeatureConfig.CODEC));
        Registry.register(Registry.FEATURE, "immersive_weathering:oak_leaf_pile", new BlockPileFeature(BlockPileFeatureConfig.CODEC));
        Registry.register(Registry.FEATURE, "immersive_weathering:dark_oak_leaf_pile", new BlockPileFeature(BlockPileFeatureConfig.CODEC));
        Registry.register(Registry.FEATURE, "immersive_weathering:spruce_leaf_pile", new BlockPileFeature(BlockPileFeatureConfig.CODEC));
        Registry.register(Registry.FEATURE, "immersive_weathering:birch_leaf_pile", new BlockPileFeature(BlockPileFeatureConfig.CODEC));

        RegistryKey<PlacedFeature> icicles = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                new Identifier("immersive_weathering", "icicles"));
        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.ICY), GenerationStep.Feature.TOP_LAYER_MODIFICATION, icicles);

        RegistryKey<PlacedFeature> oak_leaf_pile = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                new Identifier("immersive_weathering", "oak_leaf_pile"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey((BiomeKeys.FOREST)), GenerationStep.Feature.VEGETAL_DECORATION, oak_leaf_pile);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey((BiomeKeys.WINDSWEPT_FOREST)), GenerationStep.Feature.VEGETAL_DECORATION, oak_leaf_pile);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey((BiomeKeys.FLOWER_FOREST)), GenerationStep.Feature.VEGETAL_DECORATION, oak_leaf_pile);

        RegistryKey<PlacedFeature> dark_oak_leaf_pile = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                new Identifier("immersive_weathering", "dark_oak_leaf_pile"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey((BiomeKeys.DARK_FOREST)), GenerationStep.Feature.VEGETAL_DECORATION, dark_oak_leaf_pile);

        RegistryKey<PlacedFeature> birch_leaf_pile = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                new Identifier("immersive_weathering", "birch_leaf_pile"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey((BiomeKeys.BIRCH_FOREST)), GenerationStep.Feature.VEGETAL_DECORATION, birch_leaf_pile);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey((BiomeKeys.OLD_GROWTH_BIRCH_FOREST)), GenerationStep.Feature.VEGETAL_DECORATION, birch_leaf_pile);

        RegistryKey<PlacedFeature> spruce_leaf_pile = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                new Identifier("immersive_weathering", "spruce_leaf_pile"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey((BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA)), GenerationStep.Feature.VEGETAL_DECORATION, spruce_leaf_pile);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey((BiomeKeys.OLD_GROWTH_PINE_TAIGA)), GenerationStep.Feature.VEGETAL_DECORATION, spruce_leaf_pile);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey((BiomeKeys.TAIGA)), GenerationStep.Feature.VEGETAL_DECORATION, spruce_leaf_pile);

    }
}
