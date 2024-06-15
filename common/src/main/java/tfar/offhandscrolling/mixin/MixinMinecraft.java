package tfar.offhandscrolling.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.offhandscrolling.client.ModClient;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    
    @Inject(at = @At("HEAD"), method = "handleKeybinds")
    private void init(CallbackInfo info) {
        ModClient.handleKeybinds((Minecraft)(Object)this);
    }
}