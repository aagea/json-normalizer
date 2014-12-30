/*
 * Licensed to ALVARO AGEA under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  The ALVARO AGEA licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language
 */

package com.stronker.utils.normalizer;

import com.stronker.utils.normalizer.pattern.IPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Basic configuration to normalizer.
 */
public final class NormalizerConfiguration {

    private final List<IPattern> patterns = new ArrayList<IPattern>();

    /**
     * Create an empty configuration.
     */
    public NormalizerConfiguration() {
        this(new ArrayList<IPattern>());
    }

    /**
     * Define a configuration with a pattern list.
     *
     * @param patterns Pattern list.
     */
    public NormalizerConfiguration(IPattern... patterns) {
        this(Arrays.asList(patterns));
    }

    /**
     * Define a configuration with a pattern list.
     *
     * @param patterns Pattern list.
     */
    public NormalizerConfiguration(List<IPattern> patterns) {
        if (patterns != null && !patterns.isEmpty()) {
            this.patterns.addAll(patterns);
        }
    }

    /**
     * Instance of pattern list.
     *
     * @return Pattern list.
     */
    public List<IPattern> getPatterns() {
        return this.patterns;
    }
}
