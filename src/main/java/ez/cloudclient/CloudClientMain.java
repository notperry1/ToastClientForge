package ez.cloudclient;

import ez.cloudclient.CloudClient.Events.EventProcessor;
import ez.cloudclient.CloudClient.Login.LoginGUI;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.io.IOException;

@Mod(modid = CloudClientMain.MODID, name = CloudClientMain.NAME, version = CloudClientMain.VERSION)
public class CloudClientMain {
    public static final String MODID = "cloudclient";
    public static final String NAME = "Cloud Client";
    public static final String VERSION = "v1.1";
    public static final String FULLNAME = "Cloud Client " + VERSION;

    protected final static Minecraft mc = Minecraft.getMinecraft();

    private static Logger logger;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        try {
            LoginGUI.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MinecraftForge.EVENT_BUS.register(new EventProcessor());
        ModuleManager.INSTANCE.init();
        System.out.println(NAME + " " + VERSION + " on Top.");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Display.setTitle(NAME + " " + VERSION);
    }
}
