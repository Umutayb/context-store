package context;

import properties.PropertyUtilities;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static properties.PropertyUtilities.fromPropertyFile;

/**
 * --- Imported from Java-Utilities to be a standalone library. ---
 * The ContextStore class provides a thread-safe storage for key-value pairs in a ConcurrentHashMap.
 * Each thread has its own map, which avoids concurrency issues and ensures thread safety.
 *
 * @author Umut Ay Bora
 * @version 1.0.0 (Documented in 1.0.0, migrated from another Java-Utilities)
 */
@SuppressWarnings({"unused", "unchecked"})
public class ContextStore {

    /**
     * ThreadLocal variable to store a ConcurrentHashMap for each thread.
     */
    private static final ThreadLocal<ConcurrentHashMap<Object, Object>> map = ThreadLocal.withInitial(ConcurrentHashMap::new);

    /**
     * Associates the specified value with the specified key in the ContextStore.
     * If the key or value is null, the operation is skipped.
     * This method is synchronized to ensure thread safety during the put operation.
     *
     * @param <K>   The type of the keys in the ContextStore.
     * @param <V>   The type of the values in the ContextStore.
     * @param key   The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     * @throws IllegalArgumentException If either the provided key or value is null.
     * @see ContextStore#map
     */
    public static synchronized <K, V> void put(K key, V value) {
        if (key != null && value != null) {
            map.get().put(key, value);
        }
    }

    /**
     * Removes the entry with the specified key from the ContextStore.
     * If the key is not present in the ContextStore, null is returned.
     * This method is synchronized to ensure thread safety during the removal process.
     *
     * @param <K> The type of the keys in the ContextStore.
     * @param <V> The type of the values in the ContextStore.
     * @param key The key whose associated entry is to be removed.
     * @throws IllegalArgumentException If the provided key is null.
     * @see ContextStore#map
     */
    public static synchronized <K, V> void remove(K key) {
        ((ConcurrentHashMap<K, V>) map.get()).remove(key);
    }

    /**
     * Retrieves the value associated with the specified key from the ContextStore.
     * If the key is not present in the ContextStore, null is returned.
     * This method is synchronized to ensure thread safety during the retrieval process.
     *
     * @param <K> The type of the keys in the map.
     * @param <V> The type of the values in the map.
     * @param key The key whose associated value is to be retrieved.
     * @return The value associated with the specified key, or null if the key is not present.
     * @throws IllegalArgumentException If the provided key is null.
     * @see ContextStore#map
     */
    public static synchronized <K, V> V get(K key) {
        return key != null ? ((ConcurrentHashMap<K, V>) map.get()).get(key) : null;
    }

    /**
     * Retrieves the value associated with the specified key from the ContextStore.
     * If the key is not present in the ContextStore, the provided defaultValue is returned.
     * This method is synchronized to ensure thread safety during the retrieval process.
     *
     * @param <K>           The type of the keys in the map.
     * @param <V>           The type of the values in the map.
     * @param key           The key whose associated value is to be retrieved.
     * @param defaultValue  The default value to be returned if the key is not present in the map.
     * @return The value associated with the specified key, or the defaultValue if the key is not present.
     * @throws IllegalArgumentException If the provided key is null.
     * @see ContextStore#map
     */
    public static synchronized <K, V> V get(K key, V defaultValue) {
        return key != null && get(key) != null ? get(key): defaultValue;
    }

    /**
     * Retrieves the boolean value associated with the specified key from the ContextStore.
     * The method retrieves the value, converts it to a String, and uses Boolean.parseBoolean().
     * Since Boolean.parseBoolean returns false for non-"true" strings (including null and invalid values),
     * this method will return {@code false} if the key is not present, if the value is null,
     * or if the value's string representation is anything other than "true" (case-insensitive).
     *
     * @param key The key whose associated boolean value is to be retrieved.
     * @return The boolean value based on the key's string representation, or {@code false} if the key is not present or the value is not "true".
     * @throws IllegalArgumentException If the provided key is null.
     * @see ContextStore#get(Object)
     */
    public static synchronized boolean getBoolean(Object key) {
        Object value = get(key);
        return Boolean.parseBoolean(value != null ? value.toString() : null);
    }

    /**
     * Retrieves the boolean value associated with the specified key from the ContextStore.
     * The method retrieves the value, converts it to a String, and uses Boolean.parseBoolean().
     * If the key is not present in the map (and {@code get(key)} returns null), the
     * {@code defaultValue} is returned. Otherwise, {@code Boolean.parseBoolean(value.toString())}
     * is used, which returns {@code false} for non-"true" strings.
     *
     * @param key           The key whose associated boolean value is to be retrieved.
     * @param defaultValue  The default boolean value to be returned if the key is not present.
     * @return The boolean value based on the key's string representation, or the defaultValue if the key is not present.
     * @throws IllegalArgumentException If the provided key is null.
     * @see ContextStore#get(Object)
     */
    public static synchronized boolean getBoolean(Object key, boolean defaultValue) {
        Object value = get(key);
        if (value == null)
            return defaultValue;

        return Boolean.parseBoolean(value.toString());
    }

    /**
     * Retrieves the int value associated with the specified key from the ContextStore.
     * The method attempts to parse the value (after converting to String) into an int.
     * If the key is not present or parsing fails (NumberFormatException), the value {@code 0} is returned.
     *
     * @param key The key whose associated int value is to be retrieved.
     * @return The int value associated with the specified key, or {@code 0} if the key is not present or parsing fails.
     * @throws IllegalArgumentException If the provided key is null.
     * @see ContextStore#get(Object)
     */
    public static synchronized int getInt(Object key) {
        return getInt(key, 0);
    }

    /**
     * Retrieves the int value associated with the specified key from the ContextStore.
     * The method attempts to parse the value (after converting to String) into an int.
     * If parsing fails or the key is not present, the {@code defaultValue} is returned.
     *
     * @param key           The key whose associated int value is to be retrieved.
     * @param defaultValue  The default int value to be returned if parsing fails or the key is not present.
     * @return The int value associated with the specified key, or the defaultValue if parsing fails.
     * @throws IllegalArgumentException If the provided key is null.
     * @see ContextStore#get(Object)
     */
    public static synchronized int getInt(Object key, int defaultValue) {
        Object value = get(key);
        if (value == null)
            return defaultValue;

        try {return Integer.parseInt(value.toString());}
        catch (NumberFormatException ignored) {return defaultValue;}
    }

    /**
     * Retrieves the double value associated with the specified key from the ContextStore.
     * The method attempts to parse the value (after converting to String) into a double.
     * If the key is not present or parsing fails (NumberFormatException), the value {@code 0.0} is returned.
     *
     * @param key The key whose associated double value is to be retrieved.
     * @return The double value associated with the specified key, or {@code 0.0} if the key is not present or parsing fails.
     * @throws IllegalArgumentException If the provided key is null.
     * @see ContextStore#get(Object)
     */
    public static synchronized double getDouble(Object key) {
        return getDouble(key, 0.0);
    }

    /**
     * Retrieves the double value associated with the specified key from the ContextStore.
     * The method attempts to parse the value (after converting to String) into a double.
     * If parsing fails or the key is not present, the {@code defaultValue} is returned.
     *
     * @param key           The key whose associated double value is to be retrieved.
     * @param defaultValue  The default double value to be returned if parsing fails or the key is not present.
     * @return The double value associated with the specified key, or the defaultValue if parsing fails.
     * @throws IllegalArgumentException If the provided key is null.
     * @see ContextStore#get(Object)
     */
    public static synchronized double getDouble(Object key, double defaultValue) {
        Object value = get(key);
        if (value == null)
            return defaultValue;

        try {return Double.parseDouble(value.toString());}
        catch (NumberFormatException ignored) {return defaultValue;}
    }

    /**
     * Retrieves an unmodifiable set view of the keys contained in the ContextStore.
     * This method is synchronized to ensure thread safety during the retrieval process.
     *
     * @param <K> The type of the keys in the ContextStore.
     * @param <V> The type of the values in the ContextStore.
     * @return An unmodifiable set view of the keys in the ContextStore.
     * @see ContextStore#map
     */
    public static synchronized <K, V> Set<K> items() {
        return Collections.unmodifiableSet(((ConcurrentHashMap<K, V>) map.get()).keySet());
    }

    /**
     * Clears all key-value mappings from the ContextStore.
     * This method is synchronized to ensure thread safety during the clear operation.
     *
     * @see ContextStore#map
     */
    public static synchronized void clear() {
        map.get().clear();
    }

    /**
     * Checks whether the ContextStore contains the specified key.
     *
     * @param <K> The type of the key in the ContextStore.
     * @param key The key to check for existence in the ContextStore.
     * @return true if the ContextStore contains the key, otherwise false.
     */
    public static synchronized <K, V> boolean has(K key){
        return map.get().get(key) != null;
    }

    /**
     * Updates the value associated with the specified key in the ContextStore.
     * If the key or value is null, the update operation is skipped.
     * This method is synchronized to ensure thread safety during the update process.
     *
     * @param <K>   The type of the keys in the ContextStore.
     * @param <V>   The type of the values in the ContextStore.
     * @param key   The key whose associated value is to be updated.
     * @param value The new value to be associated with the specified key.
     * @throws IllegalArgumentException If either the provided key or value is null.
     * @see ContextStore#map
     */
    public static synchronized <K, V> void update(K key, V value) {
        if (key != null && value != null) {
            ((ConcurrentHashMap<K, V>) map.get()).computeIfPresent(key, (k, oldValue) -> value);
        }
    }

    /**
     * Merges the entries from the specified map into the ContextStore.
     * This method is synchronized to ensure thread safety during the merge operation.
     *
     * @param maps Maps containing entries to be merged into the ContextStore.
     * @see ContextStore#map
     */
    public static synchronized void merge(Map<?, ?>... maps) {
        for (Map<?, ?> map:maps) ContextStore.map.get().putAll(map);
    }

    /**
     * Loads properties from one or more property files, merging them into the ContextStore for the current thread.
     * The method is synchronized to ensure thread safety during the loading and merging process.
     *
     * @param propertyNames An array of property file names or paths to be loaded and merged.
     * @throws IllegalArgumentException If the provided array of property names is null or empty.
     * @throws RuntimeException If an error occurs during the loading or merging of properties.
     *                          This can be an IOException or any other runtime exception.
     *                          The specific exception details are logged for further investigation.
     * @see PropertyUtilities#fromPropertyFile(String) fromPropertyFile(String)
     * @see ContextStore#merge(Map...) UtilityPropertiesMap.merge(Map)
     * @see ContextStore#map
     */
    public static synchronized void loadProperties(String... propertyNames) {
        for (String propertyName : propertyNames) merge(fromPropertyFile(propertyName));
    }

    /**
     * Merges **all** system properties into the {@link ContextStore} that is bound to the
     * current thread.
     *
     * <p>This method is {@code synchronized} so that concurrent threads cannot corrupt the
     * store while it is being populated.  Internally it delegates to
     * {@link ContextStore#merge(Map...)} with the map returned by
     * {@link System#getProperties()}.</p>
     *
     * <p>The operation does **not** throw checked exceptions; any
     * {@code IOException}, {@code UncheckedIOException} or other runtime exceptions that
     * may be thrown by {@code ContextStore#merge(..)} are propagated to the caller.
     * Those exceptions are logged by the underlying store implementation for further
     * diagnostic purposes.</p>
     *
     * @see System#getProperties()  – the source of the property map that is merged
     * @see ContextStore#merge(Map...)  – merges the supplied map into the thread‑local store
     *
     * @throws IllegalArgumentException if {@code System.getProperties()} somehow returns
     *         {@code null} (this cannot happen with a standard JVM, but the check is
     *         performed defensively by {@link ContextStore#merge(Map...)}).
     *
     */
    public static synchronized void loadSystemProperties() {
        merge(System.getProperties());
    }

    static {
        loadProperties("test.properties", "pom.properties", "app.properties", "pickleib.properties");
    }

}
