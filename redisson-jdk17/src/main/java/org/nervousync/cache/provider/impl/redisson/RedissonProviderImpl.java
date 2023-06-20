/*
 * Licensed to the Nervousync Studio (NSYC) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nervousync.cache.provider.impl.redisson;

import org.nervousync.cache.annotation.CacheProvider;
import org.nervousync.cache.config.CacheConfig;
import org.nervousync.cache.exceptions.CacheException;
import org.nervousync.cache.provider.impl.AbstractProvider;
import org.nervousync.commons.core.Globals;
import org.nervousync.utils.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis cache provider using Redisson
 *
 * @author Steven Wee	<a href="mailto:wmkm0113@Hotmail.com">wmkm0113@Hotmail.com</a>
 * @version $Revision : 1.0 $ $Date: 12/23/2020 13:43 PM $
 */
@CacheProvider(name = "RedissonProvider", defaultPort = 6379)
public final class RedissonProviderImpl extends AbstractProvider {

    private RedissonClient redissonClient = null;

    public RedissonProviderImpl() throws CacheException {
        super();
    }

    /**
     * (non-Javadoc)
     * @see org.nervousync.cache.provider.Provider#set(String, String, int)
     */
    @Override
    public void set(final String key, final String value, final int expire) {
        this.redissonClient.getBucket(key, new StringCodec(Globals.DEFAULT_ENCODING))
                .set(value, this.expiryTime(expire), TimeUnit.SECONDS);
    }

    /**
     * (non-Javadoc)
     * @see org.nervousync.cache.provider.Provider#add(String, String, int)
     */
    @Override
    public void add(final String key, final String value, final int expire) {
        this.set(key, value, expire);
    }

    /**
     * (non-Javadoc)
     * @see org.nervousync.cache.provider.Provider#replace(String, String, int)
     */
    @Override
    public void replace(final String key, final String value, final int expire) {
        this.set(key, value, expire);
    }

    /**
     * (non-Javadoc)
     * @see org.nervousync.cache.provider.Provider#touch(String...)
     */
    @Override
    public void touch(final String... keys) {
        Arrays.asList(keys)
                .forEach(key -> this.redissonClient.getBucket(key, new StringCodec(Globals.DEFAULT_ENCODING)).touch());
    }

    /**
     * (non-Javadoc)
     * @see org.nervousync.cache.provider.Provider#delete(String)
     */
    @Override
    public void delete(final String key) {
        this.redissonClient.getBucket(key, new StringCodec(Globals.DEFAULT_ENCODING)).delete();
    }

    /**
     * (non-Javadoc)
     * @see org.nervousync.cache.provider.Provider#get(String)
     */
    @Override
    public String get(final String key) {
        return (String) this.redissonClient.getBucket(key, new StringCodec(Globals.DEFAULT_ENCODING)).get();
    }

    /**
     * (non-Javadoc)
     * @see org.nervousync.cache.provider.Provider#incr(String, long)
     */
    @Override
    public long incr(final String key, final long step) {
        return this.redissonClient.getAtomicLong(key).addAndGet(step);
    }

    /**
     * (non-Javadoc)
     * @see org.nervousync.cache.provider.Provider#decr(String, long)
     */
    @Override
    public long decr(final String key, final long step) {
        return this.redissonClient.getAtomicLong(key).addAndGet(step * -1L);
    }

    /**
     * (non-Javadoc)
     * @see org.nervousync.cache.provider.Provider#destroy()
     */
    @Override
    public void destroy() {
        if (!this.redissonClient.isShutdown() && !this.redissonClient.isShuttingDown()) {
            this.redissonClient.shutdown();
        }
    }

    /**
     * (non-Javadoc)
     * @see AbstractProvider#expire(String, int)
     */
    @Override
    public void expire(final String key, final int expire) {
        this.redissonClient.getBucket(key, new StringCodec(Globals.DEFAULT_ENCODING))
                .expire(Duration.ofMillis(this.expiryTime(expire) * 1000L));
    }

    /**
     * (non-Javadoc)
     * @see AbstractProvider#singletonMode(CacheConfig.ServerConfig, String, String)
     */
    protected void singletonMode(final CacheConfig.ServerConfig serverConfig,
                               final String userName, final String passWord) {
        Config config = new Config();
        SingleServerConfig singleConfig = config.useSingleServer()
                .setAddress(this.serverAddress(serverConfig.getServerAddress(), serverConfig.getServerPort()))
                .setConnectionMinimumIdleSize(this.getClientPoolSize())
                .setConnectTimeout(this.getConnectTimeout() * 1000)
                .setConnectionPoolSize(this.getClientPoolSize())
                .setDatabase(0);
        if (StringUtils.notBlank(passWord)) {
            singleConfig.setPassword(passWord);
            if (StringUtils.notBlank(userName)) {
                singleConfig.setUsername(userName);
            }
        }
        config.setTransportMode(TransportMode.NIO);
        this.redissonClient = Redisson.create(config);
    }

    /**
     * (non-Javadoc)
     * @see AbstractProvider#clusterMode(List, String, String, String)
     */
    protected void clusterMode(final List<CacheConfig.ServerConfig> serverConfigList,
                             final String masterName, final String userName, final String passWord) {
        Config config = new Config();
        switch (this.getClusterMode()) {
            case Sentinel -> {
                SentinelServersConfig sentinelConfig = config.useSentinelServers()
                        .setMasterName(masterName)
                        .setSentinelUsername(StringUtils.notBlank(userName) ? userName : null)
                        .setSentinelPassword(StringUtils.notBlank(passWord) ? userName : null)
                        .setConnectTimeout(this.getConnectTimeout() * 1000)
                        .setRetryAttempts(this.getRetryCount())
                        .setSlaveConnectionPoolSize(this.getClientPoolSize())
                        .setMasterConnectionPoolSize(this.getClientPoolSize());
                serverConfigList.forEach(serverConfig ->
                        sentinelConfig.addSentinelAddress(this.serverAddress(serverConfig.getServerAddress(),
                                serverConfig.getServerPort())));
            }
            case Master_Slave -> {
                MasterSlaveServersConfig masterSlaveConfig = config.useMasterSlaveServers()
                        .setUsername(StringUtils.notBlank(userName) ? userName : null)
                        .setPassword(StringUtils.notBlank(passWord) ? userName : null)
                        .setConnectTimeout(this.getConnectTimeout() * 1000)
                        .setRetryAttempts(this.getRetryCount())
                        .setSlaveConnectionPoolSize(this.getClientPoolSize())
                        .setMasterConnectionPoolSize(this.getClientPoolSize())
                        .setReadMode(ReadMode.SLAVE);
                serverConfigList.forEach(serverConfig -> {
                    if (serverConfig.getServerAddress().equalsIgnoreCase(masterName)) {
                        masterSlaveConfig.setMasterAddress(this.serverAddress(serverConfig.getServerAddress(),
                                serverConfig.getServerPort()));
                    } else {
                        masterSlaveConfig.addSlaveAddress(this.serverAddress(serverConfig.getServerAddress(),
                                serverConfig.getServerPort()));
                    }
                });
            }
            default -> {
                ClusterServersConfig clusterConfig = config.useClusterServers()
                        .setUsername(StringUtils.notBlank(userName) ? userName : null)
                        .setPassword(StringUtils.notBlank(passWord) ? userName : null)
                        .setConnectTimeout(this.getConnectTimeout() * 1000)
                        .setRetryAttempts(this.getRetryCount())
                        .setSlaveConnectionPoolSize(this.getClientPoolSize())
                        .setMasterConnectionPoolSize(this.getClientPoolSize());
                serverConfigList.forEach(serverConfig ->
                        clusterConfig.addNodeAddress(this.serverAddress(serverConfig.getServerAddress(),
                                serverConfig.getServerPort())));
            }
        }
        config.setTransportMode(TransportMode.NIO);
        this.redissonClient = Redisson.create(config);
    }

    private String serverAddress(final String serverAddress, final int serverPort) {
        return "redis://" + serverAddress + ":" + this.serverPort(serverPort);
    }
}
