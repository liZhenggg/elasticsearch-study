import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import utils.EsUtils;

import java.io.IOException;

/**
 * 删除索引
 */
public class DeleteTest {

    public static void main(String[] args) throws Exception {
        TransportClient client = new EsUtils().getClient("localhost", 9300);

        DeleteResponse response = client.prepareDelete("twitter", "_doc", "8").get();

        System.out.println("response.getIndex():" + response.getIndex());
        System.out.println("response.getType():" + response.getType());
        System.out.println("response.getId():" + response.getId());
        System.out.println("response.version():" + response.getVersion());
        System.out.println("response.status():" + response.status());
    }
}
