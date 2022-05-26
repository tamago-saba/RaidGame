package com.github.tsuoihito.raidgame;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RGCommandExecutor implements TabExecutor {

    private final RaidGame plugin;

    public RGCommandExecutor(RaidGame plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!command.getName().equalsIgnoreCase("raidgame")) {
            return true;
        }

        if (args.length == 0) {
            return true;
        }

        if (args[0].equalsIgnoreCase("setbase")) {

            plugin.setRgBase(plugin.getServer().getPlayer(sender.getName()).getLocation());

        }

        if (args[0].equalsIgnoreCase("start")) {

            if (args.length < 2) {
                return true;
            }

            if (!plugin.getRgBase().isPresent()) {
                return true;
            }

            if (!plugin.getTeamManager().getTeam(args[1]).isPresent()) {
                return true;
            }

            plugin.startGame(args[1]);

            for (Player player : plugin.getServer().getOnlinePlayers()) {

                if (plugin.getTeamManager().isNameInTeam(player.getName(), args[1])) {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.teleport(plugin.getRgBase().get());
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, 100, 5));
                } else {
                    player.setGameMode(GameMode.SPECTATOR);
                }

            }

        }

        if (args[0].equalsIgnoreCase("stop")) {

            plugin.stopGame();
            plugin.getServer().getOnlinePlayers().forEach(p -> p.setGameMode(GameMode.SURVIVAL));

        }

        if (args[0].equalsIgnoreCase("createteam")) {

            if (args.length < 2) {
                return true;
            }

            plugin.getTeamManager().addTeam(args[1]);
        }

        if (args[0].equalsIgnoreCase("deleteteam")) {

            if (args.length < 2) {
                return true;
            }

            plugin.getTeamManager().removeTeam(args[1]);
        }

        if (args[0].equalsIgnoreCase("showteams")) {

            sender.sendMessage(plugin.getTeamManager().getTeamNameList().toString());

        }

        if (args[0].equalsIgnoreCase("addmember")) {

            if (args.length < 3) {
                return true;
            }

            for (String arg : Arrays.asList(args).subList(2, args.length)) {
                plugin.getTeamManager().addTeamMember(args[1], arg);
            }

        }

        if (args[0].equalsIgnoreCase("removemember")) {

            if (args.length < 3) {
                return true;
            }

            for (String arg : Arrays.asList(args).subList(2, args.length)) {
                plugin.getTeamManager().removeTeamMember(args[1], arg);
            }

        }

        if (args[0].equalsIgnoreCase("showmembers")) {

            if (args.length < 2) {
                return true;
            }

            plugin.getTeamManager().getTeam(args[1]).ifPresent(t -> sender.sendMessage(t.getMembers().toString()));

        }

        if (args[0].equalsIgnoreCase("showresult")) {

            if (args.length < 2) {
                return true;
            }

            plugin.getGameResultManager().getGameResult(args[1]).ifPresent(g -> sender.sendMessage(plugin.getMessageData().getTeamResultMessage(g)));

        }

        if (args[0].equalsIgnoreCase("showtotalresult")) {

            sender.sendMessage(plugin.getMessageData().getTotalResultMessage(plugin.getGameResultManager().getSortedGameResults()));

        }

        if (args[0].equalsIgnoreCase("result")) {

            plugin.getServer().getOnlinePlayers().forEach(p -> p.sendMessage(plugin.getMessageData().getTotalResultMessage(plugin.getGameResultManager().getSortedGameResults())));

        }

        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            return Stream.of(
                    "setbase",
                    "start",
                    "stop",
                    "createteam",
                    "deleteteam",
                    "showteams",
                    "addmember",
                    "removemember",
                    "showmembers",
                    "showresult",
                    "showtotalresult",
                    "result").filter(c -> c.toLowerCase().startsWith(args[0].toLowerCase())).collect(Collectors.toList());
        }

        if (args.length == 2) {
            if (Stream.of("start", "deleteteam", "addmember", "removemember", "showmembers", "showresult").anyMatch(c -> c.equalsIgnoreCase(args[0]))) {
                return plugin.getTeamManager().getTeamNameList().stream().filter(t -> t.toLowerCase().startsWith(args[1].toLowerCase())).collect(Collectors.toList());
            }
        }

        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("addmember")) {
                return plugin.getServer().getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
            }
            if (args[0].equalsIgnoreCase("removemember")) {
                return plugin.getTeamManager().getTeam(args[1]).isPresent() ? plugin.getTeamManager().getTeam(args[1]).get().getMembers().stream().filter(m -> m.toLowerCase().startsWith(args[2].toLowerCase())).collect(Collectors.toList()) : new ArrayList<>();
            }
        }

        return new ArrayList<>();

    }
}
