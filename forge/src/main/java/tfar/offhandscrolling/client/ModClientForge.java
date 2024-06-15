package tfar.offhandscrolling.client;

import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModClientForge {

    public static void setup(IEventBus bus) {
        bus.addListener(ModClientForge::keybinds);
    }

    static void keybinds(RegisterKeyMappingsEvent event) {
        ModClient.keyBinds();
    }

}
