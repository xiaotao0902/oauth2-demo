package com.pactera.ecplatform.auth.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class DataStoreConfig {

    public static final String REDIS_CACHE_NAME="redis_cache_name";
    public static final String REDIS_PREFIX ="redis_cache_prefix";
    public static final Long EXPIRE =60*60L;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
    RedisCache redisCache(RedisTemplate redisTemplate){
        RedisCache redisCache = new RedisCache(REDIS_CACHE_NAME,REDIS_PREFIX.getBytes(),redisTemplate,EXPIRE);
        return redisCache;
    }
    @Bean
    public UserCache userCache(RedisCache redisCache) throws Exception {
        UserCache userCache = new SpringCacheBasedUserCache(redisCache);
        return userCache;
    }

    /**
     * 
     * Token
     * 1、InMemoryTokenStore
     * 2、JdbcTokenStore
     * 3、JwtTokenStore
     * 4、RedisTokenStore
     */
    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
        return new RedisTokenStore(redisConnectionFactory);
    }
}
