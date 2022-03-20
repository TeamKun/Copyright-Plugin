package net.kunmc.lab.copyright;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public enum LanguageHelper {

    ENGLISH("English", "english.properties"),
    JAPANESE("日本語", "japanese.properties");

    private String langName;
    private String fileName;
    private Properties prop;

    LanguageHelper(String langName, String fileName) {
        this.fileName = fileName;
        this.langName = langName;
        this.prop = new Properties();
        try {
            prop.load(new FileInputStream(new File(System.getProperty("user.dir") + File.separator + "lang", langName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String byMaterial(Material material) {
        if (fileName == null) {
            return material.name();
        } else {
            String key = material.getTranslationKey();
            if (prop.contains(key)) {
                return prop.getProperty(key);
            } else {
                return material.name();
            }
        }
    }

    public String lang() {
        return langName;
    }

}
