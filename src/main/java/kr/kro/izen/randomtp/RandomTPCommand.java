package kr.kro.izen.randomtp;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTPCommand extends BukkitCommand {
    protected RandomTPCommand(@NotNull String name) {
        super("rtp");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (args.length == 0) {
            randomTPPlayer(player);
        }
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) return true;
            randomTPPlayer(target);
        }
        return false;
    }
    public void randomTPPlayer(Player player) {
        World world = player.getWorld();
        Random random = new Random();
        List<Location> safeLoc = new ArrayList<>();

        int x = random.nextInt(20001) - 10000;
        int z = random.nextInt(20001) - 10000;
        int maxY = world.getHighestBlockYAt(x, z) + 1;
        int minY = world.getMinHeight();

        for (int y = minY; y <= maxY + 1; y++) {
            Location randomLocation = new Location(world, x + 0.5, y, z + 0.5);
            if (safePlace(randomLocation)) {
                safeLoc.add(randomLocation);
            }
        }
        if (!safeLoc.isEmpty()) {
            Location randomTp = safeLoc.get(random.nextInt(safeLoc.size()));
            player.teleport(randomTp);
            player.sendMessage(ChatColor.BLUE + "이동중입니다 !!");
        } else {
            player.sendMessage(ChatColor.RED + "이동할 좌표를 찾지 못했어요 ;ㅅ;");
        }
    }

    private boolean safePlace(Location location) {
        Block block = location.getBlock();
        Block upFace = block.getRelative(BlockFace.UP);
        Block downFace = block.getRelative(BlockFace.DOWN);

        boolean lava = block.getType() == Material.LAVA ||
                upFace.getType() == Material.LAVA ||
                downFace.getType() == Material.LAVA;

        return !block.getType().isSolid() && !upFace.getType().isSolid() && downFace.getType().isSolid() && !lava;
    }
}
