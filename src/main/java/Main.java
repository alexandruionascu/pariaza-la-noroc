import BetRadar.Controllers.BetRadar;
import BetRadar.Models.Fixture;
import BetRadar.Models.Team;
import com.google.gson.Gson;
import info.debatty.java.stringsimilarity.JaroWinkler;


/**
 * Created by faraonul on 5/13/17.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        System.out.println(gson.toJson(BetRadar.getInstance().getSerieATeams()));
        System.out.println(gson.toJson(BetRadar.getInstance().getPremierLeagueTeams()));
        System.out.println(gson.toJson(BetRadar.getInstance().getLaLigaTeams()));
        System.out.println(gson.toJson(BetRadar.getInstance().getBundesligaTeams()));
        System.out.println(gson.toJson(BetRadar.getInstance().getLaLigaTeamFixtures("2817")));
        System.out.println(new JaroWinkler().distance(
                "Man Utd",
                "ManchesterUnited"
        ));
    }
}
