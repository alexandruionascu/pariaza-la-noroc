package BossSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by faraonul on 5/21/17.
 */
public class BossServerSocket {


    private ArrayList<Socket> clients = null;
    private ArrayList<OutputStream> serverOutputStreams = null;
    private ArrayList<InputStream> serverInputStreams = null;

    private ArrayList<PrintWriter> clientWriters = null;
    private ArrayList<BufferedReader> clientReaders = null;
    private ArrayList<Thread> clientListenerThreads = null;

    public boolean debugMode = true;

    // counter to track next client
    private int counter = 0;


    private final ServerSocket serverSocket;

    public BossServerSocket(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void setServerSoTimeout(int milliseconds) throws SocketException {
        this.serverSocket.setSoTimeout(milliseconds);
    }

    public void acceptClient(int soTimeOut) throws IOException {
        this.printDebugMessages("Waiting for connection...");

        if (this.clients == null) {
            this.clients = new ArrayList<Socket>();
        }

        if (this.clientWriters == null) {
            this.clientWriters = new ArrayList<PrintWriter>();
        }


        if (this.clientReaders == null) {
            this.clientReaders = new ArrayList<BufferedReader>();
        }

        if (this.serverOutputStreams == null) {
            this.serverOutputStreams = new ArrayList<OutputStream>();
        }

        if (this.serverInputStreams == null) {
            this.serverInputStreams = new ArrayList<InputStream>();
        }

        Socket newClient = this.serverSocket.accept();
        newClient.setSoTimeout(soTimeOut);
        this.clients.add(newClient);
        InputStream serverInputStream = newClient.getInputStream();
        this.serverInputStreams.add(serverInputStream);

        BufferedReader clientReader = new BufferedReader(
                new InputStreamReader(
                        serverInputStream
                ));

        this.clientReaders.add(clientReader);

        OutputStream serverOutputStream = newClient.getOutputStream();
        this.serverOutputStreams.add(serverOutputStream);

        PrintWriter clientWriter = new PrintWriter(serverOutputStream, true);

        this.clientWriters.add(clientWriter);


        // Print clients connected
        int clientNumber = this.clients.size()- 1;

        this.printDebugMessages("Client " + clientNumber + " connected.");
    }

    public void acceptClient() throws IOException {

        acceptClient(0);
    }

    @Deprecated
    public PrintWriter getWriterForClient(int index) {
        return this.clientWriters.get(index);
    }

    @Deprecated
    public BufferedReader getReaderForClient(int index) {
        return this.clientReaders.get(index);
    }

    public OutputStream getServerOutputStreamForClient(int index) {
        return this.serverOutputStreams.get(index);
    }

    public InputStream getServerInputStreamForClient(int index) {
        return this.serverInputStreams.get(index);
    }

    public void sendMessageToAllClients(String message) {
        for (int i = 0; i < this.clients.size(); i++) {
            if (this.clients.get(i) != null){
                this.sendMessageLineForClient(i, message);
            }
        }
    }

    public void sendMessageLineForClient(int index, String message) {
        PrintWriter writer = this.clientWriters.get(index);
        writer.println(message);
        writer.flush();

    }

    public String readMessageLineForClient(int index) throws IOException {

        this.printDebugMessages("Reading message from client " + index + "...");
        BufferedReader reader = this.clientReaders.get(index);
        try {

            String message = reader.readLine();
            this.printDebugMessages("Message received from client.");
            return message;
        } catch (SocketTimeoutException e) {
            System.err.println("Read timed out");
        }

        return "";

    }

    public void sendByteArrayForClient(int index, byte[] myByteArray) throws IOException {
        OutputStream out = this.getServerOutputStreamForClient(index);
        DataOutputStream dos = new DataOutputStream(out);

        dos.writeInt(myByteArray.length);
        if (myByteArray.length > 0) {
            dos.write(myByteArray, 0, myByteArray.length);
        }
    }

    public byte[] readByteArrayForClient(int index) throws IOException {
        InputStream in = this.getServerInputStreamForClient(index);
        DataInputStream dis = new DataInputStream(in);

        int len = dis.readInt();
        byte[] data = new byte[len];
        if (len > 0) {
            dis.readFully(data);
        }
        return data;
    }

    public String readMessageLineFromNextClient() throws IOException {

        int index = this.counter % this.clients.size();

        this.counter++;
        return this.readMessageLineForClient(index);

    }

    public boolean getListenerRunningState() {
        for (Thread thread : this.clientListenerThreads) {
            if (thread.isAlive()) {
                return true;
            }
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void closeServer() throws IOException {
        for (Socket client : this.clients) {
            if (client != null) {

                client.close();
            }
        }

        this.serverSocket.close();
        this.printDebugMessages("Server closed.");
    }

    public void removeClient(int index) throws IOException {
        this.clients.set(index, null).close();
    }

    private void printDebugMessages(String message) {

        if (this.debugMode) {
            System.out.println(message);
        }
    }

    @Override
    public String toString() {
        String result = "";

        result += this.serverSocket.toString() + "\n";
        result += "Clients: ";

        for (Socket client : clients) {
            result += client.toString() + " ";
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        BossServerSocket server = new BossServerSocket(4321);
        server.debugMode = false;
        while(true) {
            server.acceptClient(1);
            server.sendMessageToAllClients("BOSS" + new Random().toString());
            Thread.sleep(2000);
            //server.sendMessageLineForClient(0,  "BOSS");
            //String messageReceived = awesomeServer.readMessageLineForClient(index);
        }
    }
}