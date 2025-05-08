package com.imjustdoom.stopdropnroll;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin implements Listener {
    public static String PREFIX = "[SDNR]";
    public static TextColor TEXT_COLOR = TextColor.color(96, 179, 255);

    public Main() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        Config.init();

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            LiteralCommandNode<CommandSourceStack> buildCommand = Commands.literal("stopdropnroll")
                    .requires(sender -> sender.getSender().hasPermission("stopdropnroll.commands"))
                    .executes(ctx -> {
                        ctx.getSource().getSender().sendMessage(Component.text(PREFIX + " Doom's Stop Drop N Roll version " + getPluginMeta().getVersion(), TEXT_COLOR));
                        return Command.SINGLE_SUCCESS;
                    }).then(Commands.literal("reload").executes(ctx -> {
                        Config.init();
                        ctx.getSource().getSender().sendMessage(Component.text(PREFIX + " Doom's Stop Drop N Roll has been reloaded!", TEXT_COLOR));
                        return Command.SINGLE_SUCCESS;
                    }))
                    .build();
            commands.registrar().register(buildCommand, List.of("sdnr"));
        });

        Bukkit.getPluginManager().registerEvents(this, this);

        Metrics metrics = new Metrics(this, 25791);
    }

    @EventHandler
    public void onPlayerSneakToggle(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (event.isSneaking() && player.getFireTicks() > 0 && Math.random() < Config.CHANCE) {
            player.setFireTicks((int) (player.getFireTicks() * (1 - Config.REMOVE_PERCENTAGE)));
        }
    }

    private static Main INSTANCE;
    public static Main get() {
        return INSTANCE;
    }
}