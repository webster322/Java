package com.java21days;

import javax.swing.*;

public class Subscriptions extends JFrame{
    String[] subs = { "Burningbird", "Freeform Goodness", "Ideoplex",
        "Inessential", "Interwingly", "Now This", "Rasterweb", "RC3",
        "whole Lotta Nothing", "Workbench" };
    JList<String> subList = new JList<>(subs);
    
    public Subscriptions(){
        super("Subskrypcje");
        setSize(150, 335);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JLabel subLabel = new JLabel("Subskrypcje RSS:");
        panel.add(subLabel);
        subList.setVisibleRowCount(8);
        JScrollPane scroller = new JScrollPane(subList);
        panel.add(scroller);
        add(panel);
        setVisible(true);
    }
    
    private static void setLookAndFeel(){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception exc){
            System.out.println(exc.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Subscriptions.setLookAndFeel();
        Subscriptions app = new Subscriptions();
    }
}