package com.github.tsuoihito.raidgame;

import com.github.tsuoihito.raidgame.objects.GameState;
import com.github.tsuoihito.raidgame.objects.Team;
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

            plugin.setGameState(new GameState(plugin.getTeamManager().getTeam(args[1]).get()));
            plugin.setInGame(true);

            for (Player player : plugin.getServer().getOnlinePlayers()) {
                if (plugin.getGameState().getTeam().getMembers().stream().anyMatch(player.getName()::equalsIgnoreCase)) {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.teleport(plugin.getRgBase().get());
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, 100, 5));
                }
            }

            for (Player player : plugin.getServer().getOnlinePlayers()) {
                if (plugin.getGameState().getTeam().getMembers().stream().noneMatch(player.getName()::equalsIgnoreCase)) {
                    player.setGameMode(GameMode.SPECTATOR);
                }
            }

            new SBScheduler(plugin).runTaskTimer(plugin, 0, 20);

        }

        if (args[0].equalsIgnoreCase("stop")) {

            plugin.setInGame(false);

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

            sender.sendMessage(plugin.getTeams().stream().map(Team::getTeamName).collect(Collectors.toList()).toString());

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

            plugin.getTeamManager().getTeam(args[1]).ifPresent(value -> sender.sendMessage(value.getMembers().toString()));

        }

        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> playerName = new ArrayList<>();
        plugin.getServer().getOnlinePlayers().forEach(p -> playerName.add(p.getName()));

        return playerName;

    }
}
