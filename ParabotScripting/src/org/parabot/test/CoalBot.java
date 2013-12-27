package org.parabot.test;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayList;
 
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Calculations;
import org.rev317.api.methods.Camera;
import org.rev317.api.methods.Interfaces;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Players;
import org.rev317.api.methods.SceneObjects;
import org.rev317.api.wrappers.hud.Item;
import org.rev317.api.wrappers.scene.SceneObject;
import org.rev317.api.wrappers.scene.Tile;
import org.rev317.api.wrappers.walking.TilePath;
 
 
/**
 * @author Kmodos
 * @version 0.1
 * For non-commercial use only
 */
@ScriptManifest(author = "Kmodos", description = "Mines Coal in the Mining Guild and Banks", name = "CoalBot", servers = { "PkHonor" }, version = 1, category = Category.MINING)
public class CoalBot extends Script implements Paintable{
        private final ArrayList<Strategy> strats = new ArrayList<Strategy>();
        private final Tile[] tileArrayBank = {new Tile(3043,9746)};
        private final TilePath pathToBank = new TilePath(tileArrayBank);
        private final Color color1 = new Color(0, 0, 102);
        private final Color color2 = new Color(255, 255, 255);
        private final BasicStroke stroke1 = new BasicStroke(1);
        private final Font font1 = new Font("Arial", 1, 20);
        private final Font font2 = new Font("Arial", 1, 17);
        private final long startTime = System.currentTimeMillis();
        public static int coalMined = 0;
 
 
        @Override
        public boolean onExecute() {
                strats.add(new Mine());
                strats.add(new BankOre());
                strats.add(new WalkToBank());
                provide(strats);
                return true;  
        }
 
        @Override
        public void onFinish() {
 
        }    
        @Override
        public void paint(Graphics g1) {
                Graphics2D g = (Graphics2D)g1;
                g.setColor(color1);
                g.fillRoundRect(0, 220, 517, 116, 16, 16);
                g.setColor(color2);
                g.setStroke(stroke1);
                g.drawRoundRect(0, 220, 517, 116, 16, 16);
                g.setFont(font1);
                g.drawString("Kmodos' Coal Miner", 163, 241);
                g.setFont(font2);
                g.drawString("Run Time: " + runTime(startTime), 7, 265);
                g.drawString("Ore Mined: " + coalMined, 4, 308);
        }
 
 
        public String runTime(long i) {
                DecimalFormat nf = new DecimalFormat("00");
                long millis = System.currentTimeMillis() - i;
                long hours = millis / (1000 * 60 * 60);
                millis -= hours * (1000 * 60 * 60);
                long minutes = millis / (1000 * 60);
                millis -= minutes * (1000 * 60);
                long seconds = millis / 1000;
                return nf.format(hours) + ":" + nf.format(minutes) + ":" + nf.format(seconds);
        }
        /******************************************************************************************************************/
        /******************************************************************************************************************/
        public class Mine implements Strategy{
                SceneObject rock;
                @Override
                public boolean activate() {
                        for (SceneObject i : SceneObjects.getNearest(2096,2097)) {
                                if (i !=null
                                                && i.isOnScreen()
                                                && !Players.getLocal().isWalking()
                                                && !Players.getLocal().isInCombat()
                                                && (Players.getLocal().getAnimation() == -1 || Players.getLocal().getAnimation() == 1353)
                                                && !Inventory.isFull()
                                                && Players.getLocal().getAnimation() != 625 ){
                                        rock = i;
                                        return true;
                                }
                        }
                        return false;
                }
 
                @Override
                public void execute() {
                        Camera.turnTo(rock);
                        rock.interact("Mine Rocks");
                        Time.sleep(2000);
                }
        }
 
        public class BankOre implements Strategy{
                SceneObject BankObj;
                @Override
                public boolean activate() {
                        BankObj = SceneObjects.getNearest(9398)[0];
                        if (BankObj !=null
                                        && BankObj.isOnScreen()
                                        && !Players.getLocal().isWalking()
                                        && !Players.getLocal().isInCombat()
                                        && !(Interfaces.getOpenInterfaceId() == 23350)
                                        && Inventory.isFull()
                                        && (Calculations.distanceBetween(Players.getLocal().getLocation(), tileArrayBank[0]) < 5)){
                                return true;
                        }
                        return false;
                }
 
                @Override
                public void execute() {
                        Camera.turnTo(BankObj);
                        BankObj.interact("Deposit");
                        while(!(Interfaces.getOpenInterfaceId() == 23350)){
                                Time.sleep(1000);
                        }
                        CoalBot.coalMined += Inventory.getCount(453);
                        for(Item i: Inventory.getItems()){
                                i.interact("Store 1");
                        }
                        Time.sleep(1000);
                        Mouse.getInstance().click(new Point(490,30));
                }
        }
        public class WalkToBank implements Strategy{
                @Override
                public boolean activate() {
                        if(!Players.getLocal().isWalking()
                                        && !Players.getLocal().isInCombat()
                                        && !(Interfaces.getOpenInterfaceId() == 23350)
                                        && Inventory.isFull()){
                                return true;
                        }
                        return false;
                }
 
                @Override
                public void execute() {
                        pathToBank.traverse();
                        while(Players.getLocal().isWalking()){
                                Time.sleep(500);
                        }
                }
        }
       
}