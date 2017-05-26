package ReactorIO;

/**
 * Created by faraonul on 5/26/17.
 */
public class EmitModel {
    private String event;
    private String message;

    public EmitModel(String eventName, String message) {
        this.event = eventName;
        this.message = message;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String eventName) {
        this.event = eventName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
