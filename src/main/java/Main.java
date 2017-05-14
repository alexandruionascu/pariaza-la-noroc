import FifaDB.Models.Player;
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
        String jsonString = userAgent.json.toString();
        System.out.println(jsonString);
       // Player player = gson.fromJson(jsonString, Player.class);//visit google
        //System.out.println(player.getClub().getImageUrls().getNormal().getLargeImageUrl());
    }
}
