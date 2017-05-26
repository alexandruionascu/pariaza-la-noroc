package ReactorIO;

import ReactorIO.EventLoop.EventLoop;
import com.google.gson.Gson;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;


import static com.ea.async.Async.await;
/**
 * Created by faraonul on 5/26/17.
 */
public class Reactor {
    AsynchronousSocketChannel channel;
    public Reactor(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    public void emit(String event, String message) {
        EmitModel model = new EmitModel(event, message);
        Gson gson = new Gson();
        ByteBuffer bytes = ByteBuffer.wrap(gson.toJson(model).getBytes());
        await(EventLoop.makeCompletable(channel.write(bytes)));
    }
}
