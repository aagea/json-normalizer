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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.stronker.utils.normalizer.data.RawData;
import com.stronker.utils.normalizer.data.StringElement;
import com.stronker.utils.normalizer.pattern.NullPatternMock;
import com.stronker.utils.normalizer.pattern.SimplePatternMock;
import com.stronker.utils.normalizer.reader.IRawReader;
import com.stronker.utils.normalizer.reader.RawReaderMock;

import java.io.EOFException;
import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NormalizerTest {
    private final static Logger LOG = LoggerFactory.getLogger(NormalizerTest.class);

    @Test
    public void instanceNormalizerTest() {
        LOG.info("Launching instanceNormalizerTest...");
        NormalizerConfiguration normalizerConfiguration = new NormalizerConfiguration(new SimplePatternMock());
        IRawReader reader = new RawReaderMock();
        Normalizer normalizer = new Normalizer(reader, normalizerConfiguration);
        assertEquals(normalizerConfiguration, normalizer.getConfiguration());
        assertEquals(reader, normalizer.getReader());
    }

    @Test
    public void simpleGetNextTest() throws IOException {
        LOG.info("Launching simpleGetNextTest...");
        NormalizerConfiguration normalizerConfiguration = new NormalizerConfiguration(new SimplePatternMock());
        RawReaderMock reader = new RawReaderMock();
        reader.add(new RawData("test1", new StringElement("test1")));
        Normalizer normalizer = new Normalizer(reader, normalizerConfiguration);
        Map<String, String> result = normalizer.getNext();
        assertTrue(result.containsKey("test1"));
    }

    @Test(expected = EOFException.class)
    public void eofTest() throws IOException {
        LOG.info("Launching eofTest... expected EOFException");
        NormalizerConfiguration normalizerConfiguration = new NormalizerConfiguration(new SimplePatternMock());
        RawReaderMock reader = new RawReaderMock();
        reader.add(new RawData("test1", new StringElement("test1")));
        Normalizer normalizer = new Normalizer(reader, normalizerConfiguration);
        Map<String, String> result = normalizer.getNext();
        assertTrue(result.containsKey("test1"));
        normalizer.getNext();
    }

    @Test
    public void nullPatternTest() throws IOException {
        LOG.info("Launching nullPatternTest...");
        NormalizerConfiguration normalizerConfiguration = new NormalizerConfiguration();
        normalizerConfiguration.getPatterns().add(new NullPatternMock());
        RawReaderMock reader = new RawReaderMock();
        reader.add(new RawData("test1", new StringElement("test1")));
        Normalizer normalizer = new Normalizer(reader, normalizerConfiguration);
        Map<String, String> result = normalizer.getNext();
        assertTrue(result.isEmpty());
    }
}
