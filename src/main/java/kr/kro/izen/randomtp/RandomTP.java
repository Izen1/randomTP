package kr.kro.izen.randomtp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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
        Bukkit.getCommandMap().register("rtp", new RandomTPCommand("rtp"));
    }

}
