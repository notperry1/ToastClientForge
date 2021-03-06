package me.remainingtoast.toastclient.command.commands;

import me.remainingtoast.toastclient.command.Command;
import me.remainingtoast.toastclient.command.CommandManifest;
import me.remainingtoast.toastclient.util.MessageUtil;
import me.remainingtoast.toastclient.util.NumberUtil;

@CommandManifest(label = "FOV", description = "Change client FOV", aliases = {""}, usage = "fov <value>")
public class Fov extends Command {

    @Override
    public void onRun(final String[] args) {
        if (args.length > 0) {
            if (NumberUtil.isNumeric(args[0])) {
                if ((Float.parseFloat(args[0]) >= 150)) {
                    MessageUtil.sendMessage("Max 150, FOV Set to 150", MessageUtil.Color.RED);
                    mc.gameSettings.fovSetting = 150f;
                } else if ((Float.parseFloat(args[0]) < 10)) {
                    MessageUtil.sendMessage("Min 10, FOV Set to 10", MessageUtil.Color.RED);
                    mc.gameSettings.fovSetting = 10f;
                } else {
                    mc.gameSettings.fovSetting = Float.parseFloat(args[0]);
                    MessageUtil.sendMessage("Successfully set FOV to: " + mc.gameSettings.fovSetting, MessageUtil.Color.GREEN);
                }
            } else {
                switch (args[0].toLowerCase()) {
                    case "max":
                    case "m":
                        mc.gameSettings.fovSetting = 150f;
                        MessageUtil.sendMessage("Successfully set FOV to: " + mc.gameSettings.fovSetting, MessageUtil.Color.GREEN);
                        break;
                    case "normal":
                    case "n":
                    case "medium":
                        mc.gameSettings.fovSetting = 70f;
                        MessageUtil.sendMessage("Successfully set FOV to: " + mc.gameSettings.fovSetting, MessageUtil.Color.GREEN);
                        break;
                    case "quakepro":
                    case "quake":
                    case "q":
                        mc.gameSettings.fovSetting = 110f;
                        MessageUtil.sendMessage("Successfully set FOV to: " + mc.gameSettings.fovSetting, MessageUtil.Color.GREEN);
                        break;
                    case "low":
                    case "l":
                        mc.gameSettings.fovSetting = 30f;
                        MessageUtil.sendMessage("Successfully set FOV to: " + mc.gameSettings.fovSetting, MessageUtil.Color.GREEN);
                        break;
                    default:
                        MessageUtil.sendMessage("Invalid arguments, enter a number between 10 and 150 inclusive or a preset: Low, Normal, QuakePro, Max.", MessageUtil.Color.RED);
                        break;
                }
            }
        }
    }
}
