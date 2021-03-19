package csapitraining.luckyblocks;

import csapitraining.luckyblocks.block.LuckyBlocksCustomBlocks;
import csapitraining.luckyblocks.item.LuckyBlocksItems;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.divinecraft.customstuff.api.block.CustomBlocks;
import ru.divinecraft.customstuff.api.item.CustomItem;
import ru.divinecraft.customstuff.api.item.CustomItems;
import ru.divinecraft.customstuff.api.item.manager.CustomItemManager;
import ru.divinecraft.customstuff.api.recipe.manager.RecipeManager;
import ru.divinecraft.customstuff.api.service.BukkitLoadingCustomStuff;

public final class LuckyBlocksPlugin extends JavaPlugin {
    private static final String RECIPES_GROUP = "luckyblocks";

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
                (recipeManager = customStuff.recipeManager()).registerRecipe(
                        shapedRecipeBase(
                                basicLuckyBlock = requireCustomItem(
                                        LuckyBlocksConstants.Type.BASIC_LUCKYBLOCK,
                                        itemManager = customStuff.itemManager()),
                                LuckyBlocksConstants.RecipeKey.BASIC_LUCKYBLOCK,
                                "dgd",
                                "ici",
                                "dgd")
                        .setIngredient('c', Material.CHEST)
                        .setIngredient('d', Material.DIRT)
                        .setIngredient('i', Material.IRON_INGOT)
                        .setIngredient('g', Material.GOLD_INGOT)
                );

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

    private static @NotNull ShapedRecipe shapedRecipeBase(final @NotNull ItemStack item,
                                                          final @NotNull NamespacedKey key,
                                                          final @NotNull String line1,
                                                          final @NotNull String line2,
                                                          final @NotNull String line3) {
        final ShapedRecipe recipe;
        (recipe = new ShapedRecipe(key, item)).setGroup(RECIPES_GROUP);
        return recipe.shape(line1, line2, line3);
    }

    private static @NotNull ItemStack requireCustomItem(final @NotNull NamespacedKey itemType,
                                                        final @NotNull CustomItemManager itemManager) {
        final CustomItem customItem;
        if ((customItem = itemManager.createNewCustomItem(itemType, null)) == null) throw new AssertionError(
                "Missing registered custom item for type \"" + itemType + "\""
        );

        return customItem.asItemStack();
    }
}
