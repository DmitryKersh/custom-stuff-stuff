package csapitraining.luckyblocks;

import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.block.properties.BlockProperties;
import ru.divinecraft.customstuff.api.block.properties.StaticBlockProperties;
import ru.divinecraft.customstuff.api.item.properties.ItemProperties;
import ru.divinecraft.customstuff.api.util.NamespacedKeys;

import static ru.divinecraft.customstuff.api.item.properties.StaticItemProperties.stackable;

@UtilityClass
public class LuckyBlocksConstants {
    private static final @NotNull String NAMESPACE = "lucky_blocks";
    private static final @NotNull String RECIPES_PREFIX = "recipes";

    @UtilityClass
    public static class Type {
        public final @NotNull NamespacedKey BASIC_LUCKYBLOCK = create("basic_luckyblock");

        private @NotNull NamespacedKey create(final @NotNull String name) {
            return NamespacedKeys.of(NAMESPACE, name);
        }
    }

    @UtilityClass
    public static class RecipeKey {
        public final @NotNull NamespacedKey BASIC_LUCKYBLOCK = create("basic_luckyblock");

        private @NotNull NamespacedKey create(final @NotNull String name) {
            return NamespacedKeys.of(NAMESPACE, RECIPES_PREFIX + '/' + name);
        }
    }

    @UtilityClass
    public static class Block {
        @UtilityClass
        public static class Properties {
            public final @NotNull BlockProperties BASIC_LUCKYBLOCK = StaticBlockProperties.create(true, 1);
        }
    }

    @UtilityClass
    public static class Item {
        @UtilityClass
        public static class Properties {
            public final @NotNull ItemProperties BASIC_LUCKYBLOCK = stackable();
        }
    }
}
