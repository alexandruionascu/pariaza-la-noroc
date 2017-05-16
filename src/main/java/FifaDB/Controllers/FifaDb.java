package FifaDB.Controllers;
import FifaDB.Constants;
import FifaDB.Models.FifaDbResponse;
import FifaDB.Models.Player;
import FifaDB.Models.QueryFactory;
import FifaDB.Models.QueryObject;
import com.google.gson.Gson;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * Created by faraonul on 5/14/17.
 */
public class FifaDb {

    private static FifaDb instance = null;
    private FifaDb() {}

    public static FifaDb getInstance() {
        if(instance == null) {
            instance = new FifaDb();
        }
        return instance;
    }

    private FifaDbResponse getDbResponse(String name) throws  ResponseException, UnsupportedEncodingException {
        QueryFactory queryFactory = new QueryFactory();
        QueryObject query = queryFactory.createDefaultQuery(name);
        String queryString = URLEncoder.encode(query.toString(), Constants.DEFAULT_ENCODING);
        String queryUrl = Constants.FIFA_DB_URL + "?" + Constants.JSON_PARAM_OBJECT + "=" + queryString;
        System.out.println(queryUrl);
        UserAgent userAgent = new UserAgent();
        userAgent.visit(queryUrl);
        return new Gson().fromJson(userAgent.json.toString(), FifaDbResponse.class);
    }

    public Player searchBestPlayer(String name) throws ResponseException, UnsupportedEncodingException {
        FifaDbResponse dbResponse = getDbResponse(name);

        for(Player player : dbResponse.getPlayers()) {
            if (player.isSpecialType() == false) {
                return player;
            }
        }

        return null;
    }

    public Player[] searchBestPlayers(String name) throws ResponseException, UnsupportedEncodingException {
        FifaDbResponse dbResponse = getDbResponse(name);
        return Arrays.stream(dbResponse.getPlayers()).filter(player -> !player.isSpecialType()).toArray(Player[]::new);
    }

}
