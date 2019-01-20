package com.liangjinhai.studydemo.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableAutoConfiguration
public class RedisConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.commonTimeout}")
    private String timeout;

    @Bean
    @ConfigurationProperties(prefix = "spring.redis.jedis.pool")
    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        return jedisPoolConfig;
    }

    @Bean
    public JedisCluster getJedisCluster() {
        String[] serverArray = clusterNodes.split(",");//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
        Set<HostAndPort> nodes = new HashSet<>();

        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }

        return new JedisCluster(nodes,Integer.valueOf(timeout),1000,1,password ,new GenericObjectPoolConfig());//需要密码连接的创建对象方式
    }
}
