import BetRadar.Controllers.BetRadar;
import BetRadar.Models.Fixture;
import BetRadar.Models.Team;
import com.google.gson.Gson;

import java.io.PrintWriter;
import java.util.List;

/**
 * Created by faraonul on 5/13/17.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        Team[] teams = BetRadar.getInstance().getAllTeams();
        for(Team team : teams) {
            team.setFixtures(BetRadar.getInstance().getFixtures(team));
        }
        PrintWriter out = new PrintWriter("data.json");
        out.print(gson.toJson(teams));
        System.out.println("here");
        out.close();
        //for(Team t :  teams) {
            //System.out.println(t.getId());
        //}

        //List<Fixture> fixtures = BetRadar.getInstance().getPremierLeagueTeamFixtures("42");
        //for(Fixture fixture : fixtures) {
        //    System.out.println(fixture.getHomeTeam() + " - "  + fixture.getAwayTeam() + " " + String.valueOf(fixture.getRound()) + "-" + fixture.getLocation());
        //}

        //System.out.println(gson.toJson(teams[0]));

    }
}
