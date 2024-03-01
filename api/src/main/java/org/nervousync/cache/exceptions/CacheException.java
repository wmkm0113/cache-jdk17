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
package org.nervousync.cache.exceptions;

import org.nervousync.exceptions.AbstractException;

import java.io.Serial;

/**
 * <h2 class="en-US">Cache Exception</h2>
 * <h2 class="zh-CN">缓存异常</h2>
 *
 * @author Steven Wee	<a href="mailto:wmkm0113@gmail.com">wmkm0113@gmail.com</a>
 * @version $Revision: 1.0.0 $ $Date: Apr 25, 2017 18:30:42 $
 */
public final class CacheException extends AbstractException {

    /**
     *
     */
	@Serial
    private static final long serialVersionUID = -156775157749202954L;
	/**
	 * <h3 class="en-US">Constructor method for CacheException</h3>
	 * <span class="en-US">Create a new CacheException with the specified message.</span>
	 * <h3 class="zh-CN">CacheException构造方法</h3>
	 * <span class="zh-CN">使用特定的信息创建 CacheException 实例对象。</span>
	 *
	 * @param errorCode 	<span class="en-US">Error identified code</span>
     *                      <span class="zh-CN">错误识别代码</span>
     * @param collections   <span class="en-US">given parameters of information formatter</span>
     *                      <span class="zh-CN">用于资源信息格式化的参数</span>
	 */
	public CacheException(final long errorCode, final Object... collections) {
		super(errorCode, collections);
	}
	/**
	 * <h3 class="en-US">Constructor method for NetworkInfoException</h3>
	 * <span class="en-US">Create a new NetworkInfoException with the specified message and root cause.</span>
	 * <h3 class="zh-CN">NetworkInfoException构造方法</h3>
	 * <span class="zh-CN">使用特定的信息以及异常信息对象实例创建NetworkInfoException实例对象。</span>
	 *
	 * @param errorCode 	<span class="en-US">Error identified code</span>
     *                      <span class="zh-CN">错误识别代码</span>
	 * @param cause 		<span class="en-US">The root cause</span>
	 *              		<span class="zh-CN">异常信息对象实例</span>
     * @param collections   <span class="en-US">given parameters of information formatter</span>
     *                      <span class="zh-CN">用于资源信息格式化的参数</span>
	 */
	public CacheException(final long errorCode, final Throwable cause, final Object... collections) {
		super(errorCode, cause, collections);
	}
}
