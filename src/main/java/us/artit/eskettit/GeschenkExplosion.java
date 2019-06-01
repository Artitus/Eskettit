package us.artit.eskettit;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeschenkExplosion extends BukkitRunnable {
    List<BlockState> states;

    public GeschenkExplosion(ArrayList<BlockState> blocks) {
        this.states = blocks;
    }

    public GeschenkExplosion(List<Block> blocks) {
        this.states = new ArrayList<BlockState>();
        for (Block b : blocks) {
            this.states.add(b.getState());
        }
    }

    public void run() {
        this.regen();
    }

    public void regen() {
        for (BlockState state : this.states) {
            Bukkit.getScheduler().runTaskLater(Eskettit.getPlugin(), () -> state.update(true), (long) (new Random().nextInt(200) + 20));
        }
    }
}


