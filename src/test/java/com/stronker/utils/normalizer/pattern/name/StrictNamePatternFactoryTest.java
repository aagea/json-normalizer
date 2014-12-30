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

package com.stronker.utils.normalizer.pattern.name;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StrictNamePatternFactoryTest {
    private final static Logger LOG = LoggerFactory.getLogger(StrictNamePatternFactoryTest.class);

    @Test
    public void instanceStrictNamePatternFactory() {
        LOG.info("Launching instanceStrictNamePatternFactory...");
        StrictNamePatternFactory factory = new StrictNamePatternFactory();
        INamePattern namePattern = factory.getInstance("test");
        assertNotNull(namePattern);
        assertEquals("test", ((AbstractNamePattern) namePattern).getPattern());
    }

    @Test
    public void notEqualsTest() {
        LOG.info("Launching notEqualsTest...");
        StrictNamePatternFactory factory = new StrictNamePatternFactory();
        INamePattern namePattern = factory.getInstance("test");
        assertFalse(namePattern.match("failTest"));
    }

    @Test
    public void equalsTest() {
        LOG.info("Launching equalsTest...");
        StrictNamePatternFactory factory = new StrictNamePatternFactory();
        INamePattern namePattern = factory.getInstance("test");
        assertTrue(namePattern.match("test"));
    }
}
