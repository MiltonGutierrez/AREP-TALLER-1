package edu.escuelaing.arep.taller1;

import edu.escuelaing.arep.taller1.Controller.NoteController;
import edu.escuelaing.arep.taller1.Controller.NoteControllerImpl;
import edu.escuelaing.arep.taller1.Services.NoteServicesImpl;

import java.net.*;
import java.io.*;

public class HttpServer {

    public static final int PORT = 35000;
    public static final String WEB_ROOT = "target/classes/webroot";
    private static String INDEX_PAGE_URI = "/notes.html";
    private static boolean RUNNING = true;
    private static final NoteController noteController = new NoteControllerImpl(new NoteServicesImpl());
    
    public static void startServer() {
        RUNNING = true;
    }

    public static void stopServer() {
        RUNNING = false;
    }

    public static void setIndexPageUri(String uri) {
        INDEX_PAGE_URI = uri;
    }

    public static void runServer() throws IOException, URISyntaxException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (RUNNING) {
            Socket clientSocket = null;
            clientSocket = serverSocket.accept();

            if (clientSocket != null) {
                handleRequests(clientSocket);
            }
        }
        serverSocket.close();
    }

    private static void handleRequests(Socket clientSocket) throws IOException, URISyntaxException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedOutputStream dataOut = new BufferedOutputStream(clientSocket.getOutputStream());

        String request = in.readLine();
        String[] parts = request.split(" ");
        String httpVerb = parts[0];
        String resource = parts[1].equals("/") ? INDEX_PAGE_URI : parts[1];
        printRequestHeaders(in);

        if (httpVerb.equals("GET") && !resource.startsWith("/app")) {
            handleGetRequests(resource, out, dataOut);
        } else if(httpVerb.equals("GET") && resource.startsWith("/app")){
            handleAppGetRequests(resource, out);

        } else if (httpVerb.equals("POST") && resource.startsWith("/app")) {
            HandleAppPostRequests(resource, out);
        } else {
            out.println("HTTP/1.1 400 Bad Request");
            out.println("Content-Type: text/html");
            out.println();
            out.println("<html><body><h1>400 Bad Request</h1></body></html>");
            out.flush();
        }
        out.close();
        in.close();
        clientSocket.close();
    }

    private static void handleAppGetRequests(String resource, PrintWriter out) throws IOException {
        String response = noteController.getNotes();
        out.print(response);
    }

    private static void HandleAppPostRequests(String resource, PrintWriter out) throws IOException {
        String response = noteController.addNote();
        out.print(response);
    }


    private static void handleGetRequests(String requestedResource, PrintWriter out, BufferedOutputStream dataOut) throws IOException {
        String contentType = getContentType(requestedResource);
        File resource = new File(WEB_ROOT, requestedResource);
        if (resource.exists() && !resource.isDirectory()) {
            int resourceLength = (int) resource.length();
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: " + contentType);
            out.println("Content-Length: " + resourceLength);
            out.println();
            out.flush();
            byte[] fileBytes = readBytesFromFile(resource, resourceLength);
            dataOut.write(fileBytes);
            dataOut.flush();
        } else {
            out.println("HTTP/1.1 404 Not Found");
            out.println("Content-Type: text/html");
            out.println();
            out.println("<html><body><h1>404 Not Found</h1></body></html>");
            out.flush();
        }
    }

    private static String getContentType(String requestedResource) {
        if (requestedResource.endsWith(".html"))
            return "text/html";
        if (requestedResource.endsWith(".css"))
            return "text/css";
        if (requestedResource.endsWith(".js"))
            return "application/javascript";
        if (requestedResource.endsWith(".png"))
            return "image/png";
        if (requestedResource.endsWith(".jpg"))
            return "image/jpg";
        if (requestedResource.endsWith(".jpeg"))
            return "image/jpeg";
        return "text/plain";
    }

    private static byte[] readBytesFromFile(File file, int fileLength) throws IOException {
        byte[] fileBytes = new byte[fileLength];
        try (FileInputStream fileIn = new FileInputStream(file)) {
            fileIn.read(fileBytes);
        }
        return fileBytes;
    }

    private static void printRequestHeaders(BufferedReader in) throws IOException {
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Header: " + inputLine);
            if (!in.ready()) {
                break;
            }
        }
    }
}
