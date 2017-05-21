import BetRadar.Controllers.BetRadar;
import BetRadar.Models.Team;

/**
 * Created by faraonul on 5/13/17.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Team[] teams = BetRadar.getInstance().getAllTeams();
        for(Team t :  teams) {
            System.out.println(t.getName());
        }

    }
}
