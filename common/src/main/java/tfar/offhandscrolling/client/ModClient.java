package tfar.offhandscrolling.client;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.glfw.GLFW;

public class ModClient {

    public static final KeyMapping MODIFIER = new KeyMapping("modifier", GLFW.GLFW_KEY_LEFT_ALT,"Offhand Category");
    public static final KeyMapping SWAP_ABOVE = new KeyMapping("swap_above", GLFW.GLFW_KEY_LEFT_ALT,"Offhand Category");


    static boolean state;

    public static void keyBinds() {
        registerKeyBind(MODIFIER);
        registerKeyBind(SWAP_ABOVE);
    }

    public static void registerKeyBind(KeyMapping keyMapping) {
        Minecraft.getInstance().options.keyMappings = ArrayUtils.add(Minecraft.getInstance().options.keyMappings, keyMapping);
    }

    public static void handleKeybinds(Minecraft minecraft) {
        if (SWAP_ABOVE.consumeClick()) {
            state = !state;
            trySwapOffhand2(state);
        }
    }

    public static boolean trySwapOffhand2(boolean up) {
            Minecraft minecraft = Minecraft.getInstance();
            Player player = minecraft.player;
            if (player != null) {
                InventoryMenu menu = player.inventoryMenu;

                ItemStack offhandStack = player.getInventory().offhand.get(0);
                //swap from 0 to 1 for example

                if (up && offhandStack.isEmpty()) {
                    minecraft.gameMode.handleInventoryMouseClick(menu.containerId, player.getInventory().selected + 27, 40, ClickType.SWAP, player);
                } else if (!up && !offhandStack.isEmpty()) {
                    minecraft.gameMode.handleInventoryMouseClick(menu.containerId, player.getInventory().selected + 27, 40, ClickType.SWAP, player);
                }
                return true;
        }
        return false;
    }

    public static boolean handleScroll(Inventory inventory, double direction) {
        if (MODIFIER.isDown()) {
            int i = (int)Math.signum(direction);
            return trySwapOffhand2(i > 0);
        }
        return false;
    }

}
