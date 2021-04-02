package ru.divinecraft.luckyblocks.block;

import ru.divinecraft.luckyblocks.LuckyBlocksConstants;
import lombok.val;
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

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Map.entry;

public class BasicLuckyBlock extends AbstractLuckyBlock implements LuckyBlocksConstants.LuckyBlockAward {
    private static final Map<ItemStack, Double> DROPS_MAP = Map.ofEntries(
            entry(new ItemStack(Material.DIAMOND), 1.0),
            entry(new ItemStack(Material.IRON_AXE), 5.0),
            entry(new ItemStack(Material.ENDER_PEARL), 5.0),
            entry(new ItemStack(Material.MOSSY_COBBLESTONE, 16), 9.0),
            entry(new ItemStack(Material.PORKCHOP, 3), 10.0),
            entry(new ItemStack(Material.POTATO), 10.0),
            entry(new ItemStack(Material.CARROT), 10.0),
            entry(new ItemStack(Material.IRON_HELMET), 10.0),
            entry(new ItemStack(Material.BIRCH_LOG, 32), 10.0),
            entry(new ItemStack(Material.SUGAR_CANE, 3), 10.0),
            entry(new ItemStack(Material.NETHER_WART, 12), 10.0),
            entry(new ItemStack(Material.MELON_SEEDS), 10.0)
            );

    protected BasicLuckyBlock(
            final @NotNull CustomBlockManager manager,
            final @NotNull Location location,
            final @NotNull Plugin plugin,
            final @NotNull RenderedCustomBlock render) {
        super(manager, location, plugin, render);
    }

    @Override
    protected ItemStack resolveLoot(){
        double sum = 0.0;
        for (val entry : DROPS_MAP.entrySet()) sum += entry.getValue();

        val random = ThreadLocalRandom.current();
        val randomNumber = random.nextDouble() * sum;

        sum = 0.0;
        for (val entry : DROPS_MAP.entrySet()){
            if (sum >= randomNumber) return entry.getKey();
            sum += entry.getValue();
        }
        return null;
    }

    public static @NotNull CustomBlock create(final @NotNull CustomBlockManager manager,
                                              final @NotNull Location location,
                                              final @NotNull Plugin plugin,
                                              final @NotNull CustomBlockRenderer renderer) {
        return new BasicLuckyBlock(manager, location, plugin, renderer.create(location, LuckyBlocksEnum.BASIC_LUCKYBLOCK
                .renderingProperties(), BlockFace.EAST));
    }

    @Override
    public void onCreate() {
        startDisplay();
    }

    @Override
    public void onRemove() {
        stopDisplay();
    }

    @Override
    public @NotNull NamespacedKey getType() {
        return LuckyBlocksConstants.Type.BASIC_LUCKYBLOCK;
    }

    @Override
    public @NotNull BlockProperties getProperties() {
        return LuckyBlocksConstants.Block.Properties.BASIC_LUCKYBLOCK;
    }
}
