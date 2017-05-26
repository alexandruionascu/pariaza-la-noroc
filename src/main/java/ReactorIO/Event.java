package ReactorIO;

/**
 * Created by faraonul on 5/26/17.
 */
public class Event {
    String data;
    Reactor reactor;

    public Event(String data, Reactor reactor) {
        this.data = data;
        this.reactor = reactor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Reactor getReactor() {
        return reactor;
    }

    public void setReactor(Reactor reactor) {
        this.reactor = reactor;
    }
}
