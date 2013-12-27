package org.parabot.interfacefinder;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.LoopTask;
import org.rev317.api.methods.Interfaces;
import org.rev317.api.wrappers.hud.Interface;
 

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
 
@ScriptManifest(author = "Paradox", category = Category.OTHER, description = "Interface viewer", name = "IViewer", servers = { "EVERY SERVER?" }, version = 0.1)
 
public class Viewer extends Script implements Paintable, LoopTask {
 
 
    private JFrame frame;
    private JList<String> list;
    private int selected;
 
    private void setupList() {
    	LogArea.log("starting setup");
        Interfaces.getOpenInterfaceId();
        try {
        	LogArea.log("first try");
            list = new JList<>();
 
            list.addListSelectionListener(new ListSelectionListener() {
 
                @Override
                public void valueChanged(ListSelectionEvent event) {
                    String s = list.getSelectedValue();
                    String index = s.substring("Interface ".length());
 
                    if (getSelected() != Integer.parseInt(index)) {
                        setSelected(Integer.parseInt(index));
 
                        try {
                        	LogArea.log("second try");
                            final Object c = Interfaces.getOpenInterface();
                            final Field f = c.getClass().getDeclaredField("openInterfaceID");
                            f.setAccessible(true);
                            //f.set(Client.get().getInstance(), getSelected());
 
                        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException e) {
                        	LogArea.log("failed second try");
                        }
                    }
                }
            });
 
            }
        catch (Exception e) {
        	LogArea.log("failed first try");
        }
 
 
 
 
 
            List<String> found = new ArrayList<>();
 
            for (int i = 0; ; i++) {
            	LogArea.log("for loop");
                Interface widget = Interfaces.get(i);
                if (widget.getChildrenIds() != null) {
                    if (widget.getParentId() != -1) {
                        if (!found.contains("Interface " + widget.getParentId())) {
                        	LogArea.log("Adding interface " + widget.getParentId() + ".");
                            System.out.println("Adding interface " + widget.getParentId() + ".");
                            found.add("Interface " + widget.getParentId());
                        }
                    }
                }
 
 
            String[] listData = found.toArray(new String[found.size()]);
 
 
                Arrays.sort(listData, new Comparator<String>() {
 
                    @Override
                    public int compare(String s1, String s2) {
                        int i1 = Integer.parseInt(s1.substring("Interface ".length()));
                        int i2 = Integer.parseInt(s2.substring("Interface ".length()));
                        if (i1 > i2) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                });
            }
 
        }
 
 
 
 
    private void setupFrame() {
    	LogArea.log("starting frame");
        SwingUtilities.invokeLater(new Runnable() {
 
            @Override
            public void run() {
                setupList();
 
                frame = new JFrame("Interface Explorer");
                getFrame().setVisible(true);
                getFrame().setLocationByPlatform(true);
                getFrame().setPreferredSize(new Dimension(256, 512));
                getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                getFrame().setLayout(new BorderLayout());
 
                getFrame().addWindowListener(new WindowAdapter() {
 
 
                });
 
                JLabel top = new JLabel("Select an interface to open.");
                JScrollPane scroll = new JScrollPane();
                scroll.setViewportView(getList());
 
                getFrame().add(top, BorderLayout.NORTH);
                getFrame().add(scroll, BorderLayout.CENTER);
 
                getFrame().pack();
                getFrame().setResizable(false);
                getFrame().setVisible(true);
            }
        });
    }
 
    @Override
    public boolean onExecute() {
        setupFrame();
        return true;
    }
 
 
 
 
 
    public JFrame getFrame() {
        return frame;
    }
 
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
 
    public JList<String> getList() {
        return list;
    }
 
    public void setList(JList<String> list) {
        this.list = list;
    }
 
    public int getSelected() {
        return selected;
    }
 
    public void setSelected(int selected) {
        this.selected = selected;
    }
 
    @Override
    public int loop() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
 
    @Override
    public void paint(Graphics graphics) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
