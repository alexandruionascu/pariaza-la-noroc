package Reactor.Server;

import Reactor.Client.ReactorClient;
import Reactor.Config;
import Reactor.EventLoop.EventLoop;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;


import static com.ea.async.Async.await;

/**
 * Created by faraonul on 5/25/17.
 */


public class ReactorServer {
    private String hostName;
    private int port;
    private AsynchronousServerSocketChannel server;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public AsynchronousServerSocketChannel getServer() {
        return server;
    }


    ReactorServer(String hostName, int port) throws IOException {
        this.hostName = hostName;
        this.port = port;
        InetSocketAddress address = new InetSocketAddress(hostName, port);
        server = AsynchronousServerSocketChannel.open().bind(address);
    }

    public CompletableFuture<Void> on(String event, Consumer<ReactorClient> consumer) {
        return CompletableFuture.runAsync(() -> {
            //consumer.accept();
        });
    }


    private void handleAsyncRead(AsynchronousSocketChannel socket) {
        ByteBuffer buffer = ByteBuffer.allocate(Config.BUFFER_SIZE);
        await(EventLoop.makeCompletable(socket.read(buffer)));
        System.out.println(new String(buffer.array()));
    }

    public CompletableFuture listen() throws InterruptedException, ExecutionException {
        return EventLoop.getInstance().runAsync(() -> {
            try {
                Future<AsynchronousSocketChannel> acceptFuture = server.accept();
                while (true) {
                    // accept all incoming connections
                    while (acceptFuture.isDone()) {
                        // non blocking get
                        AsynchronousSocketChannel channel = acceptFuture.get();
                        // initialize a new async future accept
                        acceptFuture = server.accept();
                        handleAsyncRead(channel);
                    }
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

    }

    public static void main(String[] args) throws Exception {
        ReactorServer server = new ReactorServer("localhost", 8000);
        await(server.listen());

        System.out.println("BOSS");

    }


}
