import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import utils.EsUtils;

import java.net.UnknownHostException;

/**
 * 查询
 */
public class SearchTest {

    public static void main(String[] args) throws UnknownHostException {
        TransportClient client = new EsUtils().getClient("localhost", 9300);

        SearchResponse response = client.prepareSearch("twitter", "accounts")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("user", "kimchy_5_index"))
//                .setPostFilter(QueryBuilders.rangeQuery("age").from(12))
                .setFrom(0).setSize(60).setExplain(true)
                .get();

        // 最短的查询--查询所有数据
//        SearchResponse response = client.prepareSearch().get();

        System.out.println("response:" + response.toString());
    }

}
