package org.parabot.bbhunter.main;

/**
 * Created with IntelliJ IDEA.
 * User: Bautista
 * Date: 8/1/13
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */


import org.parabot.bbhunter.data.Var;
import org.parabot.bbhunter.gui.GUI;
import org.parabot.bbhunter.strategys.Buy;
import org.parabot.bbhunter.strategys.Catch;
import org.parabot.bbhunter.strategys.Login;
import org.parabot.bbhunter.strategys.Sell;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.events.MessageEvent;
import org.rev317.api.events.listeners.MessageListener;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

@ScriptManifest(author = "Bautista123", category = Category.OTHER, description = "Hunt implings, MAKE BANK.", name = "BBHunter", servers = {"CrisisX"}, version = 0.4)
public final class Main extends Script implements MessageListener,
        Paintable {

    public static ArrayList<Strategy> strategies = new ArrayList<Strategy>();


    static long startTime;
    static boolean start = false;
    private final Color color1 = new Color(153, 102, 0, 85);
    private final Color color2 = new Color(0, 0, 0);

    private final BasicStroke stroke1 = new BasicStroke(1);

    private final Font font1 = new Font("DFKai-SB", 1, 20);
    private final Font font2 = new Font("DFKai-SB", 1, 12);

    public static String perHour(int gained) {
        return formatNumber((int) ((gained) * 3600000D / (System
                .currentTimeMillis() - startTime)));
    }

    public static String formatNumber(int start) {
        DecimalFormat nf = new DecimalFormat("0.0");
        double i = start;
        if (i >= 1000000) {
            return nf.format((i / 1000000)) + "M";
        }
        if (i >= 1000) {
            return nf.format((i / 1000)) + "K";
        }
        return "" + start;
    }

    public static String runTime(long i) {
        DecimalFormat nf = new DecimalFormat("00");
        long millis = System.currentTimeMillis() - i;
        long hours = millis / (1000 * 60 * 60);
        millis -= hours * (1000 * 60 * 60);
        long minutes = millis / (1000 * 60);
        millis -= minutes * (1000 * 60);
        long seconds = millis / 1000;
        return nf.format(hours) + ":" + nf.format(minutes) + ":"
                + nf.format(seconds);
    }

    public boolean onExecute() {
        GUI g = new GUI();
        g.setVisible(true);
        Var.setGuiwait(true);
        while (Var.getGuiwait()) {
            sleep(50);
        }
        startTime = System.currentTimeMillis();
        LogArea.log("Script started.");
        strategies.add(new Buy());
        strategies.add(new Catch());
        strategies.add(new Sell());
        strategies.add(new Login());
        provide(strategies);
        return true;
    }

    public void onFinish() {
        LogArea.error("Script ended.");
    }


    @Override
    public void messageReceived(MessageEvent m) {
        if (m.getMessage().contains("You Caught an Impling!")) {
            Var.setCaught(Var.getCaught() + 1);
        }

    }

    @Override
    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.setColor(color1);
        g.fillRect(384, 3, 132, 141);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRect(384, 3, 132, 141);
        g.setFont(font1);
        g.drawString("BBHunter", 409, 23);
        g.setFont(font2);
        g.drawString("Time: " + runTime(startTime), 386, 40);
        g.drawString("Caught: " + Var.getCaught() + "(" + perHour(Var.getCaught()) + ")", 385, 68);
        g.drawString("Profit: " + Var.getProfit() + "(" + perHour(Var.getProfit()) + ")", 385,
                103);
        g.drawString("Status: " + Var.getStatus(), 387, 131);
    }


}