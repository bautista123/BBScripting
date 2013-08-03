package org.parabot.bbhunter.strategys;

import org.parabot.bbhunter.data.Constants;
import org.parabot.bbhunter.data.Var;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.*;
import org.rev317.api.methods.Menu;
import org.rev317.api.wrappers.hud.Item;
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
public class Sell implements Strategy {

    final Npc[] buyer = Npcs.getNearest(Constants.shopkeeper);
    final Npc Buyer = buyer[0];
    final Player me = Players.getLocal();

    public boolean activate() {
        if (Game.isLoggedIn() && !me.isWalking()
                && Inventory.getCount(Constants.impJarArray) >= 1
                && me.getAnimation() == -1 && Inventory.getCount(Constants.jar) == 0
                ) {
            Var.setGo(true);
            return true;
        } else {
            return false;
        }
    }

    public void execute() {
        try {
            if (Buyer != null) {
                if (Game.isLoggedIn() && !Buyer.isOnScreen()) {
                    Camera.turnTo(Buyer);
                    Time.sleep(600);
                } else if (Game.isLoggedIn() && Buyer.isOnScreen()) {
                    while (Var.getGo()) {
                        Var.setStatus("Selling.");
                        Buyer.interact("Talk-to");
                        Time.sleep(1000);
                        Camera.setPitch(true);
                        Var.setGo(false);
                    }
                    if (Game.isLoggedIn()
                            && Inventory.getItems(Constants.impJarArray) != null
                            && !(Interfaces.getOpenInterfaceId() == -1)) {
                        for (Item i : Inventory.getItems(Constants.impJarArray)) {
                            i.interact("Sell 10");
                            if (Game.isLoggedIn()
                                    && Inventory.getCount(Constants.impJarArray) == 0
                                    && !(Interfaces.getOpenInterfaceId() == -1)) {
                                Menu.interact("Close", new Point(488, 22));
                                Time.sleep(1000);
                            }
                        }
                    }
                }
            }
        } catch (Exception fuckAgain) {
            Var.setStatus("Buyer is null.");
        }
    }
}

