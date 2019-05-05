import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import utils.EsUtils;

import java.net.UnknownHostException;

/**
 * 多个查询
 */
public class MultiSearchTest {
    public static void main(String[] args) throws UnknownHostException {
        TransportClient client = new EsUtils().getClient("localhost", 9300);

        SearchRequestBuilder srb1 = client.prepareSearch().setQuery(QueryBuilders.queryStringQuery("")).setSize(1);
        SearchRequestBuilder srb2 = client.prepareSearch().setQuery(QueryBuilders.matchQuery("name", "kimchy")).setSize(1);

    }
}
