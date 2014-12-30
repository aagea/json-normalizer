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

package com.stronker.utils.normalizer.pattern.function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.stronker.utils.normalizer.data.NormalizedData;
import com.stronker.utils.normalizer.data.RawDataMock;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToStringFunctionPatternFactoryTest {
    private final static Logger LOG = LoggerFactory.getLogger(ToStringFunctionPatternFactoryTest.class);
    private final RawDataMock dataMock = new RawDataMock();

    @Test
    public void instanceToStringFunctionPatternFactory() {
        LOG.info("Launching instanceToStringFunctionPatternFactory...");
        ToStringFunctionPatternFactory factory = new ToStringFunctionPatternFactory();
        IFunctionPattern functionPattern = factory.getInstance();
        assertNotNull(functionPattern);
    }

    @Test
    public void booleanTransformTest() {
        LOG.info("Launching booleanTransformTest...");
        ToStringFunctionPatternFactory factory = new ToStringFunctionPatternFactory();
        IFunctionPattern functionPattern = factory.getInstance();
        NormalizedData normalizedData = functionPattern.call(this.dataMock.getBooleanRawData());
        assertEquals(this.dataMock.getBooleanRawData().getName(), normalizedData.getKey());
        assertEquals(Boolean.toString(RawDataMock.BOOLEAN_RAW_DATA), normalizedData.getValue());
    }

    @Test
    public void intTransformTest() {
        LOG.info("Launching intTransformTest...");
        ToStringFunctionPatternFactory factory = new ToStringFunctionPatternFactory();
        IFunctionPattern functionPattern = factory.getInstance();
        NormalizedData normalizedData = functionPattern.call(this.dataMock.getIntRawData());
        assertEquals(this.dataMock.getIntRawData().getName(), normalizedData.getKey());
        assertEquals(Integer.toString(RawDataMock.INT_RAW_DATA), normalizedData.getValue());
    }

    @Test
    public void stringTransformTest() {
        LOG.info("Launching stringTransformTest...");
        ToStringFunctionPatternFactory factory = new ToStringFunctionPatternFactory();
        IFunctionPattern functionPattern = factory.getInstance();
        NormalizedData normalizedData = functionPattern.call(this.dataMock.getStringRawData());
        assertEquals(this.dataMock.getStringRawData().getName(), normalizedData.getKey());
        assertEquals(RawDataMock.STRING_RAW_DATA, normalizedData.getValue());
    }

    @Test
    public void objectTransformTest() {
        LOG.info("Launching objectTransformTest...");
        ToStringFunctionPatternFactory factory = new ToStringFunctionPatternFactory();
        IFunctionPattern functionPattern = factory.getInstance();
        NormalizedData normalizedData = functionPattern.call(this.dataMock.getObjectRawData());
        assertEquals(this.dataMock.getObjectRawData().getName(), normalizedData.getKey());
    }

    @Test
    public void listTransformTest() {
        LOG.info("Launching listTransformTest...");
        ToStringFunctionPatternFactory factory = new ToStringFunctionPatternFactory();
        IFunctionPattern functionPattern = factory.getInstance();
        NormalizedData normalizedData = functionPattern.call(this.dataMock.getListRawData());
        assertEquals(this.dataMock.getListRawData().getName(), normalizedData.getKey());
    }


}
