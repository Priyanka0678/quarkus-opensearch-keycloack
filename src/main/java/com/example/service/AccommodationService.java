
// // package com.example.service;

// // import jakarta.enterprise.context.ApplicationScoped;
// // import jakarta.inject.Inject;
// // import org.opensearch.action.index.IndexRequest;
// // import org.opensearch.action.update.UpdateRequest;
// // import org.opensearch.action.delete.DeleteRequest;
// // import org.opensearch.action.get.GetRequest;
// // import org.opensearch.client.RequestOptions;
// // import org.opensearch.client.RestHighLevelClient;
// // import org.opensearch.search.builder.SearchSourceBuilder;
// // import org.opensearch.index.query.QueryBuilders;
// // import org.opensearch.action.search.SearchRequest;
// // import org.opensearch.action.search.SearchResponse;
// // //import org.opensearch.client.core.IndexResponse;

// // import java.io.IOException;
// // import java.util.ArrayList;
// // import java.util.List;
// // import java.util.Map;

// // @ApplicationScoped
// // public class AccommodationService {

// //     @Inject
// //     RestHighLevelClient client;

// //     private static final String INDEX = "accommodations";

// //     // Ensure the index exists
// //     public void ensureIndexExists() throws IOException {
// //         if (!client.indices().exists(new org.opensearch.client.indices.GetIndexRequest(INDEX), RequestOptions.DEFAULT)) {
// //             createIndex();
// //         }
// //     }

// //     // Create index if it doesn't exist
// //     private void createIndex() throws IOException {
// //         org.opensearch.client.indices.CreateIndexRequest createIndexRequest = new org.opensearch.client.indices.CreateIndexRequest(INDEX);
// //         client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
// //     }

// //     // Create document
// //     public void createDocument(String id, Map<String, Object> document) throws IOException {
// //         ensureIndexExists();
// //         IndexRequest indexRequest = new IndexRequest(INDEX).id(id).source(document);
// //         client.index(indexRequest, RequestOptions.DEFAULT);
// //     }

// //     // Get document by ID
// //     public Map<String, Object> getDocument(String id) throws IOException {
// //         ensureIndexExists();
// //         return client.get(new GetRequest(INDEX, id), RequestOptions.DEFAULT).getSource();
// //     }

// //     // Update document by ID
// //     public void updateDocument(String id, Map<String, Object> document) throws IOException {
// //         ensureIndexExists();
// //         UpdateRequest updateRequest = new UpdateRequest(INDEX, id).doc(document);
// //         client.update(updateRequest, RequestOptions.DEFAULT);
// //     }

// //     // Delete document by ID
// //     public void deleteDocument(String id) throws IOException {
// //         ensureIndexExists();
// //         client.delete(new DeleteRequest(INDEX, id), RequestOptions.DEFAULT);
// //     }

// //     // Search documents by field and value
// //     public List<Map<String, Object>> searchDocuments(String field, String value) throws IOException {
// //         ensureIndexExists();
// //         SearchRequest searchRequest = new SearchRequest(INDEX);
// //         searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.matchQuery(field, value)));

// //         SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
// //         List<Map<String, Object>> results = new ArrayList<>();

// //         response.getHits().forEach(hit -> results.add(hit.getSourceAsMap()));
// //         return results;
// //     }
// // }


// package com.example.service;

// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.inject.Inject;
// import org.opensearch.action.index.IndexRequest;
// import org.opensearch.action.update.UpdateRequest;
// import org.opensearch.action.delete.DeleteRequest;
// import org.opensearch.action.get.GetRequest;
// import org.opensearch.client.RequestOptions;
// import org.opensearch.client.RestHighLevelClient;
// import org.opensearch.search.builder.SearchSourceBuilder;
// import org.opensearch.index.query.QueryBuilders;
// import org.opensearch.action.search.SearchRequest;
// import org.opensearch.action.search.SearchResponse;

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;

// /**
//  * Service for managing accommodation documents in OpenSearch.
//  */
// @ApplicationScoped
// public class AccommodationService {

//     @Inject
//     RestHighLevelClient client;

//     private static final String INDEX = "accommodations";

//     /**
//      * Ensures the index exists in OpenSearch.
//      *
//      * @throws IOException if an error occurs
//      */
//     public void ensureIndexExists() throws IOException {
//         if (!client.indices().exists(new org.opensearch.client.indices.GetIndexRequest(INDEX), RequestOptions.DEFAULT)) {
//             createIndex();
//         }
//     }

//     /**
//      * Creates the index in OpenSearch.
//      *
//      * @throws IOException if an error occurs
//      */
//     private void createIndex() throws IOException {
//         org.opensearch.client.indices.CreateIndexRequest createIndexRequest = new org.opensearch.client.indices.CreateIndexRequest(INDEX);
//         client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
//     }

//     /**
//      * Creates a document in OpenSearch.
//      *
//      * @param id       Document ID
//      * @param document Document data
//      * @throws IOException if an error occurs
//      */
//     public void createDocument(String id, Map<String, Object> document) throws IOException {
//         ensureIndexExists();
//         IndexRequest indexRequest = new IndexRequest(INDEX).id(id).source(document);
//         client.index(indexRequest, RequestOptions.DEFAULT);
//     }

//     /**
//      * Retrieves a document by ID from OpenSearch.
//      *
//      * @param id Document ID
//      * @return Document data
//      * @throws IOException if an error occurs
//      */
//     public Map<String, Object> getDocument(String id) throws IOException {
//         ensureIndexExists();
//         return client.get(new GetRequest(INDEX, id), RequestOptions.DEFAULT).getSource();
//     }

//     /**
//      * Updates a document in OpenSearch.
//      *
//      * @param id       Document ID
//      * @param document Updated document data
//      * @throws IOException if an error occurs
//      */
//     public void updateDocument(String id, Map<String, Object> document) throws IOException {
//         ensureIndexExists();
//         UpdateRequest updateRequest = new UpdateRequest(INDEX, id).doc(document);
//         client.update(updateRequest, RequestOptions.DEFAULT);
//     }

//     /**
//      * Deletes a document by ID from OpenSearch.
//      *
//      * @param id Document ID
//      * @throws IOException if an error occurs
//      */
//     public void deleteDocument(String id) throws IOException {
//         ensureIndexExists();
//         client.delete(new DeleteRequest(INDEX, id), RequestOptions.DEFAULT);
//     }

//     /**
//      * Retrieves all documents from OpenSearch.
//      *
//      * @return List of documents
//      * @throws IOException if an error occurs
//      */
//     public List<Map<String, Object>> getAllDocuments() throws IOException {
//         ensureIndexExists();
//         SearchRequest searchRequest = new SearchRequest(INDEX);
//         searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));

//         SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
//         List<Map<String, Object>> results = new ArrayList<>();

//         response.getHits().forEach(hit -> results.add(hit.getSourceAsMap()));
//         return results;
//     }

//     /**
//      * Searches for documents in OpenSearch by a specific field and value.
//      *
//      * @param field Field to search
//      * @param value Value to match
//      * @return List of matching documents
//      * @throws IOException if an error occurs
//      */
//     public List<Map<String, Object>> searchDocuments(String field, String value) throws IOException {
//         ensureIndexExists();
//         SearchRequest searchRequest = new SearchRequest(INDEX);
//         searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.matchQuery(field, value)));

//         SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
//         List<Map<String, Object>> results = new ArrayList<>();

//         response.getHits().forEach(hit -> results.add(hit.getSourceAsMap()));
//         return results;
//     }
// }


package com.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.update.UpdateRequest;
import org.opensearch.action.delete.DeleteRequest;
import org.opensearch.action.get.GetRequest;
import org.opensearch.search.builder.SearchSourceBuilder;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class AccommodationService {

    @Inject
    RestHighLevelClient client;

    private static final String INDEX = "accommodations";

    public void ensureIndexExists() throws IOException {
        if (!client.indices().exists(new org.opensearch.client.indices.GetIndexRequest(INDEX), RequestOptions.DEFAULT)) {
            createIndex();
        }
    }

    private void createIndex() throws IOException {
        org.opensearch.client.indices.CreateIndexRequest createIndexRequest = new org.opensearch.client.indices.CreateIndexRequest(INDEX);
        client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }

    public void createDocument(String id, Map<String, Object> document) throws IOException {
        ensureIndexExists();
        IndexRequest indexRequest = new IndexRequest(INDEX).id(id).source(document);
        client.index(indexRequest, RequestOptions.DEFAULT);
    }

    public Map<String, Object> getDocument(String id) throws IOException {
        ensureIndexExists();
        return client.get(new GetRequest(INDEX, id), RequestOptions.DEFAULT).getSource();
    }

    public void updateDocument(String id, Map<String, Object> document) throws IOException {
        ensureIndexExists();
        UpdateRequest updateRequest = new UpdateRequest(INDEX, id).doc(document);
        client.update(updateRequest, RequestOptions.DEFAULT);
    }

    public void deleteDocument(String id) throws IOException {
        ensureIndexExists();
        client.delete(new DeleteRequest(INDEX, id), RequestOptions.DEFAULT);
    }

    public List<Map<String, Object>> getAllDocuments() throws IOException {
        ensureIndexExists();
        SearchRequest searchRequest = new SearchRequest(INDEX);
        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        List<Map<String, Object>> results = new ArrayList<>();

        response.getHits().forEach(hit -> results.add(hit.getSourceAsMap()));
        return results;
    }

    public List<Map<String, Object>> searchDocuments(String field, String value) throws IOException {
        ensureIndexExists();-
        SearchRequest searchRequest = new SearchRequest(INDEX);
        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.matchQuery(field, value)));

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        List<Map<String, Object>> results = new ArrayList<>();

        response.getHits().forEach(hit -> results.add(hit.getSourceAsMap()));
        return results;
    }
}

