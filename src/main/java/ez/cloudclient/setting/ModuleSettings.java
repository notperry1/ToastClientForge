package ez.cloudclient.setting;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class ModuleSettings {

    public Map<String, Object> settings = new HashMap<>();

    public Object addSetting(String settingName, Object defaultValue) {
        return settings.put(settingName, defaultValue);
    }

//    public Object addBooleanSetting(String settingName, Boolean bool) {
//        return settings.put(settingName, bool);
//    }
//
//    public boolean getBooleanSetting(String settingName) {
//        return (boolean) settings.get(settingName);
//    }

//    public void setBooleanSetting(String settingName, Boolean bool) {
//        settings.replace(settingName, bool);
//    }

    public void setSetting(String settingName, Object newValue) {
        settings.replace(settingName, newValue);
    }

    public Object getSetting(String settingName) {
        return settings.get(settingName);
    }

}
