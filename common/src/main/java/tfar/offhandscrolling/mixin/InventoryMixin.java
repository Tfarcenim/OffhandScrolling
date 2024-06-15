package tfar.offhandscrolling.mixin;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.offhandscrolling.client.ModClient;

@Mixin(Inventory.class)
public class InventoryMixin {


    @Shadow @Final public Player player;

    @Inject(method = "swapPaint",at = @At("HEAD"),cancellable = true)
    private void preventScrolling(double $$0, CallbackInfo ci) {
        if (player.level().isClientSide) {
            boolean handled = ModClient.handleScroll((Inventory) (Object)this,$$0);
            if (handled) ci.cancel();
        }
    }

}
