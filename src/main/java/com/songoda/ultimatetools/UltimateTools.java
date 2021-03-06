package com.songoda.ultimatetools;

import com.songoda.core.SongodaCore;
import com.songoda.core.SongodaPlugin;
import com.songoda.core.commands.CommandManager;
import com.songoda.core.compatibility.CompatibleMaterial;
import com.songoda.core.configuration.Config;
import com.songoda.core.locale.Locale;
import com.songoda.ultimatetools.commands.CommandGiveBook;
import com.songoda.ultimatetools.enchant.EnchantManager;
import com.songoda.ultimatetools.listeners.BlockListeners;
import com.songoda.ultimatetools.listeners.EntityListeners;
import com.songoda.ultimatetools.listeners.InteractListeners;
import com.songoda.ultimatetools.listeners.InventoryListeners;
import com.songoda.ultimatetools.listeners.VanillaEnchantListener;
import com.songoda.ultimatetools.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import java.util.List;

public class UltimateTools extends SongodaPlugin {
    private static UltimateTools INSTANCE;

    private EnchantManager enchantManager;

    public static UltimateTools getInstance() {
        return INSTANCE;
    }

    @Override
    public void onPluginLoad() {
        INSTANCE = this;
    }

    @Override
    public void onPluginEnable() {
        // Run Songoda Updater
        SongodaCore.registerPlugin(this, 580, CompatibleMaterial.ENCHANTED_BOOK);

        // Setup Config
        Settings.setupConfig();
        this.setLocale(Settings.LANGUGE_MODE.getString(), false);

        // Register commands
        CommandManager commandManager = new CommandManager(this);
        commandManager.addMainCommand("UTO")
                .addSubCommands(new CommandGiveBook(this));

        this.enchantManager = new EnchantManager(this).load();

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new InteractListeners(this), this);
        pluginManager.registerEvents(new BlockListeners(this), this);
        pluginManager.registerEvents(new EntityListeners(this), this);
        pluginManager.registerEvents(new InventoryListeners(this), this);
        pluginManager.registerEvents(new VanillaEnchantListener(this), this);
    }

    @Override
    public void onPluginDisable() {
    }

    @Override
    public void onDataLoad() {
    }

    @Override
    public void onConfigReload() {
    }

    @Override
    public List<Config> getExtraConfig() {
        return null;
    }

    public EnchantManager getEnchantManager() {
        return enchantManager;
    }

    public Locale getLocale() {
        return locale;
    }
}
