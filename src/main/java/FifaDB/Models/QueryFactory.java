package FifaDB.Models;

/**
 * Created by faraonul on 5/14/17.
 */
public class QueryFactory {
    public QueryObject createDefaultQuery(String name) {
        QueryObject query = new QueryObject();
        query.setName(name);
        query.setPage(1); // default page
        query.setPosition(Constants.getAllPositions());
        return query;
    }

    public QueryObject createQuery(String name, int page, String position) {
        QueryObject query = new QueryObject();
        query.setName(name);
        query.setPage(page);
        query.setPosition(position);
        return query;
    }

    public QueryObject createQuery(String name, String position) {
        QueryObject query = new QueryObject();
        query.setName(name);
        query.setPage(1);
        query.setPosition(position);
        return query;
    }

    public QueryObject createQuery(String name, int page) {
        QueryObject query = new QueryObject();
        query.setName(name);
        query.setPage(page);
        query.setPosition(Constants.getAllPositions());
        return query;
    }
}
