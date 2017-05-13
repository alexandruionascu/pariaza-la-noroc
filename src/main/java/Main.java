import Models.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jaunt.UserAgent;

/**
 * Created by faraonul on 5/13/17.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        UserAgent userAgent = new UserAgent();         //create new userAgent (headless browser)
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        userAgent.visit("https://www.easports.com/uk/fifa/ultimate-team/api/fut/item?jsonParamObject=%7B%22page%22:1,%22name%22:%22messi%22,%22position%22:%22LF,CF,RF,ST,LW,LM,CAM,CDM,CM,RM,RW,LWB,LB,CB,RB,RWB%22%7D");
        Player player = gson.fromJson(userAgent.json.get("items").get(0).toString(), Player.class);//visit google
        System.out.println(player.getHeadshotImgUrl());
    }
}
