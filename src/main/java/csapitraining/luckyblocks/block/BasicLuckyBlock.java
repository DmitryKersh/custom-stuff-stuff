package csapitraining.luckyblocks.block;

import csapitraining.luckyblocks.LuckyBlocksConstants.*;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.block.CustomBlock;
import ru.divinecraft.customstuff.api.block.manager.CustomBlockManager;
import ru.divinecraft.customstuff.api.block.properties.BlockProperties;

public class BasicLuckyBlock extends AbstractLuckyBlock {
    protected BasicLuckyBlock(
            final @NotNull CustomBlockManager manager,
            final @NotNull Location location,
            final @NotNull Plugin plugin) {
        super(manager, location, plugin);
    }

    public static @NotNull CustomBlock create(final @NotNull CustomBlockManager manager,
                                              final @NotNull Location location,
                                              final @NotNull Plugin plugin) {
        return new BasicLuckyBlock(manager, location, plugin);
    }

    @Override
    public @NotNull NamespacedKey getType() {
        return Type.BASIC_LUCKYBLOCK;
    }

    @Override
    public @NotNull BlockProperties getProperties() {
        return null;
    }
}
