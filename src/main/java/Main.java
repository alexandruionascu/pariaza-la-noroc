import FifaDB.Controllers.FifaDb;
import FifaDB.Models.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jaunt.UserAgent;

/**
 * Created by faraonul on 5/13/17.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Player[] players = FifaDb.getInstance().searchBestPlayers("ronaldo");
        for(Player player : players) {
            System.out.println(player.getName() + " - " + player.getClub().getName());
        }
    }
}
