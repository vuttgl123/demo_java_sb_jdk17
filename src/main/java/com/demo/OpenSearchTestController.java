package com.demo;

import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.get.GetRequest;
import org.opensearch.action.get.GetResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OpenSearchTestController {

    private final RestHighLevelClient client;

    public OpenSearchTestController(RestHighLevelClient client) {
        this.client = client;
    }

    @GetMapping("/test-opensearch")
    public String testOpenSearch() throws Exception {
        // 1) Index 1 document
        IndexRequest indexRequest = new IndexRequest("test-index")
                .id("1")
                .source(Map.of("message", "Hello OpenSearch"));

        client.index(indexRequest, RequestOptions.DEFAULT);

        // 2) Get lại document
        GetRequest getRequest = new GetRequest("test-index", "1");
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);

        return "OpenSearch OK, document = " + response.getSourceAsString();
    }
}
