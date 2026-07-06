package redis.starter.configuration.environment;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisEnvironment {
    private String host;
    private int port;
    private long lifetime;
    private String password;

    public RedisEnvironment(String host, int port, long lifetime, String password) {
        this.host = host;
        this.port = port;
        this.lifetime = lifetime;
        this.password = password;
    }

    public RedisEnvironment() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getLifetime() {
        return lifetime;
    }

    public void setLifetime(long lifetime) {
        this.lifetime = lifetime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
