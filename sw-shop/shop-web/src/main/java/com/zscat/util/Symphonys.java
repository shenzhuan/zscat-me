/*
 * Copyright (c) 2012-2016, b3log.org & hacpai.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zscat.util;

import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Symphony utilities.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.4.0.4, Sep 11, 2016
 * @since 0.1.0
 */
public final class Symphonys {

    /**
     * Configurations.
     */
    private static final ResourceBundle CFG = ResourceBundle.getBundle("resources");

    /**
     * HacPai bot User-Agent.
     */
    public static final String USER_AGENT_BOT = "Mozilla/5.0 (compatible; HacPaibot/1.0; +https://hacpai.com/)";

    

    /**
     * Thread pool.
     */
    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(50);

   

    

    /**
     * Gets a configuration string property with the specified key.
     *
     * @param key the specified key
     * @return string property value corresponding to the specified key, returns {@code null} if not found
     */
    public static String get(final String key) {
        return CFG.getString(key);
    }

    /**
     * Gets a configuration boolean property with the specified key.
     *
     * @param key the specified key
     * @return boolean property value corresponding to the specified key, returns {@code null} if not found
     */
    public static Boolean getBoolean(final String key) {
        final String stringValue = get(key);

        if (null == stringValue) {
            return null;
        }

        return Boolean.valueOf(stringValue);
    }

    /**
     * Gets a configuration float property with the specified key.
     *
     * @param key the specified key
     * @return float property value corresponding to the specified key, returns {@code null} if not found
     */
    public static Float getFloat(final String key) {
        final String stringValue = get(key);
        if (null == stringValue) {
            return null;
        }

        return Float.valueOf(stringValue);
    }

    /**
     * Gets a configuration integer property with the specified key.
     *
     * @param key the specified key
     * @return integer property value corresponding to the specified key, returns {@code null} if not found
     */
    public static Integer getInt(final String key) {
        final String stringValue = get(key);
        if (null == stringValue) {
            return null;
        }

        return Integer.valueOf(stringValue);
    }

    /**
     * Gets a configuration long property with the specified key.
     *
     * @param key the specified key
     * @return long property value corresponding to the specified key, returns {@code null} if not found
     */
    public static Long getLong(final String key) {
        final String stringValue = get(key);
        if (null == stringValue) {
            return null;
        }

        return Long.valueOf(stringValue);
    }

    /**
     * Private default constructor.
     */
    private Symphonys() {
    }
}
