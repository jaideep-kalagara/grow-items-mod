package net.supergamer.growitems.advancements.critera;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.util.Identifier;
import net.supergamer.growitems.GrowItems;
import net.supergamer.growitems.advancements.critera.custom.ItemGrownCriterion;

public class ModCriteria {
    public static final ItemGrownCriterion ITEM_GROWN = Criteria.register(Identifier.of(GrowItems.MOD_ID, "item_grown").toString(), new ItemGrownCriterion());

    public static void registerModCriteria() {
        GrowItems.LOGGER.info("Registering advancement criteria for " + GrowItems.MOD_ID);
    }
}
