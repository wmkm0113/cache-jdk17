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
@XmlSchema(namespace = "https://nervousync.org/schemas/cache", elementFormDefault = XmlNsForm.QUALIFIED,
        location = "https://nervousync.org/schemas/cache_config_1.0.xsd",
        xmlns = {
            @XmlNs(prefix = "cache", namespaceURI = "https://nervousync.org/schemas/cache"),
            @XmlNs(prefix = "secure", namespaceURI = "https://nervousync.org/schemas/secure")
        })
package org.nervousync.cache.config;

import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;