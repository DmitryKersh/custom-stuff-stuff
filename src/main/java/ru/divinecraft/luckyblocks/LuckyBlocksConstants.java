package ru.divinecraft.luckyblocks;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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
        public final @NotNull NamespacedKey ALLIN_LUCKYBLOCK = create("allin_luckyblock");

        private @NotNull NamespacedKey create(final @NotNull String name) {
            return NamespacedKeys.of(NAMESPACE, name);
        }
    }

    @UtilityClass
    public static class RecipeKey {
        public final @NotNull NamespacedKey BASIC_LUCKYBLOCK = create("basic_luckyblock");
        public final @NotNull NamespacedKey ALLIN_LUCKYBLOCK = create("allin_luckyblock");

        private @NotNull NamespacedKey create(final @NotNull String name) {
            return NamespacedKeys.of(NAMESPACE, RECIPES_PREFIX + '/' + name);
        }
    }

    @UtilityClass
    public static class Block {
        @UtilityClass
        public static class Properties {
            public final @NotNull BlockProperties BASIC_LUCKYBLOCK = StaticBlockProperties.create(true, 1);
            public final @NotNull BlockProperties ALLIN_LUCKYBLOCK = StaticBlockProperties.create(true, 1);
        }
    }

    @UtilityClass
    public static class Item {
        @UtilityClass
        public static class Properties {
            public final @NotNull ItemProperties BASIC_LUCKYBLOCK = stackable();
            public final @NotNull ItemProperties ALLIN_LUCKYBLOCK = stackable();
        }
    }

    @FunctionalInterface
    public interface LuckyBlockAward {
        void awardPlayer(@NotNull Location location, @NotNull Player player);

        static @NotNull LuckyBlockAward loot(final @NotNull ItemStack item){
            return (location, player) -> location.getWorld().dropItemNaturally(location, item);
        }

        static @NotNull LuckyBlockAward message(final @NotNull String message){
            return (location, player) -> player.sendMessage(message);
        }
    }
}
