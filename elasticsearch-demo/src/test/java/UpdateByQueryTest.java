import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryAction;
import org.elasticsearch.index.reindex.UpdateByQueryRequestBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.sort.SortOrder;
import utils.EsUtils;

import java.net.UnknownHostException;
import java.util.Collections;

/**
 * 根据查询结果更新
 */
public class UpdateByQueryTest {

    public static void main(String[] args) throws UnknownHostException {
        TransportClient client = new EsUtils().getClient("localhost", 9300);

        UpdateByQueryRequestBuilder updateByQuery =
                new UpdateByQueryRequestBuilder(client, UpdateByQueryAction.INSTANCE);

        updateByQuery.source("zZTX47X").abortOnVersionConflict(false);

        /*updateByQuery.source("source_index")
                .filter(QueryBuilders.termQuery("level", "awesome"))
                .size(1000)
                .script(new Script(ScriptType.INLINE,
                        "ctx._source.awesome = 'absolutely'",
                        "painless",
                        Collections.emptyMap()));*/

        /*updateByQuery.source("source_index")
                .filter(QueryBuilders.termQuery("level", "awesome"))
                .size(1000)
                .script(new Script(ScriptType.INLINE,
                        "ctx._source.awesome = 'absolutely'",
                        "painless",
                        Collections.emptyMap()));*/

        /*updateByQuery.source("source_index")
                .source()
                .setSize(500);*/

        /*updateByQuery.source("source_index")
                .size(100)
                .source()
                .addSort("cat", SortOrder.DESC);*/

        /*updateByQuery.source("source_index")
                .script(new Script(
                        ScriptType.INLINE,
                        "if (ctx._source.awesome == 'absolutely') {"
                                + "  ctx.op='noop'"
                                + "} else if (ctx._source.awesome == 'lame') {"
                                + "  ctx.op='delete'"
                                + "} else {"
                                + "ctx._source.awesome = 'absolutely'}",
                        "painless",
                        Collections.emptyMap()));*/


        BulkByScrollResponse response = updateByQuery.get();
        System.out.println("response.toString()" + response.toString());
        System.out.println("response.getStatus()" + response.getStatus());
        System.out.println("response.getTotal()" + response.getTotal());
        System.out.println("response.getUpdated()" + response.getUpdated());

    }
}
