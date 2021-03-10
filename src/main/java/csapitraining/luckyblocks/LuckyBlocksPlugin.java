package csapitraining.luckyblocks;

import csapitraining.luckyblocks.blocks.LuckyBlocksCustomBlocks;
import lombok.val;
import org.bukkit.plugin.java.JavaPlugin;
import ru.divinecraft.customstuff.api.block.CustomBlocks;
import ru.divinecraft.customstuff.api.service.BukkitLoadingCustomStuff;

public final class LuckyBlocksPlugin extends JavaPlugin {
    @Override
    public void onLoad(){
        BukkitLoadingCustomStuff loadingCustomStuff = getServer().getServicesManager().load(BukkitLoadingCustomStuff.class);

        assert loadingCustomStuff != null;

        try (val loading = loadingCustomStuff.request(this)){
            CustomBlocks.register(LuckyBlocksCustomBlocks.create(this), loading);
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
