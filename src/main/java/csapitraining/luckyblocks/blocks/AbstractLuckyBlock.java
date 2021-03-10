package csapitraining.luckyblocks.blocks;

import csapitraining.luckyblocks.LuckyBlocksConstants;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.block.AbstractCustomBlock;
import ru.divinecraft.customstuff.api.block.manager.CustomBlockManager;
import ru.divinecraft.customstuff.api.block.properties.BlockProperties;
import ru.divinecraft.customstuff.api.render.RenderedCustomBlock;

public abstract class AbstractLuckyBlock extends AbstractCustomBlock {

    @NotNull Plugin plugin;

    protected AbstractLuckyBlock(
            @NotNull final CustomBlockManager manager, @NotNull final Location location,
            @NotNull final Plugin plugin) {
        super(manager, location);
        this.plugin = plugin;
    }


    protected void createPhysicalBlock() {
        location.getBlock().setType(Material.BARRIER);
    }

    protected void removePhysicalBlock() {
        location.getBlock().setType(Material.AIR);
    }

    // render
    /*

    protected void startDisplay() {
        {
            final Plugin thisPlugin;
            final Server server;
            if (!(server = (thisPlugin = plugin).getServer()).isPrimaryThread()) server.getScheduler()
                    .runTask(thisPlugin, this::createPhysicalBlock);
            else createPhysicalBlock();
        }
        // render.start();
    }

    protected void stopDisplay() {
        {
            final Plugin thisPlugin;
            final Server server;
            if (!(server = (thisPlugin = plugin).getServer()).isPrimaryThread()) server.getScheduler()
                    .runTask(thisPlugin, this::removePhysicalBlock);
            else removePhysicalBlock();
        }

        // render.close();
    }

    */
}
