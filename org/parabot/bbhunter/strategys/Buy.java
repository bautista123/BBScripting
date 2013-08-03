package org.parabot.bbhunter.strategys;

import org.parabot.bbhunter.data.Constants;
import org.parabot.bbhunter.data.Var;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.*;
import org.rev317.api.methods.Menu;
import org.rev317.api.wrappers.interactive.Npc;
import org.rev317.api.wrappers.interactive.Player;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Bautista
 * Date: 8/1/13
 * Time: 8:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Buy implements Strategy {

    public boolean activate() {
        final Player me = Players.getLocal();


        return Game.isLoggedIn() && me.getAnimation() == -1
                && !me.isWalking() && Inventory.getCount(Constants.jar) == 0
                && Inventory.getCount(Constants.impJarArray) == 0;

    }


    public void execute() {
        try {
            final Npc[] seller = Npcs.getNearest(Constants.tamayu);
            final Npc Seller = seller[0];
            if (Game.isLoggedIn() && !Seller.isOnScreen()) {
                Camera.turnTo(Seller);
                Time.sleep(1000);
            } else if (Game.isLoggedIn() && Seller.isOnScreen()) {
                Seller.interact("Trade");
                Time.sleep(1000);
                Var.setStatus("Buying.");
                if (Game.isLoggedIn() && Inventory.getCount() == 28
                        && Inventory.getItems(Constants.jar) != null
                        && !(Interfaces.getOpenInterfaceId() == -1)) {
                    Menu.interact("Close", new Point(488, 22));
                    Time.sleep(1000);
                } else if (Game.isLoggedIn() && Inventory.getCount() < 28
                        && !(Interfaces.getOpenInterfaceId() == -1)) {
                    Menu.interact("Buy 200", new Point(95, 65));
                    Time.sleep(1000);
                    Menu.interact("Close", new Point(488, 22));
                    Time.sleep(1000);
                }
            }
        } catch (Exception fuckkk) {
            Var.setStatus("Seller is null.");
        }
    }
}


