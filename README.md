# Cache Utils
Nervousync® Cache Utils. Provider implements class loaded by Java SPI.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.nervousync/cache-jdk17/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.nervousync/cache-jdk17/)
[![License](https://img.shields.io/github/license/wmkm0113/Cache.svg)](https://github.com/wmkm0113/Cache/blob/master/LICENSE)

**Redis Client:** Jedis 4.4.2, Lettuce 6.2.4.RELEASE, Redisson 3.22.0

**Memcached Client:** Xmemcached 2.4.7

## Usage
```
<!-- Contains all implement providers -->
<dependency>
    <groupId>org.nervousync</groupId>
	<artifactId>cache-nodeps-jdk17</artifactId>
    <version>${version}</version>
</dependency>
<!-- Choose provider to use -->
<dependency>
    <groupId>org.nervousync</groupId>
	<artifactId>cache-core-jdk17</artifactId>
    <version>${version}</version>
</dependency>
<!-- Jedis -->
<dependency>
    <groupId>org.nervousync</groupId>
	<artifactId>cache-jedis-jdk17</artifactId>
    <version>${version}</version>
</dependency>
<!-- Lettuce -->
<dependency>
    <groupId>org.nervousync</groupId>
	<artifactId>cache-lettuce-jdk17</artifactId>
    <version>${version}</version>
</dependency>
<!-- Xmemcached -->
<dependency>
    <groupId>org.nervousync</groupId>
	<artifactId>cache-xmemcached-jdk17</artifactId>
    <version>${version}</version>
</dependency>
```

## CacheCore
**Package:** org.nervousync.cache.core

Register cache config and generate cache agent. Please execute static method CacheCore.destroy() when system shutdown.

## CacheConfigBuilder
**Package:** org.nervousync.cache.builder
Using program to generate or reconfigure the cache configure information.

## cache-config.xsd
**Package:** org.nervousync.cache.resources
Configure file XML Schemas Definition.