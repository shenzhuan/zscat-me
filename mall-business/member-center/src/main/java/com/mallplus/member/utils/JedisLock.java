package com.mallplus.member.utils;

import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.UUID;

/**
 * Redis distributed lock implementation.
 *
 * @author Alois Belaska <alois.belaska@gmail.com>
 */
public class JedisLock {

    /**
     * Lua script which allows for an atomic delete on the lock only
     * if it is owned by the lock. This prevents locks stealing from others.
     */
    private final static String DELETE_IF_OWNED =
            "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                    "return redis.call('del', KEYS[1]) " +
                    "else " +
                    "return 0 " +
                    "end";

    private Jedis jedis;

    /**
     * Lock key path.
     */
    private String lockKey;
    private String token;

    /**
     * Lock expiration in milliseconds.
     */
    private int expireMsecs = 60 * 1000;

    /**
     * Acquire timeout in milliseconds.
     */
    private int timeoutMsecs = 10 * 1000;

    private boolean locked = false;

    /**
     * Detailed constructor with default acquire timeout 10000 msecs and lock expiration of 60000 msecs.
     *
     * @param jedis
     * @param lockKey lock key (ex. account:1, ...)
     */
    public JedisLock(Jedis jedis, String lockKey) {
        this.jedis = jedis;
        this.lockKey = lockKey;
        this.token = UUID.randomUUID().toString();
    }

    /**
     * Detailed constructor with default lock expiration of 60000 msecs.
     *
     * @param jedis
     * @param lockKey      lock key (ex. account:1, ...)
     * @param timeoutMsecs acquire timeout in milliseconds (default: 10000 msecs)
     */
    public JedisLock(Jedis jedis, String lockKey, int timeoutMsecs) {
        this(jedis, lockKey);
        this.timeoutMsecs = timeoutMsecs;
    }

    /**
     * Detailed constructor.
     *
     * @param jedis
     * @param lockKey      lock key (ex. account:1, ...)
     * @param timeoutMsecs acquire timeout in milliseconds (default: 10000 msecs)
     * @param expireMsecs  lock expiration in milliseconds (default: 60000 msecs)
     */
    public JedisLock(Jedis jedis, String lockKey, int timeoutMsecs, int expireMsecs) {
        this(jedis, lockKey, timeoutMsecs);
        this.expireMsecs = expireMsecs;
    }

    /**
     * Detailed constructor with default acquire timeout 10000 msecs and lock expiration of 60000 msecs.
     *
     * @param lockKey lock key (ex. account:1, ...)
     */
    public JedisLock(String lockKey) {
        this(null, lockKey);
    }

    /**
     * Detailed constructor with default lock expiration of 60000 msecs.
     *
     * @param lockKey      lock key (ex. account:1, ...)
     * @param timeoutMsecs acquire timeout in miliseconds (default: 10000 msecs)
     */
    public JedisLock(String lockKey, int timeoutMsecs) {
        this(null, lockKey, timeoutMsecs);
    }

    /**
     * Detailed constructor.
     *
     * @param lockKey      lock key (ex. account:1, ...)
     * @param timeoutMsecs acquire timeout in miliseconds (default: 10000 msecs)
     * @param expireMsecs  lock expiration in miliseconds (default: 60000 msecs)
     */
    public JedisLock(String lockKey, int timeoutMsecs, int expireMsecs) {
        this(null, lockKey, timeoutMsecs, expireMsecs);
    }

    /**
     * @return lock key
     */
    public String getLockKey() {
        return lockKey;
    }

    /**
     * Acquire lock.
     *
     * @return true if lock is acquired, false acquire timeouted
     * @throws InterruptedException in case of thread interruption
     */
    public synchronized boolean acquire() throws InterruptedException {
        return acquire(jedis);
    }

    /**
     * Acquire lock.
     *
     * @param jedis
     * @return true if lock is acquired, false acquire timed out
     * @throws InterruptedException in case of thread interruption
     */
    public synchronized boolean acquire(Jedis jedis) throws InterruptedException {
        int timeout = timeoutMsecs;
        while (timeout >= 0) {

            if ("OK".equals(jedis.set(lockKey, token, "NX", "PX", expireMsecs))) {
                // lock acquired
                locked = true;
                return true;
            }

            timeout -= 100;
            Thread.sleep(100);
        }

        return false;
    }

    /**
     * Acquired lock release.
     */
    public synchronized void release() {
        release(jedis);
    }

    /**
     * Acquired lock release.
     */
    public synchronized void release(Jedis jedis) {
        if (locked) {
            // prevent threads from releasing locks which they don't own
            jedis.eval(DELETE_IF_OWNED, Arrays.asList(lockKey), Arrays.asList(token));
            locked = false;
        }
    }
}
