package org.parabot.bbhunter.strategys;

import org.parabot.bbhunter.data.Var;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Game;
import org.rev317.api.methods.Interfaces;
import org.rev317.api.wrappers.hud.Interface;

/**
 * Created with IntelliJ IDEA.
 * User: Bautista
 * Date: 8/1/13
 * Time: 6:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Login implements Strategy {


    public boolean activate() {
        final Interface logScreen = Interfaces.getInterface(15944);
        return !Game.isLoggedIn() && !logScreen.isVisible()
                || Game.isLoggedIn();
    }

    public void execute() {
        try {
            Var.setStatus("Logged out.");
            if (!Game.isLoggedIn()) {
                Mouse.getInstance().click(365, 345, true);
                Time.sleep(5000);
                Var.setStatus("Logging in.");
            }
        } catch (Exception exx) {
            Var.setStatus("Log is null");
        }
    }


}
