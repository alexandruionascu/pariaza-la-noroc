package Server; /**
 * Created by faraonul on 5/20/17.
 */
import BetRadar.Controllers.BetRadar;
import spark.Spark;
import static spark.Spark.*;

public class Server {
    public static void main(String[] args) {
        Spark.port(8000);

        get("/hello", (req, res) -> BetRadar.getInstance().getPremierLeagueTeams());
    }
}
