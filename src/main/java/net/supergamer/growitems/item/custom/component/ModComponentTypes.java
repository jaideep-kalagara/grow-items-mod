package net.supergamer.growitems.item.custom.component;

import net.fabricmc.fabric.api.item.v1.ComponentTooltipAppenderRegistry;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.supergamer.growitems.GrowItems;

public class ModComponentTypes {

    public static final ComponentType<RichSoilUpgradeComponent> RICH_SOIL_UPGRADE_COMPONENT_COMPONENT_TYPE = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(GrowItems.MOD_ID, "rich_soil_upgrade_component"),
            ComponentType.<RichSoilUpgradeComponent>builder().codec(RichSoilUpgradeComponent.CODEC).build()
    );

    public static void registerModComponents() {
        ComponentTooltipAppenderRegistry.addAfter(
                DataComponentTypes.DAMAGE,
                RICH_SOIL_UPGRADE_COMPONENT_COMPONENT_TYPE
        );
        GrowItems.LOGGER.info("Registering components for " + GrowItems.MOD_ID);
    }
}
