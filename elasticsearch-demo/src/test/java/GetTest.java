import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import utils.EsUtils;

import java.net.UnknownHostException;

/**
 * 查询
 */
public class GetTest {
    public static void main(String[] args) throws Exception {
        TransportClient client = new EsUtils().getClient("localhost", 9300);

        GetResponse response = client.prepareGet("twitter", "_doc", "6").get();

        System.out.println("response.getIndex():" + response.getIndex());
        System.out.println("response.getType():" + response.getType());
        System.out.println("response.getId():" + response.getId());
        System.out.println("response.version():" + response.getVersion());
        System.out.println("response.getSource():" + response.getSource());

    }
}
