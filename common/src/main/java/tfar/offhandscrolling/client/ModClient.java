package tfar.offhandscrolling.client;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.glfw.GLFW;

public class ModClient {

    public static final KeyMapping MODIFIER = new KeyMapping("modifier", GLFW.GLFW_KEY_LEFT_ALT,"Offhand Category");

    static int offhandSelected;

    public static void keyBinds() {
        registerKeyBind(MODIFIER);
    }

    public static void registerKeyBind(KeyMapping keyMapping) {
        Minecraft.getInstance().options.keyMappings = ArrayUtils.add(Minecraft.getInstance().options.keyMappings, keyMapping);
    }

    public static void handleKeybinds(Minecraft minecraft) {
        if (areAnyHotbarKeysDown(minecraft) && MODIFIER.consumeClick()) {
            for(int i = 0; i < 9; ++i) {
                if (minecraft.options.keyHotbarSlots[i].consumeClick()) {
                    if (minecraft.player.isSpectator()) {
                 //       minecraft.gui.getSpectatorGui().onHotbarSelected(i);
                    } else {
                        trySwapOffhand(i);
                    }
                }
            }
        }
    }

    public static boolean trySwapOffhand(int newSlot) {
        int oldSlot = offhandSelected;
        if (oldSlot != newSlot) {

            Minecraft minecraft = Minecraft.getInstance();
            Player player = minecraft.player;
            if (player != null) {
                AbstractContainerMenu menu = player.inventoryMenu;

                ItemStack offhandStack = player.getInventory().offhand.get(0);

                int oldInvSlot = oldSlot + 27;

                minecraft.gameMode.handleInventoryMouseClick(menu.containerId, oldInvSlot, 40, ClickType.SWAP, player);
                minecraft.gameMode.handleInventoryMouseClick(menu.containerId, newSlot + 27, 40, ClickType.SWAP, player);
                offhandSelected = newSlot;
                return true;
            }
        }
        return false;
    }

    public static boolean areAnyHotbarKeysDown(Minecraft minecraft) {
        for(int i = 0; i < 9; ++i) {
            if (minecraft.options.keyHotbarSlots[i].isDown()) {
                return true;
            }
        }
        return false;
    }

    public static boolean handleScroll(Inventory inventory, double direction) {
        if (MODIFIER.isDown()) {
            int i = (int)Math.signum(direction);
            int newOffhandSelected = offhandSelected;
            for(newOffhandSelected -= i; newOffhandSelected < 0; newOffhandSelected += 9) {
            }

            while(newOffhandSelected >= 9) {
                newOffhandSelected -= 9;
            }

            return trySwapOffhand(newOffhandSelected);
        }
        return false;
    }

}
