package Server; /**
 * Created by faraonul on 5/20/17.
 */
import BetRadar.Controllers.BetRadar;
import DbManager.DbManager;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import spark.Spark;

import static DbManager.DbManager.DEFAULT_BUCKET;
import static DbManager.DbManager.HOST;
import static DbManager.DbManager.TEAM_PREFIX;
import static spark.Spark.*;

public class Server {
    public static void main(String[] args) {
        Spark.port(8000);
        CouchbaseCluster cluster = CouchbaseCluster.create(HOST);
        // Connect to the bucket and open it
        Bucket bucket = cluster.openBucket(DEFAULT_BUCKET);
        get("/teams/:id", (req, res) -> bucket.get(TEAM_PREFIX + req.params(":id")).content().toString());
    }
}
