package csapitraining.luckyblocks.block;

import lombok.val;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.block.AbstractCustomBlock;
import ru.divinecraft.customstuff.api.block.manager.CustomBlockManager;
import ru.divinecraft.customstuff.api.render.RenderedCustomBlock;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class AbstractLuckyBlock extends AbstractCustomBlock {

    @NotNull Plugin plugin;
    @NotNull RenderedCustomBlock render;

    protected Map<ItemStack, Double> drops;

    protected AbstractLuckyBlock(
            @NotNull final CustomBlockManager manager, @NotNull final Location location,
            @NotNull final Plugin plugin, @NotNull final RenderedCustomBlock render) {
        super(manager, location);
        this.plugin = plugin;
        this.render = render;
        drops = new HashMap<>();
    }


    protected void createPhysicalBlock() {
        location.getBlock().setType(Material.BARRIER);
    }

    protected void removePhysicalBlock() {
        location.getBlock().setType(Material.AIR);
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

}
