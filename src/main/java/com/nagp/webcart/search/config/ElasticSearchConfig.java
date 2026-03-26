package com.nagp.webcart.search.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

@Configuration
public class ElasticSearchConfig extends ElasticsearchConfiguration {

    @Value("${elasticsearch.host}")
    private String elasticsearchHost;

    @Value("${elasticsearch.username}")
    private String username;

    @Value("${elasticsearch.password}")
    private String password;

    @Override
    public ClientConfiguration clientConfiguration() {
        HostnameVerifier noopVerifier = (hostname, session) -> true;
        return ClientConfiguration.builder()
                .connectedTo(elasticsearchHost)
                .usingSsl(getSslContext(), noopVerifier)
                .withBasicAuth(username, password)
                .build();
    }

    // NOTE: This trusts all certificates. Use proper CA validation in production.
    private SSLContext getSslContext() {
        try {
            TrustManager[] trustManagers = new TrustManager[]{
                    new X509TrustManager() {
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                        public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                    }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, new java.security.SecureRandom());
            return sslContext;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create SSL context", e);
        }
    }
}
