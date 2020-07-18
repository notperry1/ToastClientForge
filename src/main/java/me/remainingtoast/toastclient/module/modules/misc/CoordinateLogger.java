package me.remainingtoast.toastclient.module.modules.misc;

import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManifest;
import me.remainingtoast.toastclient.util.MessageUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@ModuleManifest(label = "CoordinateLogger", category = Module.Category.MISC, description = "Logs death coordinates", aliases = {}, hidden = false)
public class CoordinateLogger extends Module {

    private BlockPos l;

    @SubscribeEvent
    public void onDeath(LivingDeathEvent e) {
        if (e.getEntity() == mc.player) {
            setDeathLocation(mc.player.getPosition());
        }
    }

    @SubscribeEvent
    public void onSpawn(PlayerEvent.PlayerRespawnEvent e) {
        if (e.player == mc.player) {
            if (getDeathLocation() != null) {
                MessageUtil.sendMessage("You died at " + l.toString(), MessageUtil.Color.GREEN);
            }
        }
    }

    public BlockPos getDeathLocation() {
        return l;
    }

    public void setDeathLocation(BlockPos loc) {
        l = loc;
    }

}
