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
package org.nervousync.cache.commons;

/**
 * <h2 class="en-US">Cache static global field value</h2>
 * <h2 class="zh-CN">缓存静态变量</h2>
 */
public final class CacheGlobals {

    /**
     * <span class="en-US">Default connect timeout. Unit: second</span>
     * <span class="zh-CN">默认连接超时时间。单位：秒</span>
     */
    public static final int DEFAULT_CONNECTION_TIMEOUT = 1;
    /**
     * <span class="en-US">Default expire time. Unit: second</span>
     * <span class="zh-CN">默认缓存过期时间。单位：秒</span>
     */
    public static final int DEFAULT_EXPIRE_TIME = -1;
    /**
     * <span class="en-US">Default client pool size</span>
     * <span class="zh-CN">默认缓存连接池大小</span>
     */
    public static final int DEFAULT_CLIENT_POOL_SIZE = 5;
    /**
     * <span class="en-US">Default maximum client size</span>
     * <span class="zh-CN">默认最大缓存连接数</span>
     */
    public static final int DEFAULT_MAXIMUM_CLIENT = 500;
    /**
     * <span class="en-US">Default retry count</span>
     * <span class="zh-CN">默认重试次数</span>
     */
    public static final int DEFAULT_RETRY_COUNT = 3;
    /**
     * <span class="en-US">Default server weight</span>
     * <span class="zh-CN">默认服务器权重</span>
     */
    public static final int DEFAULT_CACHE_SERVER_WEIGHT = 1;
    /**
     * <span class="en-US">Default cache identification code</span>
     * <span class="zh-CN">默认的缓存识别代码</span>
     */
    public static final String DEFAULT_CACHE_NAME = "Nervousync_Cache";
}
