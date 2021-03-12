package csapitraining.luckyblocks.item;

import csapitraining.luckyblocks.LuckyBlocksConstants;
import csapitraining.luckyblocks.block.LuckyBlocksEnum;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import ru.divinecraft.customstuff.api.item.AbstractCustomItems;
import csapitraining.luckyblocks.LuckyBlocksConstants.Type;
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
                SimpleCustomItemRegistration.of(Type.BASIC_LUCKYBLOCK, ((customItemManager, compoundMap) ->
                    StaticCustomItem.create(Type.BASIC_LUCKYBLOCK,
                            LuckyBlocksConstants.Item.Properties.BASIC_LUCKYBLOCK,
                            customItemManager, LuckyBlocksEnum.BASIC_LUCKYBLOCK.handItem(),
                            Type.BASIC_LUCKYBLOCK, compoundMap
                            )
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
