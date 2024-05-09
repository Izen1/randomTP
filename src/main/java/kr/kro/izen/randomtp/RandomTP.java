package kr.kro.izen.randomtp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class RandomTP extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().warning("""
                
                ------------------------------------------------------
                랜덤 텔레포트 플러그인 작동 !!!
                ------------------------------------------------------
                작동법 : /rtp <Player>
                """);
        getCommand("rtp").setExecutor(((sender, command, s, args) -> {
            if (!(sender instanceof Player player)) return true;
            if (args.length == 0) randomTPPlayer(player);
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) return true;
                randomTPPlayer(target);
            }
            return false;
        }));
    }

    public void randomTPPlayer(Player player) {
        Random random = new Random();

        int x = random.nextInt(20000) - 10000;
        int y = 0;
        int z = random.nextInt(20000) - 10000;

        Location randomLocation = new Location(player.getWorld(), x + 0.5, y, z + 0.5);
        y = randomLocation.getWorld().getHighestBlockYAt(randomLocation) + 1;
        randomLocation.setY(y);

        player.teleport(randomLocation);
        player.sendMessage(ChatColor.BLUE + "이동중입니다 !!");
    }
}
