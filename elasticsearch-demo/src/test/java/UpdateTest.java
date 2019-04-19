import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService;
import utils.EsUtils;

import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * 更新
 */
public class UpdateTest {
    public static void main(String[] args) throws Exception {
        TransportClient client = new EsUtils().getClient("localhost", 9300);

        // 方式一：UpdateRequest
        /*UpdateRequest request = new UpdateRequest().index("twitter").type("_doc").id("1");
        request.doc(jsonBuilder()
                .startObject()
                .field("user", "kimchy_1_update")
                .field("postDate", new Date())
                .field("message", "trying out Elasticsearch_1_update")
                .endObject());
        client.update(request).get();*/

        //方式二：prepareUPdate ??
        /*client.prepareUpdate().setScript(new Script("ctx._source.gender = \"male\"",
                ScriptService.ScriptType.FILE, null, null)).get();*/
        // or
        /*client.prepareUpdate("twitter", "_doc", "4")
                .setDoc(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy_4")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch_4")
                        .endObject()).get();*/

        //方式三：update by script
        /*UpdateRequest request = new UpdateRequest("twitter", "_doc", "4")
                .script(new Script("ctx._source.user = \"kimchy_4_Update2\";ctx._source.postDate=\"2019-01-01\";ctx._source.message=\"trying out Elasticsearch_4_update2\""));
        client.update(request).get();*/

        //方式四：update by merging documents
        /*UpdateRequest request = new UpdateRequest("twitter", "_doc", "3")
                .doc(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy_3")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch_3")
                        .endObject());
        client.update(request).get();*/

        //方式五：upsert 有则更新，无则新增
        IndexRequest indexRequest = new IndexRequest("twitter", "_doc", "5")
                .source(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy_5_index")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch_5_index")
                        .endObject());
        UpdateRequest updateRequest = new UpdateRequest("twitter", "_doc", "5")
                .doc(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy_5_UP")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch_5_UP")
                        .endObject())
                .upsert(indexRequest);
        client.update(updateRequest).get();
    }
}
