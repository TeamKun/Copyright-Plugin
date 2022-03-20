package net.kunmc.lab.copyright;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

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
    // propertiesファイルを読み込むために使うプラグインフォルダのプラグイン名をここに入力する。
    // ex. %user.dir% / plugins / %pluginame% / lang / english.properties
    private final String pluginName = "Copyright";

    LanguageHelper(String langName, String fileName) {
        this.fileName = fileName;
        this.langName = langName;
        this.prop = new Properties();
        try {
            prop.load(new FileInputStream(new File(System.getProperty("user.dir") + File.separator + "plugins" + File.separator + pluginName + File.separator + "lang", langName)));
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
            } else if (this == ENGLISH) {
                return material.name();
            } else {
                return LanguageHelper.ENGLISH.byMaterial(material);
            }
        }
    }

    public String byEnchantment(Enchantment enchantment) {
        if (fileName == null) {
            return enchantment.getName();
        } else {
            String key = enchantment.getKey().getKey();
            if (prop.contains(key)) {
                return prop.getProperty(key);
            } else if (this == ENGLISH) {
                return enchantment.getName();
            } else {
                return LanguageHelper.ENGLISH.byEnchantment(enchantment);
            }
        }
    }

    public String byItemStack(ItemStack is) {
        if (fileName == null) {
            return is.getI18NDisplayName();
        } else {
            if (is.getItemMeta().hasDisplayName()) {
                return is.getI18NDisplayName();
            }
            String key = is.getTranslationKey();
            if (prop.contains(key)) {
                return prop.getProperty(key);
            } else if (this == ENGLISH) {
                return is.getI18NDisplayName();
            } else {
                return LanguageHelper.ENGLISH.byItemStack(is);
            }
        }
    }

    public String lang() {
        return langName;
    }

}
