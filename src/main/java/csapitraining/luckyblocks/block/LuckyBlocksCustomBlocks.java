package csapitraining.luckyblocks.block;

import csapitraining.luckyblocks.LuckyBlocksConstants.Type;
import lombok.NonNull;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import ru.divinecraft.customstuff.api.block.AbstractCustomBlocks;
import ru.divinecraft.customstuff.api.block.CustomBlocks;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

public final class LuckyBlocksCustomBlocks extends AbstractCustomBlocks {
    @NotNull @Unmodifiable Collection<CustomBlockRegistration> registrations;

    public LuckyBlocksCustomBlocks(final @NotNull Plugin plugin) {
        registrations = Arrays.asList(
                SimpleCustomBlockRegistration
                        .of(Type.BASIC_LUCKYBLOCK, (manager, location, direction, nbt) -> BasicLuckyBlock
                                .create(manager, location, plugin, customStuff().blockRenderer())),

                SimpleCustomBlockRegistration.of(Type.ALLIN_LUCKYBLOCK,
                        (manager, location, direction, nbtTags) -> AllInLuckyBlock
                                .create(manager, location, plugin, customStuff().blockRenderer()))
        );
    }

    @Override
    public @NotNull Enumeration<@NotNull CustomBlockRegistration> registrations() {
        return Collections.enumeration(registrations);
    }

    public static CustomBlocks create(final @NonNull Plugin plugin) {
        return new LuckyBlocksCustomBlocks(plugin);
    }
}
