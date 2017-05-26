package Reactor.Client;

import com.ea.async.Async;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by faraonul on 5/25/17.
 */
public class ReactorClient {
    AsynchronousSocketChannel client;

    public ReactorClient (String hostName, int port) throws IOException, ExecutionException, InterruptedException {
        client = AsynchronousSocketChannel.open();
        SocketAddress address = new Socket(hostName, port).getRemoteSocketAddress();
        client.connect(address).get();
    }

    public ReactorClient (AsynchronousSocketChannel channel) {
        client = channel;
    }

    public static void main(String[] args) throws Exception {
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        client.connect(new Socket("localhost", 8000).getRemoteSocketAddress()).get();
        ByteBuffer message = ByteBuffer.allocate(4000);
        client.write(ByteBuffer.wrap("de la client".getBytes())).get();
        client.write(ByteBuffer.wrap("de la client 2".getBytes())).get();
        Thread.sleep(1000);
        client.write(ByteBuffer.wrap("de la client".getBytes())).get();
        // Async.await(makeCompletableFuture(client.read(message)));

        System.out.println("ASYNC CLIENT " + new String(message.array()));
    }

    public static <T> CompletableFuture<T> makeCompletableFuture(Future<T> future) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return future.get();
            } catch (InterruptedException|ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
