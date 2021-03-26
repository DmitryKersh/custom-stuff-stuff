package ru.divinecraft.luckyblocks.item;

import ru.divinecraft.luckyblocks.LuckyBlocksConstants;
import ru.divinecraft.luckyblocks.block.LuckyBlocksEnum;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import ru.divinecraft.customstuff.api.item.AbstractCustomItems;
import ru.divinecraft.customstuff.api.item.CustomItems;
import ru.divinecraft.customstuff.api.item.StaticCustomItem;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

public class LuckyBlocksItems extends AbstractCustomItems {
    @NotNull @Unmodifiable Collection<CustomItemRegistration> registrations;

    public LuckyBlocksItems() {
        registrations = Arrays.asList(
                SimpleCustomItemRegistration.of(
                        LuckyBlocksConstants.Type.BASIC_LUCKYBLOCK, (customItemManager, compoundMap) ->
                    StaticCustomItem.create(LuckyBlocksConstants.Type.BASIC_LUCKYBLOCK,
                            LuckyBlocksConstants.Item.Properties.BASIC_LUCKYBLOCK,
                            customItemManager, LuckyBlocksEnum.BASIC_LUCKYBLOCK.handItem(),
                            LuckyBlocksConstants.Type.BASIC_LUCKYBLOCK, compoundMap
                            )),
                SimpleCustomItemRegistration.of(
                        LuckyBlocksConstants.Type.ALLIN_LUCKYBLOCK, (customItemManager, compoundMap) ->
                        StaticCustomItem.create(LuckyBlocksConstants.Type.ALLIN_LUCKYBLOCK,
                                LuckyBlocksConstants.Item.Properties.ALLIN_LUCKYBLOCK,
                                customItemManager, LuckyBlocksEnum.ALLIN_LUCKYBLOCK.handItem(),
                                LuckyBlocksConstants.Type.ALLIN_LUCKYBLOCK, compoundMap
                        ))
        );
    }

    @Override
    public @NotNull Enumeration<@NotNull CustomItemRegistration> registrations() {
        return Collections.enumeration(registrations);
    }

    public static CustomItems create() {
        return new LuckyBlocksItems();
    }
}
