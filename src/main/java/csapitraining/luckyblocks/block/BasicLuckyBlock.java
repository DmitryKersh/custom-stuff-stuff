package csapitraining.luckyblocks.block;

import csapitraining.luckyblocks.LuckyBlocksConstants.*;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.block.CustomBlock;
import ru.divinecraft.customstuff.api.block.manager.CustomBlockManager;
import ru.divinecraft.customstuff.api.block.properties.BlockProperties;
import ru.divinecraft.customstuff.api.render.CustomBlockRenderer;
import ru.divinecraft.customstuff.api.render.RenderedCustomBlock;

public class BasicLuckyBlock extends AbstractLuckyBlock {
    protected BasicLuckyBlock(
            final @NotNull CustomBlockManager manager,
            final @NotNull Location location,
            final @NotNull Plugin plugin,
            final @NotNull RenderedCustomBlock render) {
        super(manager, location, plugin, render);
    }

    public static @NotNull CustomBlock create(final @NotNull CustomBlockManager manager,
                                              final @NotNull Location location,
                                              final @NotNull Plugin plugin,
                                              final @NotNull CustomBlockRenderer renderer) {
        return new BasicLuckyBlock(manager, location, plugin, renderer.create(location, LuckyBlocksEnum.BASIC_LUCKYBLOCK
                .renderingProperties(), BlockFace.EAST));
    }

    @Override
    public void onBreak(@NotNull final Player player) {

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
