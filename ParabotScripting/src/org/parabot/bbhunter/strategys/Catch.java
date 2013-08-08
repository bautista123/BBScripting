package org.parabot.bbhunter.strategys;

import org.parabot.bbhunter.data.Constants;
import org.parabot.bbhunter.data.Var;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.*;
import org.rev317.api.wrappers.hud.Item;
import org.rev317.api.wrappers.hud.Tab;
import org.rev317.api.wrappers.interactive.Npc;
import org.rev317.api.wrappers.interactive.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Bautista
 * Date: 8/1/13
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Catch implements Strategy {


    final Npc[] imp = Npcs.getNearest(Var.getImpling());
    final Npc Imp = imp[0];
    final Player me = Players.getLocal();

    public boolean activate() {
        return Game.isLoggedIn() && Inventory.getItems(Constants.jar) != null
                && Inventory.getCount(Constants.jar) >= 1;
    }

    public void execute() {

        try {
            Var.setProfit(Var.getMoney() * Var.getCaught());
            if (Game.isLoggedIn() && Inventory.getItems(Constants.net) != null) {
                if (Game.isLoggedIn() && Tab.INVENTORY.isOpen()) {
                    for (Item i : Inventory.getItems(Constants.net)) {
                        i.interact("Wield");
                        Time.sleep(600);
                    }
                } else if (Game.isLoggedIn() && !Tab.INVENTORY.isOpen()) {
                    Tab.INVENTORY.open();
                    Time.sleep(200);
                }
            }
            if (Imp != null) {
                if (Game.isLoggedIn() && Inventory.getCount(Constants.net) == 0) {
                    if (Game.isLoggedIn() && Imp.isOnScreen()) {
                        Var.setStatus("Catching.");
                        Imp.interact("Catch");
                        if (Game.isLoggedIn() && !Camera.setPitch(true)) {
                            Camera.setPitch(true);
                        }
                    } else if (Game.isLoggedIn() && !Imp.isOnScreen()) {
                        Camera.turnTo(Imp);
                        Time.sleep(300);
                    }
                }
            }
        } catch (Exception fuck) {
            Var.setStatus("Imp is null.");
        }
    }
}


