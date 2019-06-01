package us.artit.eskettit;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Iterator;

public class Eskettit extends JavaPlugin implements Listener, CommandExecutor {

    private static Eskettit plugin;

    @Override
    public void onEnable() {
        plugin = this;
        System.out.print("                                                                                                                                                   \n" +
                "                                                                                                                                                   \n" +
                "                                                                                                                                                   \n" +
                "                                                                                                                                                   \n" +
                "                                                                                                                                                   \n" +
                "                                                                                                                                                   \n" +
                "                                                                                                                                                   \n" +
                "EEEEEEEEEEEEEEEEEEEEEE                 kkkkkkkk                                     tttt               tttt            iiii          tttt          \n" +
                "E::::::::::::::::::::E                 k::::::k                                  ttt:::t            ttt:::t           i::::i      ttt:::t          \n" +
                "E::::::::::::::::::::E                 k::::::k                                  t:::::t            t:::::t            iiii       t:::::t          \n" +
                "EE::::::EEEEEEEEE::::E                 k::::::k                                  t:::::t            t:::::t                       t:::::t          \n" +
                "  E:::::E       EEEEEE    ssssssssss    k:::::k    kkkkkkk eeeeeeeeeeee    ttttttt:::::tttttttttttttt:::::ttttttt    iiiiiiittttttt:::::ttttttt    \n" +
                "  E:::::E               ss::::::::::s   k:::::k   k:::::kee::::::::::::ee  t:::::::::::::::::tt:::::::::::::::::t    i:::::it:::::::::::::::::t    \n" +
                "  E::::::EEEEEEEEEE   ss:::::::::::::s  k:::::k  k:::::ke::::::eeeee:::::eet:::::::::::::::::tt:::::::::::::::::t     i::::it:::::::::::::::::t    \n" +
                "  E:::::::::::::::E   s::::::ssss:::::s k:::::k k:::::ke::::::e     e:::::etttttt:::::::tttttttttttt:::::::tttttt     i::::itttttt:::::::tttttt    \n" +
                "  E:::::::::::::::E    s:::::s  ssssss  k::::::k:::::k e:::::::eeeee::::::e      t:::::t            t:::::t           i::::i      t:::::t          \n" +
                "  E::::::EEEEEEEEEE      s::::::s       k:::::::::::k  e:::::::::::::::::e       t:::::t            t:::::t           i::::i      t:::::t          \n" +
                "  E:::::E                   s::::::s    k:::::::::::k  e::::::eeeeeeeeeee        t:::::t            t:::::t           i::::i      t:::::t          \n" +
                "  E:::::E       EEEEEEssssss   s:::::s  k::::::k:::::k e:::::::e                 t:::::t    tttttt  t:::::t    tttttt i::::i      t:::::t    tttttt\n" +
                "EE::::::EEEEEEEE:::::Es:::::ssss::::::sk::::::k k:::::ke::::::::e                t::::::tttt:::::t  t::::::tttt:::::ti::::::i     t::::::tttt:::::t\n" +
                "E::::::::::::::::::::Es::::::::::::::s k::::::k  k:::::ke::::::::eeeeeeee        tt::::::::::::::t  tt::::::::::::::ti::::::i     tt::::::::::::::t\n" +
                "E::::::::::::::::::::E s:::::::::::ss  k::::::k   k:::::kee:::::::::::::e          tt:::::::::::tt    tt:::::::::::tti::::::i       tt:::::::::::tt\n" +
                "EEEEEEEEEEEEEEEEEEEEEE  sssssssssss    kkkkkkkk    kkkkkkk eeeeeeeeeeeeee            ttttttttttt        ttttttttttt  iiiiiiii         ttttttttttt  \n" +
                "                                                                                                                                                   \n" +
                "                                                                                                                                                   \n" +
                "                                                                                                                                                   \n" +
                "                                                                                                                                                   \n" +
                "                                                                                                                                                   \n" +
                "                                                                                                                                                   \n" +
                "                                                                                                                                                   ");

        this.getCommand("eskettit").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public static Eskettit getPlugin() {
        return plugin;
    }

    public static final ItemStack TNT = new ItemStack(Material.TNT);

    static {
        ItemMeta tntMeta = TNT.getItemMeta();
        tntMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Eskettit");
        TNT.setItemMeta(tntMeta);
    }

    @EventHandler
    public void esket(PlayerInteractEvent e) {
        if (e.getPlayer().getItemInHand().getType() == TNT.getType()) {
            TNTPrimed a = (TNTPrimed) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.PRIMED_TNT);
            a.setVelocity(e.getPlayer().getEyeLocation().getDirection());
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        p.setAllowFlight(true);
        p.setFlying(false);
    }

    @EventHandler
    public void esketDouble_jump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(true);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setVelocity(player.getLocation().getDirection().multiply(2.0).setY(2.0));
        player.playSound(player.getLocation(), Sound.LAVA_POP, 10.0f, 10.0f);
    }

    @EventHandler
    public void move(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE && player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        ArrayList<Block> blocks = new ArrayList();
        ArrayList<Block> dontexplode = new ArrayList();
        Iterator var4 = e.blockList().iterator();

        Block b;
        while (var4.hasNext()) {
            b = (Block) var4.next();
            blocks.add(b);
        }

        var4 = dontexplode.iterator();

        while (var4.hasNext()) {
            b = (Block) var4.next();
            e.blockList().remove(b);
            b.getState().update(true);
        }

        e.setYield(0.0F);
        GeschenkExplosion ge = new GeschenkExplosion(blocks);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, ge, 40L);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player p = (Player) commandSender;
        p.getInventory().addItem(TNT);
        return true;
    }
}