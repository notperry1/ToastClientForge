package me.remainingtoast.toastclient;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRichPresence;
import me.remainingtoast.toastclient.util.MathUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;

public class DiscordPresence {

    private static final club.minnced.discord.rpc.DiscordRPC rpc;
    public static DiscordRichPresence presence;
    private static boolean connected;
    private static String details;
    private static String state;
    private static String gamemode;

    static {
        rpc = club.minnced.discord.rpc.DiscordRPC.INSTANCE;
        DiscordPresence.presence = new DiscordRichPresence();
        DiscordPresence.connected = false;
    }

    public static void start() {
        if (DiscordPresence.connected) return;
        DiscordPresence.connected = true;

        final DiscordEventHandlers handlers = new DiscordEventHandlers();
        DiscordPresence.rpc.Discord_Initialize(ToastClient.APP_ID, handlers, true, "");
        DiscordPresence.presence.startTimestamp = System.currentTimeMillis() / 1000L;

        /* update rpc normally */
        setRpcFromSettings();

        /* update rpc while thread isn't interrupted  */
        new Thread(DiscordPresence::setRpcFromSettingsNonInt, "Discord-RPC-Callback-Handler").start();
    }

    public static void end() {
        DiscordPresence.connected = false;
        DiscordPresence.rpc.Discord_Shutdown();
    }

    private static void setRpcFromSettingsNonInt() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                DiscordPresence.rpc.Discord_RunCallbacks();
                String separator = " | ";
                //First Line of RPC



                //Second Line of RPC
                state = "Loading";
                if (Minecraft.getMinecraft().isSingleplayer()) { gamemode = "Singleplayer"; }
                if (!Minecraft.getMinecraft().isSingleplayer()) { gamemode = "Multiplayer"; }
                if (Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) {
                    state = ToastClient.FULLVERSION + separator + "Main Menu";
                }
                if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null) {
                    if (Minecraft.getMinecraft().isSingleplayer()) {
                        if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null) {
                            details = Minecraft.getMinecraft().getSession().getUsername() + " is playing " + gamemode;
                            state = "Located at " + MathUtil.formatPlayerCoords(Minecraft.getMinecraft().player);
                        }
                    }
                    if (!Minecraft.getMinecraft().isSingleplayer() && Minecraft.getMinecraft().getCurrentServerData() != null) {
                        details = Minecraft.getMinecraft().getSession().getUsername() + " is playing " + gamemode;
                        state = "Connected to " + Minecraft.getMinecraft().getCurrentServerData().serverIP;
                    }
                }

                DiscordPresence.presence.details = details;
                DiscordPresence.presence.state = state;
                DiscordPresence.rpc.Discord_UpdatePresence(DiscordPresence.presence);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e3) {
                e3.printStackTrace();
            }
        }
    }

    private static void setRpcFromSettings() {
        DiscordPresence.presence.details = details;
        DiscordPresence.presence.state = state;
    }
}
