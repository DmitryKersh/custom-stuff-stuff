package ru.divinecraft.luckyblocks.block;

import lombok.val;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.block.AbstractCustomBlock;
import ru.divinecraft.customstuff.api.block.manager.CustomBlockManager;
import ru.divinecraft.customstuff.api.render.RenderedCustomBlock;
import ru.divinecraft.luckyblocks.LuckyBlocksConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class AbstractLuckyBlock extends AbstractCustomBlock implements LuckyBlocksConstants.LuckyBlockAward {

    protected @NotNull Plugin plugin;
    protected @NotNull RenderedCustomBlock render;

    // protected Map<ItemStack, Double> drops;

    protected AbstractLuckyBlock(
            final @NotNull CustomBlockManager manager, final @NotNull Location location,
            final @NotNull Plugin plugin, final @NotNull RenderedCustomBlock render) {
        super(manager, location);
        this.plugin = plugin;
        this.render = render;
    }

    protected abstract ItemStack resolveLoot();

    protected void createPhysicalBlock() {
        location.getBlock().setType(Material.BARRIER);
    }

    protected void removePhysicalBlock() {
        location.getBlock().setType(Material.AIR);
    }

    @Override
    public void awardPlayer(final @NotNull Location location,  final @NotNull Player player) {
        LuckyBlocksConstants.LuckyBlockAward.loot(resolveLoot()).awardPlayer(location, player);
    }

    @Override
    public void onBreak( final @NotNull Player player) {
        awardPlayer(getLocation().clone(), player);
    }

    // render
    protected void startDisplay() {
        {
            final Plugin thisPlugin;
            final Server server;
            if (!(server = (thisPlugin = plugin).getServer()).isPrimaryThread()) server.getScheduler()
                    .runTask(thisPlugin, this::createPhysicalBlock);
            else createPhysicalBlock();
        }
        render.start();
    }

    protected void stopDisplay() {
        {
            final Plugin thisPlugin;
            final Server server;
            if (!(server = (thisPlugin = plugin).getServer()).isPrimaryThread()) server.getScheduler()
                    .runTask(thisPlugin, this::removePhysicalBlock);
            else removePhysicalBlock();
        }

        render.close();
    }
}

