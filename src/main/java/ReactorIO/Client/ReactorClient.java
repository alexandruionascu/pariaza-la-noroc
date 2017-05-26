package ReactorIO.Client;

import ReactorIO.Config;
import ReactorIO.EmitModel;
import ReactorIO.Event;
import ReactorIO.EventLoop.EventLoop;
import ReactorIO.Reactor;
import com.google.gson.Gson;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Consumer;

import static com.ea.async.Async.await;

/**
 * Created by faraonul on 5/25/17.
 */
public class ReactorClient {
    private AsynchronousSocketChannel channel;
    private Reactor reactor;
    private HashMap<String, Consumer<Event>> callbacks;

    public ReactorClient (String hostName, int port) throws IOException, ExecutionException, InterruptedException {
        channel = AsynchronousSocketChannel.open();
        SocketAddress address = new Socket(hostName, port).getRemoteSocketAddress();
        channel.connect(address).get();
        reactor = new Reactor(channel);
        callbacks = new HashMap<>();
    }

    public ReactorClient (AsynchronousSocketChannel channel) {
        this.channel = channel;
        reactor = new Reactor(channel);

        callbacks = new HashMap<>();
    }

    public void emit(String eventData, String message) {
        EmitModel model = new EmitModel(eventData, message);
        Gson gson = new Gson();
        ByteBuffer bytes = ByteBuffer.wrap(gson.toJson(model).getBytes());
        await(EventLoop.makeCompletable(channel.write(bytes)));
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
           // callbacks.get(model.getEvent()).accept(new Event(model.getMessage(), new Reactor(socket)));
            System.out.println(model.getMessage());
        } catch (Exception ex) {
            System.out.println("Failed to serialize " + data);
        }
    }

    public CompletableFuture listen() throws InterruptedException, ExecutionException {
        return EventLoop.getInstance().runAsync(() -> {
            try {
                EventLoop.getInstance().runAsync(() -> handleAsyncRead(channel));
                while(true) {}

            }
            catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        });

    }



    public static void main(String[] args) throws Exception {
        ReactorClient client = new ReactorClient(Config.HOST_NAME, Config.PORT);
        client.emit("connected", "my message sent from client");
    }

}
