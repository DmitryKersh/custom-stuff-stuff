package csapitraining.luckyblocks.block;

import csapitraining.luckyblocks.LuckyBlocksConstants.*;
import lombok.val;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
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
import java.util.WeakHashMap;

public class BasicLuckyBlock extends AbstractLuckyBlock {
    protected BasicLuckyBlock(
            final @NotNull CustomBlockManager manager,
            final @NotNull Location location,
            final @NotNull Plugin plugin,
            final @NotNull RenderedCustomBlock render) {
        super(manager, location, plugin, render);
        drops.put(new ItemStack(Material.DIAMOND), 0.01);

        drops.put(new ItemStack(Material.IRON_AXE), 0.05);
        drops.put(new ItemStack(Material.ENDERMAN_SPAWN_EGG), 0.05);

        drops.put(new ItemStack(Material.MOSSY_COBBLESTONE, 16), 0.09);

        drops.put(new ItemStack(Material.PORKCHOP, 3), 0.1);
        drops.put(new ItemStack(Material.POTATO), 0.1);
        drops.put(new ItemStack(Material.CARROT), 0.1);
        drops.put(new ItemStack(Material.IRON_HELMET), 0.1);
        drops.put(new ItemStack(Material.BIRCH_LOG, 32), 0.1);
        drops.put(new ItemStack(Material.SUGAR_CANE, 3), 0.1);
        drops.put(new ItemStack(Material.NETHER_WART, 12), 0.1);
        drops.put(new ItemStack(Material.MELON_SEEDS), 0.1);
    }

    protected ItemStack resolveLoot(){
        double sum = 0.0;
        for (val entry : drops.entrySet()) sum += entry.getValue();

        val random = new Random();
        val randomNumber = random.nextDouble() * sum;

        for (val entry : drops.entrySet()){
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
    public void onBreak(@NotNull final Player player) {
        Location location = this.getLocation().clone();
        location.getWorld().dropItem(location.add(0.5, 0.5, 0.5), resolveLoot());
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
