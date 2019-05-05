import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import utils.EsUtils;

import java.net.UnknownHostException;

/**
 * 滚动
 */
public class UsingScrollsTest {

    public static void main(String[] args) throws UnknownHostException {
        TransportClient client = new EsUtils().getClient("localhost", 9300);

        TermQueryBuilder qb = QueryBuilders.termQuery("postDate", "2019-04-18");

        SearchResponse scrollResponse = client.prepareSearch("twitter", "accounts")
                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
                .setScroll(new TimeValue(60000))
                .setQuery(qb)
                .setSize(100)
                .get();
        System.out.println("scrollResponse.getHits().getTotalHits():" + scrollResponse.getHits().getTotalHits());
        System.out.println("scrollResponse.getScrollId():" + scrollResponse.getScrollId());
        System.out.println("scrollResponse.toString():" + scrollResponse.toString());

        do {
            for (SearchHit hit : scrollResponse.getHits().getHits()) {
                System.out.println("--------------- hit ---------------");
                System.out.println("hit.getId():" + hit.getId());
                System.out.println("hit.getIndex():" + hit.getIndex());
                System.out.println("hit.getType():" + hit.getType());
                System.out.println("hit.getScore():" + hit.getScore());
                System.out.println("hit.docId():" + hit.docId());
                System.out.println("hit.toString():" + hit.toString());
            }
            scrollResponse = client.prepareSearchScroll(scrollResponse.getScrollId())
                    .setScroll(new TimeValue(60000))
                    .execute().actionGet();
            System.out.println("+++++++++++++++ scrollResponse +++++++++++++++");
            System.out.println("scrollResponse.getHits().getTotalHits():" + scrollResponse.getHits().getTotalHits());
            System.out.println("scrollResponse.getScrollId():" + scrollResponse.getScrollId());
            System.out.println("scrollResponse.toString():" + scrollResponse.toString());
//            scrollResponse.getScrollId();

        } while (scrollResponse.getHits().getHits().length != 0);

    }
}
