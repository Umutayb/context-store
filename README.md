# ContextStore


[![Maven Central](https://img.shields.io/maven-central/v/io.github.umutayb/context-store?color=brightgreen&label=ContextStore)](https://mvnrepository.com/artifact/io.github.umutayb/context-store/latest)

## Overview

ContextStore provides utility classes for managing thread-safe context storage and property handling. It includes the following key components:

1. **`ContextStore`**: A thread-safe context storage solution using `ThreadLocal` and `ConcurrentHashMap`.
2. **`PropertiesReader`**: A simple wrapper for loading and accessing properties from a file.
3. **`PropertyUtilities`**: A utility class for loading and manipulating properties from multiple sources (files, environment, system).
4. **`UtilityPropertiesMap`**: A custom implementation of `AbstractMap` for flexible property handling.

---

## Table of Contents

- [Getting Started](#getting-started)
- [Classes and Methods](#classes-and-methods)
    - [ContextStore](#contextstore)
    - [PropertiesReader](#propertiesreader)
    - [PropertyUtilities](#propertyutilities)
    - [UtilityPropertiesMap](#utilitypropertiesmap)
- [Examples](#examples)

---

## Getting Started

### Prerequisites

- Java 8 or later

### Installation

To use Context Store in your Maven project, add the following dependency to your pom.xml file:
```xml
<dependency>
    <groupId>io.github.umutayb</groupId>
    <artifactId>context-store</artifactId>
    <version>1.x.x</version>
</dependency>
```
After updating your project, the quickstart library is ready to use.

### ContextStore Usage

```java
ContextStore.put("key1", "value1");
String value = ContextStore.get("key1"); // "value1"
```

```java
String value = ContextStore.get("key1", "default"); // "returns 'default' if the key doesn't exist"
```

## Classes and Methods

### ContextStore

A thread-safe store for key-value pairs, designed for concurrent applications.

#### Key Methods:

- **`put(K key, V value)`**  
  Associates the specified value with the specified key in the current thread's context.

- **`get(K key)`**  
  Retrieves the value associated with the given key.

- **`get(K key, V defaultValue)`**  
  Retrieves the value for the given key or returns the default value.

- **`remove(K key)`**  
  Removes the entry for the given key.

- **`clear()`**  
  Clears all entries in the current thread's context.

- **`items()`**  
  Returns an unmodifiable set of all keys.

- **`has(K key)`**  
  Checks if a key exists.

- **`update(K key, V value)`**  
  Updates the value for an existing key.

- **`merge(Map... maps)`**  
  Merges entries from multiple maps into the context.

- **`loadProperties(String... propertyNames)`**  
  Loads properties from specified files and merges them into the context.

---

### PropertiesReader

Loads and retrieves properties from a specified file.

#### Key Methods:

- **`PropertiesReader(String propertyFileName)`**  
  Constructor to initialize with a property file.

- **`getProperty(String propertyName)`**  
  Retrieves the value of the specified property.

---

### PropertyUtilities

Utility functions for managing properties from files, environment variables, and system properties.

#### Key Methods:

- **`loadPropertyFile(String propertyFileName)`**  
  Loads a properties file.

- **`getProperty(String key)`**  
  Retrieves a property by its key.

- **`getProperty(String key, String defaultValue)`**  
  Retrieves a property or returns the default value.

- **`fromPropertyFile(String propertyFileName)`**  
  Converts properties from a file into a map.

- **`getProperties(String... propertyNames)`**  
  Retrieves properties from multiple files and merges them.

- **`fromEnvironment()`**  
  Loads properties from environment variables.

- **`fromSystemProperties()`**  
  Loads properties from system properties.

---

### UtilityPropertiesMap

A flexible map implementation for property handling.

#### Key Features:

- Inherits from `AbstractMap`.
- Supports formatting of keys (case-insensitive, special character normalization).
- Allows cascading lookup with parent maps.

#### Key Methods:

- **`create(Properties p)`**  
  Creates a `UtilityPropertiesMap` from a `Properties` object.

- **`formatKey(String key)`**  
  Formats keys to handle variations in case and special characters.

---

## Examples

### ContextStore Usage

```java
ContextStore.put("key1", "value1");
String value = ContextStore.get("key1"); // "value1"
```

```java
String value = ContextStore.get("key1", "default"); // "returns 'default' if the key doesn't exist"
```

### Loading Properties with PropertiesReader

```java
PropertiesReader reader = new PropertiesReader("config.properties");
String value = reader.getProperty("someKey");
```

### PropertyUtilities for Property Management

```java
PropertyUtilities.loadPropertyFile("app.properties");
String value = PropertyUtilities.getProperty("key", "default");
```

### UtilityPropertiesMap for Flexible Property Access

```java
Properties props = new Properties();
props.setProperty("key", "value");
UtilityPropertiesMap map = UtilityPropertiesMap.create(props);
String value = map.get("key");
```

---

## License

This library is open-source and licensed under the MIT License.
