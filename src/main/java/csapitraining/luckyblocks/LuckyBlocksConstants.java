package csapitraining.luckyblocks;

import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.util.NamespacedKeys;

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
}
