package tfar.offhandscrolling;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import tfar.offhandscrolling.client.ModClientForge;

@Mod(OffhandScrolling.MOD_ID)
public class OffhandScrollingForge {
    
    public OffhandScrollingForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.

        if (FMLEnvironment.dist.isClient()) {
            ModClientForge.setup(bus);
        }
    
        // Use Forge to bootstrap the Common mod.
        OffhandScrolling.init();
        
    }
}