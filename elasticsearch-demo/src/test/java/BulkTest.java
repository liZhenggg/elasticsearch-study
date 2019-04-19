import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import utils.EsUtils;

import java.io.IOException;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * 批量操作
 */
public class BulkTest {

    public static void main(String[] args) throws IOException {
        TransportClient client = new EsUtils().getClient("localhost", 9300);

        BulkRequestBuilder bulkRequest = client.prepareBulk();

        // 批量添加
        /*bulkRequest.add(client.prepareIndex("twitter", "_doc", "bulk1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy_bulk11")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch_bulk1")
                        .endObject()
                )

        );

        bulkRequest.add(client.prepareIndex("twitter", "_doc", "bulk2")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy_bulk22")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch_bulk2")
                        .endObject()
                )
        );*/

        // 批量删除
        bulkRequest.add(client.prepareDelete("twitter", "_doc", "bulk1"));
        bulkRequest.add(client.prepareDelete("twitter", "_doc", "bulk2"));

        BulkResponse responses = bulkRequest.get();
        System.out.println("responses.status():" + responses.status());

        BulkItemResponse[] item = responses.getItems();
        for (BulkItemResponse res : item) {
            System.out.println("------------res------------");
            System.out.println("res.getIndex():" + res.getIndex());
            System.out.println("res.getType():" + res.getType());
            System.out.println("res.getId():" + res.getId());
            System.out.println("res.getVersion():" + res.getVersion());
            System.out.println("res.getItemId():" + res.getItemId());
            System.out.println("res.getOpType():" + res.getOpType());
            System.out.println("res.getResponse():" + res.getResponse());
            System.out.println("res.getFailureMessage():" + res.getFailureMessage());
        }

        if (responses.hasFailures()) {
            System.out.println("hasFailures:" + responses.buildFailureMessage());
        }

    }
}
