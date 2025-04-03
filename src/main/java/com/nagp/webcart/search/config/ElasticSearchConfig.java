package com.nagp.webcart.search.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

@Configuration
public class ElasticSearchConfig extends ElasticsearchConfiguration {

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo("35.89.133.135:9200")// If authentication is required
                .build();
    }

//    private SSLContext getSslContext() {
//        try {
//            TrustManager[] trustManagers = new TrustManager[]{
//                    new X509TrustManager() {
//                        public void checkClientTrusted(X509Certificate[] chain, String authType) {}
//                        public void checkServerTrusted(X509Certificate[] chain, String authType) {}
//                        public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
//                    }
//            };
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(null, trustManagers, new java.security.SecureRandom());
//            return sslContext;
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to create SSL context", e);
//        }
//    }
}
