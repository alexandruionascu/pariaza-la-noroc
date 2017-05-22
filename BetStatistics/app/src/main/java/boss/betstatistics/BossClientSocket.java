package boss.betstatistics;

import java.io.*;
import java.net.Socket;

public class BossClientSocket {


    private final Socket serverSocket;
    private final InputStream clientInputStream;
    private final BufferedReader serverReader;
    private final PrintWriter serverWriter;
    private final OutputStream clientOutputStream;


    public boolean debugMode = true;

    public BossClientSocket(String hostName, int port) throws IOException {

        printDebugMessages("Waiting for server...");
        this.serverSocket = new Socket(hostName, port);

        printDebugMessages("Server connected.");

        this.clientInputStream = this.serverSocket.getInputStream();

        this.serverReader = new BufferedReader(
                new InputStreamReader(
                        this.clientInputStream
                ));


        this.clientOutputStream = this.serverSocket.getOutputStream();
        this.serverWriter = new PrintWriter(this.clientOutputStream, true);

    }


    @Deprecated
    public BufferedReader getServerReader() throws IOException {
        return this.serverReader;
    }

    @Deprecated
    public PrintWriter getServerWriter() throws IOException {
        return this.serverWriter;
    }

    public InputStream getClientInputStream() {
        return this.clientInputStream;
    }

    public OutputStream getClientOutputStream() {
        return this.clientOutputStream;
    }


    public void sendMessageLine(String message) {

        PrintWriter writer = this.serverWriter;
        writer.println(message);
        writer.flush();
    }

    public String readMessageLine() throws IOException {

        printDebugMessages("Reading message from server...");

        BufferedReader reader = this.serverReader;
        String message = reader.readLine();
        printDebugMessages("Message received from server.");
        return message;
    }


    public void sendByteArray(byte[] myByteArray) throws IOException {
        OutputStream out = this.getClientOutputStream();
        DataOutputStream dos = new DataOutputStream(out);

        dos.writeInt(myByteArray.length);
        if (myByteArray.length > 0) {
            dos.write(myByteArray, 0, myByteArray.length);
        }
    }

    public byte[] readByteArray() throws IOException {
        InputStream in = this.getClientInputStream();
        DataInputStream dis = new DataInputStream(in);

        int len = dis.readInt();
        byte[] data = new byte[len];
        if (len > 0) {
            dis.readFully(data);
        }

        return data;
    }

    public void closeClient() throws IOException {
        this.serverSocket.close();
        printDebugMessages("Client closed.");
    }

    private void printDebugMessages(String message) {

        if (this.debugMode) {
            System.out.println(message);
        }
    }

    @Override
    public String toString() {
        return this.serverSocket.toString();
    }

    public static void main(String[] args) throws Exception {
        BossClientSocket client = new BossClientSocket("localhost", 4321);
        while (true) {
            //client.sendMessageLine("CLIENT DE BOSS"); // send message
            System.out.println(client.readMessageLine());
        }

    }
}