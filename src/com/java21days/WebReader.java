package com.java21days;

import javax.swing.*;
import java.net.*;
import java.io.*;

public class WebReader extends JFrame {
    JTextArea box = new JTextArea("Pobieranie danych...");

    public WebReader() {
        super("Aplikacja pobierania danych");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        JScrollPane pane = new JScrollPane(box);
        add(pane);
        setVisible(true);
    }

    void getData(String address) throws MalformedURLException {
        setTitle(address);
        URL page = new URL(address);
        StringBuilder text = new StringBuilder();
        try {
            HttpURLConnection conn = (HttpURLConnection)
                page.openConnection();
            conn.connect();
            InputStreamReader in = new InputStreamReader(
                (InputStream) conn.getContent());
            BufferedReader buff = new BufferedReader(in);
            box.setText("Pobieranie danych ...");
            String line;
            do {
                line = buff.readLine();
                text.append(line);
                text.append("\n");
            } while (line != null);
            box.setText(text.toString());
        } catch (IOException ioe) {
            System.out.println("Błąd wejścia-wyjścia:" + ioe.getMessage());
        }
    }

    public static void main(String[] arguments) {
        if (arguments.length < 1) {
            System.out.println("Użycie: java WebReader url");
            System.exit(1);
        }
        try {
            WebReader app = new WebReader();
            app.getData(arguments[0]);
        } catch (MalformedURLException mue) {
            System.out.println("Błędny URL: " + arguments[0]);
        }
    }
}
