package ReactorIO.Server;

import ReactorIO.Config;
import ReactorIO.EmitModel;
import ReactorIO.Event;
import ReactorIO.EventLoop.EventLoop;
import ReactorIO.Reactor;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.HashMap;
import java.util.Map;
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
    private HashMap<String, Consumer<Event>> callbacks;

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
        callbacks = new HashMap<>();
    }

    public void on(String eventStr, Consumer<Event> event) {
        if (!callbacks.containsKey(eventStr)) {
            callbacks.put(eventStr, event);
        }
    }


    private void handleAsyncRead(AsynchronousSocketChannel socket) {
        ByteBuffer buffer = ByteBuffer.allocate(Config.BUFFER_SIZE);
        await(EventLoop.makeCompletable(socket.read(buffer)));

        String data = new String(buffer.array()).trim();
        // skip disconnect event
        if(data.length() == 0)
            return;
        System.out.println(data);
        Gson gson = new Gson();
        try {
            EmitModel model = gson.fromJson(data, EmitModel.class);
            callbacks.get(model.getEvent()).accept(new Event(model.getMessage(), new Reactor(socket)));
        } catch (Exception ex) {
            System.out.println("Failed to serialize " + data);
        }
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
                        // async handler inside thread pool
                        handleAsyncRead(channel);
                    }
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        });

    }

    public static void main(String[] args) throws Exception {
        ReactorServer server = new ReactorServer("localhost", 8000);
        server.on("connected", event -> {
            System.out.println("MY FUNCT " + event.getData());
            event.getReactor().emit("custom_event", "response from server");
        });
        await(server.listen());
    }


}
