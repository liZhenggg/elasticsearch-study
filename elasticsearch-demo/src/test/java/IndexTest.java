import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import utils.EsUtils;

import java.io.IOException;
import java.time.LocalDate;

/**
 * 添加索引
 */
public class IndexTest {
    public static void main(String[] args) throws IOException {
        TransportClient client = new EsUtils().getClient("localhost", 9300);

        // 若指定id不存在则新增，若id存在则修改，若不指定id则新增id由ES生成

        //jsonBuilder方式
        /*IndexResponse response = client.prepareIndex("twitter", "_doc", "6")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy_6")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch_6")
                        .endObject())
                .get();*/


        // 直接传入json形式数据
        String json = "{" +
                "\"user\":\"kimchy_8\"," +
                "\"postDate\":\"" + LocalDate.now() + "\"," +
                "\"message\":\"trying out Elasticsearch8\"" +
                "}";
        IndexResponse response = client.prepareIndex().setIndex("twitter").setType("_doc").setId("8")
                .setSource(json, XContentType.JSON)
                .get();

        System.out.println("response.getIndex():" + response.getIndex());
        System.out.println("response.getType():" + response.getType());
        System.out.println("response.getId():" + response.getId());
        System.out.println("response.version():" + response.getVersion());
        System.out.println("response.status():" + response.status());

//        client.close();
    }

}
