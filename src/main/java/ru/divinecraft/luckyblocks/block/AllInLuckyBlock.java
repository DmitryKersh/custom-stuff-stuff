package ru.divinecraft.luckyblocks.block;

import ru.divinecraft.luckyblocks.LuckyBlocksConstants;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.block.CustomBlock;
import ru.divinecraft.customstuff.api.block.manager.CustomBlockManager;
import ru.divinecraft.customstuff.api.block.properties.BlockProperties;
import ru.divinecraft.customstuff.api.render.CustomBlockRenderer;
import ru.divinecraft.customstuff.api.render.RenderedCustomBlock;

import java.util.Random;

public class AllInLuckyBlock extends AbstractLuckyBlock {
    private static final double CHANCE = 0.1;

    public static @NotNull CustomBlock create(final @NotNull CustomBlockManager manager,
                                              final @NotNull Location location,
                                              final @NotNull Plugin plugin,
                                              final @NotNull CustomBlockRenderer renderer) {
        return new BasicLuckyBlock(manager, location, plugin, renderer.create(location, LuckyBlocksEnum.ALLIN_LUCKYBLOCK
                .renderingProperties(), BlockFace.EAST));
    }

    protected AllInLuckyBlock(final @NotNull CustomBlockManager manager,
                              final @NotNull Location location,
                              final @NotNull Plugin plugin,
                              final @NotNull RenderedCustomBlock render) {
        super(manager, location, plugin, render);
    }


    @Override
    protected ItemStack resolveLoot() {
        Random random = new Random();
        if (random.nextDouble() <= CHANCE) return new ItemStack(Material.NETHER_STAR);
        return new ItemStack(Material.DIRT);
    }

    @Override
    public void onBreak(@NotNull final Player player) {
        Location location = getLocation().clone();
        location.getWorld().dropItem(location.add(0.5, 0.5, 0.5), resolveLoot());
    }

    @Override
    public @NotNull NamespacedKey getType() {
        return LuckyBlocksConstants.Type.ALLIN_LUCKYBLOCK;
    }

    @Override
    public @NotNull BlockProperties getProperties() {
        return LuckyBlocksConstants.Block.Properties.ALLIN_LUCKYBLOCK;
    }



}
