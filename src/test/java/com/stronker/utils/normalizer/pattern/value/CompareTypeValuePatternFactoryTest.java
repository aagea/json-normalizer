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

package com.stronker.utils.normalizer.pattern.value;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.stronker.utils.normalizer.data.RawDataMock;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompareTypeValuePatternFactoryTest {
    private final static Logger LOG = LoggerFactory.getLogger(CompareTypeValuePatternFactoryTest.class);
    private final RawDataMock rawDataMock = new RawDataMock();

    @Test
    public void instanceCompareTypeValuePatternFactory() {
        LOG.info("Launching instanceCompareTypeValuePatternFactory...");
        CompareTypeValuePatternFactory factory = new CompareTypeValuePatternFactory();
        IValuePattern valuePattern = factory.getInstance("any");
        assertNotNull(valuePattern);
        assertEquals("any", ((AbstractValuePattern) valuePattern).getPattern());
    }

    @Test
    public void anyTypeTest() {
        LOG.info("Launching anyTypeTest...");
        CompareTypeValuePatternFactory factory = new CompareTypeValuePatternFactory();
        IValuePattern valuePattern = factory.getInstance("any");

        assertTrue(valuePattern.match(this.rawDataMock.getBooleanRawData().getValue()));
        assertTrue(valuePattern.match(this.rawDataMock.getIntRawData().getValue()));
        assertTrue(valuePattern.match(this.rawDataMock.getListRawData().getValue()));
        assertTrue(valuePattern.match(this.rawDataMock.getObjectRawData().getValue()));
        assertTrue(valuePattern.match(this.rawDataMock.getStringRawData().getValue()));
    }

    @Test
    public void simpleTypeTest() {
        LOG.info("Launching simpleTypeTest...");
        CompareTypeValuePatternFactory factory = new CompareTypeValuePatternFactory();
        IValuePattern valuePattern = factory.getInstance("simple-type");

        assertTrue(valuePattern.match(this.rawDataMock.getBooleanRawData().getValue()));
        assertTrue(valuePattern.match(this.rawDataMock.getIntRawData().getValue()));
        assertTrue(valuePattern.match(this.rawDataMock.getStringRawData().getValue()));

        assertFalse(valuePattern.match(this.rawDataMock.getListRawData().getValue()));
        assertFalse(valuePattern.match(this.rawDataMock.getObjectRawData().getValue()));
    }

    @Test
    public void objectTypeTest() {
        LOG.info("Launching objectTypeTest...");
        CompareTypeValuePatternFactory factory = new CompareTypeValuePatternFactory();
        IValuePattern valuePattern = factory.getInstance("object");
        assertFalse(valuePattern.match(this.rawDataMock.getListRawData().getValue()));
        assertTrue(valuePattern.match(this.rawDataMock.getObjectRawData().getValue()));
    }

    @Test
    public void listTypeTest() {
        LOG.info("Launching listTypeTest...");
        CompareTypeValuePatternFactory factory = new CompareTypeValuePatternFactory();
        IValuePattern valuePattern = factory.getInstance("list");
        assertTrue(valuePattern.match(this.rawDataMock.getListRawData().getValue()));
        assertFalse(valuePattern.match(this.rawDataMock.getObjectRawData().getValue()));
    }

}
