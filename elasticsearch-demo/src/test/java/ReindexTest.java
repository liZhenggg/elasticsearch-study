import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.ReindexAction;
import org.elasticsearch.index.reindex.ReindexRequestBuilder;
import utils.EsUtils;

import java.net.UnknownHostException;

/**
 * 复制索引
 */
public class ReindexTest {

    public static void main(String[] args) throws UnknownHostException {
        TransportClient client = new EsUtils().getClient("localhost", 9300);

        BulkByScrollResponse response = new ReindexRequestBuilder(client, ReindexAction.INSTANCE)
                .source("twitter")
                .destination("reindex_twitter")
                .filter(QueryBuilders.matchQuery("user", "kimchy_4_Update2"))
                .get();

        System.out.println("response.getTotal():" + response.getTotal());
        System.out.println("response.getStatus():" + response.getStatus());
        System.out.println("response.getBatches():" + response.getBatches());
        System.out.println("response.getDeleted():" + response.getDeleted());
        System.out.println("response.getCreated():" + response.getCreated());
        System.out.println("response.getBulkFailures():" + response.getBulkFailures());
        System.out.println("response.getSearchFailures():" + response.getSearchFailures());
        System.out.println("response.getVersionConflicts():" + response.getVersionConflicts());
        System.out.println("response.toString():" + response.toString());
    }

}
