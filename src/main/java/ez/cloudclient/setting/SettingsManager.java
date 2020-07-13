package ez.cloudclient.setting;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ez.cloudclient.CloudClient;
import ez.cloudclient.module.Module;
import ez.cloudclient.module.ModuleManager;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static ez.cloudclient.CloudClient.CLOUDCLIENT_CONFIGFILE;

public class SettingsManager {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    File configFile = new File(CLOUDCLIENT_CONFIGFILE);

    public Map<String, ModuleSettings> readSettings() {
        Map<String, ModuleSettings> settingsArray = new HashMap<>();
        if (configFile.exists() && configFile.isFile()) {
            try {
                CloudClient.log.info("Reading config file...");
                settingsArray = gson.fromJson(new FileReader(configFile), new TypeToken<Map<String, ModuleSettings>>(){}.getType());
                CloudClient.log.info("Successfully read config file.");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                CloudClient.log.fatal("Could not read config file.");
            }
        } else {
            try {
                CloudClient.log.info("Creating config file...");
                FileWriter fw = new FileWriter(configFile);
                gson.toJson(settingsArray, fw);
                fw.flush();
                fw.close();
                CloudClient.log.info("Created config file.");
            } catch (IOException e) {
                e.printStackTrace();
                CloudClient.log.fatal("Could not create config file.");
            }
        }
        return settingsArray;
    }

    public void writeSettings(Map<String, ModuleSettings> settingsArray) {
        try {
            CloudClient.log.info("Writing config file");
            FileWriter fw = new FileWriter(configFile);
            gson.toJson(settingsArray, fw);
            fw.flush();
            fw.close();
            CloudClient.log.info("Wrote config file.");
        } catch (IOException e) {
            e.printStackTrace();
            CloudClient.log.fatal("Could not write config file.");
        }
    }

    public void updateSettings() {
        Map<String, ModuleSettings> settingsArray = new HashMap<>();
        for (Module module : ModuleManager.modules) {
            settingsArray.put(module.getName(), module.getSettings());
        }
        writeSettings(settingsArray);
    }

    public void loadSettings() {
        Map<String, ModuleSettings> settingsArray = readSettings();
        for (Module module : ModuleManager.modules) {
            String moduleName = module.getName();
            try {
                if (settingsArray.containsKey(moduleName)) {
                    module.setSettings(settingsArray.get(moduleName));
                } else {
                    module.registerSettings();
                }
            } catch (NullPointerException npe) {
                module.registerSettings();
            }
        }
        updateSettings();
    }
}