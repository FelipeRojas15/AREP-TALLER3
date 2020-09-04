package edu.escuelaing.arep;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class HttpServer {

    public static void main(String[] args) {
        while (true) {
            ServerSocket serverSocket = null;
            Socket clientSocket = null;
            try {
                socketPrepare(serverSocket, clientSocket);
            } catch (IOException ex) {
                Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void socketPrepare(ServerSocket serverSocket, Socket clientSocket) throws IOException {
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("No se logro escuchar por el puerto"+getPort());
        }
        try {
            System.out.println("Listo para escuchar en el puerto "+getPort()+"...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed. ");
            System.exit(1);
        }
        answerRequest(clientSocket, serverSocket);
    }

    private static void answerRequest(Socket clientSocket, ServerSocket serverSocket) throws IOException {

        while (!clientSocket.isClosed()) {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, totalInput = "";
            while ((inputLine = in.readLine()) != null) {
                totalInput = totalInput + "\n" + inputLine;
                System.out.println("Received: " + inputLine);
                if (inputLine.contains("GET")) {
                    String[] tempArray = inputLine.split(" ");
                    String path = System.getProperty("user.dir") + "/src/main/Resources" + tempArray[1];
                    BufferedReader br = null;

                    try {
                        br = new BufferedReader(new FileReader(path));

                    } catch (Exception e) {
                        out.println("HTTP/1.1 404 Not Found");
                        out.println("Content-Type: text/html");
                        System.out.println("Not found");
                        e.printStackTrace();
                    }
                    if (path.contains(".html")) {
                        out.write("HTTP/1.1 200 OK");
                        out.println("Content-Type: text/html");
                        out.println();
                        String temp = br.readLine();
                        while (temp != null) {
                            out.write(temp);
                            temp = br.readLine();
                        }

                        br.close();
                    } else if (path.contains(".png")) {
                        out.write("HTTP/1.1 200 OK");
                        out.println("Content-Type: image/png");
                        out.println();
                        BufferedImage image = ImageIO
                                .read(new File(System.getProperty("user.dir") + "/src/main/Resources" + tempArray[1]));
                        ImageIO.write(image, "PNG", clientSocket.getOutputStream());

                    }

                    out.close();
                }
                if (!in.ready()) {
                    break;
                }
            }
            in.close();
        }
        clientSocket.close();
        serverSocket.close();
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000;
    }
} 

