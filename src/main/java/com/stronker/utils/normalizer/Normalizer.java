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


import com.stronker.utils.normalizer.data.NormalizedData;
import com.stronker.utils.normalizer.data.ObjectNormalData;
import com.stronker.utils.normalizer.data.RawData;
import com.stronker.utils.normalizer.pattern.IPattern;
import com.stronker.utils.normalizer.reader.IRawReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class to normalize documents.
 */
public final class Normalizer {
    private static final Logger LOG= LoggerFactory.getLogger(Normalizer.class);

    private final IRawReader reader;
    private final NormalizerConfiguration configuration;

    /**
     * Create a new Normalizer using a reader and a configuration.
     *
     * @param reader        Object that extract ObjectData.
     * @param configuration Basic configuration.
     */
    public Normalizer(IRawReader reader, NormalizerConfiguration configuration) {
        this.reader = reader;
        this.configuration = configuration;

    }

    /**
     * Get the reader instance.
     *
     * @return Reader instance.
     */
    public IRawReader getReader() {
        return this.reader;
    }

    /**
     * Get the configuration.
     *
     * @return Configuration instance.
     */
    public NormalizerConfiguration getConfiguration() {
        return this.configuration;
    }

    /**
     * Normalize an object and return her information.
     *
     * @return A map with the information normalize.
     * @throws IOException Inherit exception.
     */
    public Map<String, String> getNext() throws IOException {
        if (this.reader.isEOF()) {
            LOG.error("You release the reader [EOFException].");
            throw new EOFException();
        }
        LOG.debug("---> Get next element.");
        final ObjectNormalData element = this.reader.getNextElement();
        return this.normalize(element);
    }

    private Map<String, String> normalize(ObjectNormalData element) {
        final Map<String, String> result = new HashMap<String, String>(element.getValue().size());
        for (RawData rawData : element.getValue()) {
            final NormalizedData data = this.normalize(rawData);
            if (data != null) {
                result.put(data.getKey(), data.getValue());
            }
        }
        LOG.debug("Result: {} Processed: {}",result.size(),element.getValue().size());
        return result;
    }

    private NormalizedData normalize(RawData data) {
        NormalizedData result = null;
        for (IPattern pattern : this.configuration.getPatterns()) {
            result = pattern.apply(data);
            if (result != null) {
                break;
            }
        }
        return result;
    }
}
