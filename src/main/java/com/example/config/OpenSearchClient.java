
package com.example.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.RestHighLevelClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;

@ApplicationScoped
public class OpenSearchClient {

    @Produces
    @ApplicationScoped
    public RestHighLevelClient createClient() {
        try {
            // Load Truststore
            KeyStore truststore = KeyStore.getInstance("JKS");
            truststore.load(new FileInputStream("src/main/resources/truststore.jks"), "changeit".toCharArray());

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(truststore);

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);

            // Basic Authentication
            BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, 
                new UsernamePasswordCredentials("admin", "Priyanka@123"));

            // Updated RestClientBuilder with SSL context and Basic Auth
            RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "https")
            ).setHttpClientConfigCallback(httpClientBuilder ->
                httpClientBuilder
                    .setSSLContext(sslContext)  
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .setDefaultCredentialsProvider(credentialsProvider)  // Set credentials
            );

            return new RestHighLevelClient(builder);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create OpenSearch client", e);
        }
    }
}

//==========================Middlware added========================