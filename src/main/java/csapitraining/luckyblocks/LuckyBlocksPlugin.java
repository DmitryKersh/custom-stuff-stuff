package csapitraining.luckyblocks;

import csapitraining.luckyblocks.block.LuckyBlocksCustomBlocks;
import csapitraining.luckyblocks.item.LuckyBlocksItems;
import lombok.val;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import ru.divinecraft.customstuff.api.block.CustomBlocks;
import ru.divinecraft.customstuff.api.item.CustomItems;
import ru.divinecraft.customstuff.api.item.manager.CustomItemManager;
import ru.divinecraft.customstuff.api.recipe.manager.RecipeManager;
import ru.divinecraft.customstuff.api.service.BukkitLoadingCustomStuff;

public final class LuckyBlocksPlugin extends JavaPlugin {
    @Override
    public void onLoad(){
        BukkitLoadingCustomStuff loadingCustomStuff = getServer().getServicesManager().load(BukkitLoadingCustomStuff.class);

        assert loadingCustomStuff != null;

        try (val loading = loadingCustomStuff.request(this)){
            CustomBlocks.register(LuckyBlocksCustomBlocks.create(this), loading);
            CustomItems.register(LuckyBlocksItems.create(), loading);

            loading.onceReady(customStuff -> {
                final CustomItemManager itemManager;
                final Server server;
                final RecipeManager recipeManager;

                final ItemStack basicLuckyBlock;
            });
        }
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
