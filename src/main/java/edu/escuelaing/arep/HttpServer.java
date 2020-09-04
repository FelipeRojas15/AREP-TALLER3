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
public class HttpServer extends Thread {
    
    /**
     * Creation of client and server sockets 
     */
    public void run() {
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
    /**
     * Indicates the port through which the Http protocol can be displayed
     * @return int port
     */
    public int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000;
    }
    /**
     * Prepares the sockets and directs the response according to the customer's request
     * @param serverSocket ServerSocket 
     * @param clientSocket server's socket
     * @throws IOException 
     */
    private void socketPrepare(ServerSocket serverSocket, Socket clientSocket) throws IOException {
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("No se logro escuchar por el puerto" + getPort());
        }
        try {
            System.out.println("Listo para escuchar en el puerto " + getPort() + "...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed. ");
            System.exit(1);
        }
        answerRequest(clientSocket);
        
        clientSocket.close();
        serverSocket.close();
    }
    /**
     * Create the client's response according to the path and method entered
     * @param clientSocket server's socket
     * @throws IOException 
     */
    private void answerRequest(Socket clientSocket) throws IOException {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, totalInput = "";
        Request request = new Request();
        while ((inputLine = in.readLine()) != null) {
            request.setMethod(inputLine.split(" ")[0]);
            request.setPath(inputLine.split(" ")[1]);
            if (!in.ready() || inputLine.length() == 0) {
                break;
            }
            break;
        }
        if (!request.getMethod().equals("")) {
            createAnswer(request, new PrintStream(clientSocket.getOutputStream(), true));
        }

    }
    /**
     * Creates the response and the necessary headers for the server to respond to different requests
     * @param request request
     * @param out printstream
     * @throws IOException 
     */
    public void createAnswer(Request request, PrintStream out) throws IOException {

        if (request.getMethod().equals("GET")) {
            File file = new File("src/main/Resources" + request.getPath());
            if (file.exists()) {
                String path = System.getProperty("user.dir") + "/src/main/Resources" + request.getPath();
                BufferedReader br = null;
                synchronized (out) {
                    try {
                        br = new BufferedReader(new FileReader(path));
                    } catch (Exception e) {
                        out.println("HTTP/1.1 404 Not Found");
                        out.println("Content-Type: text/html");
                        System.out.println("Not found");
                    }
                    if (path.contains(".html")) {
                        out.print("HTTP/1.1 200 OK \r\n"+ "Content-Type: text/html" + "\r\n\r\n");
                        String temp = br.readLine();
                        while (temp != null) {
                            out.print(temp);
                            temp = br.readLine();
                        }
                        br.close();
                        out.close();
                    }
                    if (path.contains(".png")) {
                        out.print("HTTP/1.1 200 OK \r\n"+"Content-Type: image/png" + "\r\n\r\n");                        
                        BufferedImage image = ImageIO
                                .read(new File(System.getProperty("user.dir") + "/src/main/Resources" + request.getPath()));
                        ImageIO.write(image, "PNG", out);
                        out.close();
                    }
                    if (path.contains(".css")) {
                        out.print("HTTP/1.1 200 OK \r\n"+"Content-Type: text/css"+ "\r\n\r\n");
                        String temp = br.readLine();
                        while (temp != null) {
                            out.print(temp);
                            temp = br.readLine();
                        }
                        out.close();
                    }
                    if (path.contains(".js")) {
                        out.print("HTTP/1.1 200 OK \r\n"+"Content-Type: text/plain"+ "\r\n\r\n");
                        String temp = br.readLine();
                        while (temp != null) {
                            out.print(temp);
                            temp = br.readLine();

                        }
                        out.close();
                    }
                    out.close();
                }
            }else{
                out.print("HTTP/1.0 404 Not Found \r\n" + "Content-type: text/html" + "\r\n\r\n"+ "404 File not found");
                
            }
        }
    }

}
