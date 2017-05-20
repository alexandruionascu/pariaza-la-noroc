package DbManager;

import BetRadar.Controllers.BetRadar;
import BetRadar.Models.Team;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.transcoder.JsonTranscoder;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by faraonul on 5/20/17.
 */
public class DbManager {
    public static final String HOST = "127.0.0.1";
    public static final String DEFAULT_BUCKET = "default";
    public static final String TEAM_PREFIX = "team_";

    public static void main(String[] args) throws  Exception {
        updatePremierLeagueTeams();
        updateBundesligaTeams();
        updateLaLigaTeams();
        updateSerieATeams();
    }

    private static void updatePremierLeagueTeams() throws Exception {
        updateTeams(BetRadar.getInstance().getPremierLeagueTeams());
    }

    private static void updateLaLigaTeams() throws Exception {
        updateTeams(BetRadar.getInstance().getLaLigaTeams());
    }

    private static void updateBundesligaTeams() throws  Exception {
        updateTeams(BetRadar.getInstance().getBundesligaTeams());
    }

    private static void updateSerieATeams() throws Exception {
        updateTeams(BetRadar.getInstance().getSerieATeams());
    }

    private static void updateTeams(Team[] teams) throws Exception {
        CouchbaseCluster cluster = CouchbaseCluster.create(HOST);
        // Connect to the bucket and open it
        Bucket bucket = cluster.openBucket(DEFAULT_BUCKET);
        JsonTranscoder transcoder = new JsonTranscoder();
        Gson gson = new Gson();
        for(Team team : teams) {
            JsonObject teamObj = transcoder.stringToJsonObject(gson.toJson(team).toString());
            bucket.upsert(JsonDocument.create(TEAM_PREFIX + team.getId(), teamObj));
        }


        /*// Create a JSON document and store it with the ID "helloworld"
        JsonObject content = JsonObject.create().put("hello", "world");
        JsonDocument inserted = bucket.upsert(JsonDocument.create("helloworld", content));

        // Read the document and print the "hello" field
        JsonDocument found = bucket.get("team_38");
        System.out.println("Couchbase is the best database in the " + found.content().toString());
        */
        // Close all buckets and disconnect
        cluster.disconnect();
    }
}
