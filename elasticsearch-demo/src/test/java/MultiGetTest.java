import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.transport.TransportClient;
import utils.EsUtils;

import java.net.UnknownHostException;

/**
 * 查询多个数据
 */
public class MultiGetTest {

    public static void main(String[] args) throws UnknownHostException {
        TransportClient client = new EsUtils().getClient("localhost", 9300);

        MultiGetResponse responses = client.prepareMultiGet()
                //根据单个id查询
                .add("twitter", "_doc", "1")
                //根据 同一个index和type下的多个id查询
                .add("twitter", "_doc", "3", "4", "5")
                //查询 不同index下数据
                .add("accounts", "person", "1")
                .get();
        System.out.println("responses json:");


        for (MultiGetItemResponse itemResponses : responses) {
            GetResponse response = itemResponses.getResponse();
            if (response.isExists()) {
                String json = response.getSourceAsString();
                System.out.println(json);
            }
        }

    }


}
