import BetRadar.Controllers.BetRadar;
import BetRadar.Models.Team;


/**
 * Created by faraonul on 5/13/17.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BetRadar.getInstance().getSerieAFixtures("2697");

        /*for(Team t : BetRadar.getInstance().getPremierLeagueTeams()) {
            System.out.println(t.getId() + " - " + t.getName());
        }
        */

        System.out.println("---------------");

        /*for(Team t : BetRadar.getInstance().getLaLigaTeams()) {
            System.out.println(t.getId() + " - " + t.getName());
        }*/

        /*for(Team t : BetRadar.getInstance().getBundesligaTeams()) {
            System.out.println(t.getId() + " - " + t.getName());
        } */


        for(Team t : BetRadar.getInstance().getSerieATeams()) {
            System.out.println(t.getId() + " - " + t.getName());
        }

    }
}
