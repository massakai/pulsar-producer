package com.github.massakai.pulsar.producer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties("pulsar.client")
@Data
public class PulsarClientProperties {
    private String serviceUrl;
    private String authPluginClassName;
    private Map<String, String> authParams;
    private Duration operationTimeout;
    private Duration statsInterval = Duration.ofSeconds(60);
    private int numIoThreads = 1;
    private int numListenerThreads = 1;
    private boolean useTcpNoDelay = true;
    private String tlsTrustCertsFilePath;
    private boolean tlsAllowInsecureConnection = false;
    private boolean tlsHostnameVerificationEnable = false;
    private int concurrentLookupRequest = 5_000;
    private int maxLookupRequest = 50_000;
    private int maxNumberOfRejectedRequestPerConnection = 50;
    private Duration keepAliveInterval = Duration.ofSeconds(30);
    private Duration connectionTimeout = Duration.ofMillis(10_000);
    private Duration requestTimeout = Duration.ofMillis(60_000);
    // ClientConfigurationDataの実装にないようなので削除
    // private Duration defaultBackoffInterval = Duration.ofMillis(100);
    private Duration maxBackoffInterval = Duration.ofSeconds(30);
    private String socks5ProxyAddress;
    private String socks5ProxyPassword;
    private Duration connectionMaxIdle = Duration.ofSeconds(180);

    /**
     * 設定のマップを生成する
     *
     * @return PulsarClient.loadConf()に渡すマップ
     */
    public Map<String, Object> toMap() {
        var map = new HashMap<String, Object>();

        map.put("serviceUrl", serviceUrl);
        map.put("authPluginClassName", authPluginClassName);
        map.put("authParams", authParams);
        if (operationTimeout != null) {
            map.put("operationTimeoutMs", operationTimeout.toMillis());
        }
        if (statsInterval != null) {
            map.put("statsIntervalSeconds", statsInterval.toSeconds());
        }
        map.put("numIoThreads", numIoThreads);
        map.put("numListenerThreads", numListenerThreads);
        map.put("useTcpNoDelay", useTcpNoDelay);
        map.put("tlsTrustCertsFilePath", tlsTrustCertsFilePath);
        map.put("tlsAllowInsecureConnection", tlsAllowInsecureConnection);
        map.put("tlsHostnameVerificationEnable", tlsHostnameVerificationEnable);
        map.put("concurrentLookupRequest", concurrentLookupRequest);
        map.put("maxLookupRequest", maxLookupRequest);
        map.put("maxNumberOfRejectedRequestPerConnection", maxNumberOfRejectedRequestPerConnection);
        if (keepAliveInterval != null) {
            map.put("keepAliveIntervalSeconds", keepAliveInterval.toSeconds());
        }
        if (connectionTimeout != null) {
            map.put("connectionTimeoutMs", connectionTimeout.toMillis());
        }
        if (requestTimeout != null) {
            map.put("requestTimeoutMs", requestTimeout.toMillis());
        }
        if (maxBackoffInterval != null) {
            map.put("maxBackoffIntervalNanos", maxBackoffInterval.toNanos());
        }
        map.put("socks5ProxyAddress", socks5ProxyAddress);
        map.put("socks5ProxyPassword", socks5ProxyPassword);
        if (connectionMaxIdle != null) {
            map.put("connectionMaxIdleSeconds", connectionMaxIdle.toSeconds());
        }

        return map;
    }
}
