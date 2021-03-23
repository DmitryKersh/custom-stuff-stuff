package csapitraining.luckyblocks.block;


import csapitraining.luckyblocks.LuckyBlocksConstants;
import csapitraining.luckyblocks.LuckyBlocksConstants.Type;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import ru.divinecraft.customstuff.api.item.CustomItem;
import ru.divinecraft.customstuff.api.render.CustomBlockRenderingProperties;
import ru.divinecraft.customstuff.api.render.SimpleCustomBlockRenderingProperties;
import ru.divinecraft.customstuff.api.util.item.ItemStackBuilder;
import ru.divinecraft.customstuff.api.util.item.SimpleItemStackBuilder;

import java.util.function.Function;

@RequiredArgsConstructor
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum LuckyBlocksEnum {
    BASIC_LUCKYBLOCK(Type.BASIC_LUCKYBLOCK, 15, "block.dmitrykersh.basic_luckyblock"),
    ALLIN_LUCKYBLOCK(Type.ALLIN_LUCKYBLOCK, 15, "block.dmitrykersh.allin_luckyblock");

    @Getter
    @NotNull CustomBlockRenderingProperties renderingProperties;
    @NotNull ItemStack handItem;

    <M extends ItemMeta> LuckyBlocksEnum(final @NonNull ItemStackBuilder<M> baseItem,
                                       final @NonNull Function<ItemStackBuilder<M>, ItemStack> handItemPatcher,
                                       final @NotNull CustomBlockRenderingProperties.RenderingHint @Unmodifiable @NonNull ... renderingHints) {
        this(
                SimpleCustomBlockRenderingProperties.create(baseItem.build(), renderingHints),
                handItemPatcher.apply(baseItem)
        );
    }

    LuckyBlocksEnum(final @NonNull NamespacedKey type, final int damage, final @NonNull String localizedName) {
        this(
                SimpleItemStackBuilder.create(Bukkit.getItemFactory())
                        .type(Material.DIAMOND_HOE)
                        .damage((short) damage)
                        .unbreakable(true),
                builder -> builder
                        .modifyMeta(meta -> meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES))
                        .localizedName(localizedName)
                        .build(nbt -> CustomItem.writeType(nbt, type))
        );
    }

    public @NotNull ItemStack handItem() {
        return handItem.clone();
    }
}
