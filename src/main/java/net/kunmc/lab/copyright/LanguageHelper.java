package net.kunmc.lab.copyright;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Locale;

public enum LanguageHelper {

    ENGLISH("English", null),
    JAPANESE("日本語", "japanese.yml");

    private String langName;
    private String fileName;

    LanguageHelper(String langName, String fileName) {
        this.fileName = fileName;
        this.langName = langName;
    }

    public String byMaterial(Material material) {
        if (fileName == null) {
            return material.name();
        } else {
            Configuration config = YamlConfiguration.loadConfiguration(new File(System.getProperty("user.dir") + File.separator + "lang", fileName));
            if (config.contains(material.toString().toLowerCase(Locale.ROOT))) {
                return config.getString(material.toString().toLowerCase(Locale.ROOT));
            } else {
                return material.name();
            }
        }
    }

    public String lang() {
        return langName;
    }

}
