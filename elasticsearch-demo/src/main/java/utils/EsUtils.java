package utils;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class EsUtils {

    private TransportClient client = null;

    public TransportClient getClient(String host, int port) throws UnknownHostException {
        Settings settings = Settings.builder()
                //指定集群名称
//                .put("cluster.name", "my-application")
                //探测集群中机器状态
//                .put("client.transport.sniff", true)
                .build();
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));

        return client;
    }

    public void close() {
        if (client != null) {
            client.close();
        }
    }
}
