package com.java21days;

import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.util.*;

public class FingerServer {

    public FingerServer() {
        try {
            // Utwórz nieblokujący kanał gniazda serwerowego.
            ServerSocketChannel sock = ServerSocketChannel.open();
            sock.configureBlocking(false);

            // Ustaw hosta i port do monitorowania.
            InetSocketAddress server = new InetSocketAddress(
                "localhost", 79);
            ServerSocket socket = sock.socket();
            socket.bind(server);

            // Utwórz selektor i zarejestruj go dla kanału.
            Selector selector = Selector.open();
            sock.register(selector, SelectionKey.OP_ACCEPT);

            // Pętla nieskończona czekająca na połączenia od klientów.
            while (true) {
                // Czekaj na połączenie.
                selector.select();

                // Pobierz klucze selekcji dla czekających zdarzeń.
                Set keys = selector.selectedKeys();
                Iterator it = keys.iterator();

                // Obsłuż każdy klucz
                while (it.hasNext()) {

                    // Pobierz klucz i usuń go z iteracji.
                    SelectionKey sKey = (SelectionKey) it.next();

                    it.remove();
                    if (sKey.isAcceptable()) {

                        // Utwórz połączenie z klinetem.
                        ServerSocketChannel selChannel =
                            (ServerSocketChannel) sKey.channel();
                        ServerSocket sSock = selChannel.socket();
                        Socket connection = sSock.accept();

                        // Obsłuż żądanie Finger.
                        handleRequest(connection);
                        connection.close();
                    }
                }
             }
         } catch (IOException ioe) {
             System.out.println(ioe.getMessage());
         }
     }

    private void handleRequest(Socket connection)
        throws IOException {

        // Ustaw wejście i wyjście.
        InputStreamReader isr = new InputStreamReader (
            connection.getInputStream());
        BufferedReader is = new BufferedReader(isr);
        PrintWriter pw = new PrintWriter(new
            BufferedOutputStream(connection.getOutputStream()),
            false);

        // Wyślij pozdrowienie.
        pw.println("Serwer Finger");
        pw.flush();

        // Obsłuż dane wejściowe.
        String outLine = null;
        String inLine = is.readLine();

        if (inLine.length() > 0) {
            outLine = inLine;
        }
        readPlan(outLine, pw);

        // Czyszczenie.
        pw.flush();
        pw.close();
        is.close();
    }

    private void readPlan(String userName, PrintWriter pw) {
        try {
            FileReader file = new FileReader(userName + ".plan");
            BufferedReader buff = new BufferedReader(file);
            boolean eof = false;

            pw.println("\nNazwa użytkownika: " + userName + "\n");

            while (!eof) {
                String line = buff.readLine();

                if (line == null) {
                    eof = true;
                } else {
                   pw.println(line);
                }
            }

            buff.close();
        } catch (IOException e) {
            pw.println("Nie znaleziono użytkownika " + userName + ".");
        }
    }

    public static void main(String[] arguments) {
        FingerServer nio = new FingerServer();
    }
}
