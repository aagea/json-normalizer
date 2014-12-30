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

package com.stronker.utils.normalizer.pattern;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.stronker.utils.normalizer.data.BooleanElement;
import com.stronker.utils.normalizer.data.RawData;
import com.stronker.utils.normalizer.pattern.function.FunctionPatternMock;
import com.stronker.utils.normalizer.pattern.function.IFunctionPattern;
import com.stronker.utils.normalizer.pattern.name.INamePattern;
import com.stronker.utils.normalizer.pattern.name.NamePatternMock;
import com.stronker.utils.normalizer.pattern.value.IValuePattern;
import com.stronker.utils.normalizer.pattern.value.ValuePatternMock;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatternTest {
    private final static Logger LOG = LoggerFactory.getLogger(PatternTest.class);

    private final RawData rawData = new RawData("test", new BooleanElement(true));
    private final IFunctionPattern functionPattern = new FunctionPatternMock();

    @Test
    public void instancePatternTest() {
        LOG.info("Launching instancePatternTest...");
        INamePattern namePattern = new NamePatternMock(true);
        IValuePattern valuePattern = new ValuePatternMock(true);
        Pattern pattern = new Pattern(namePattern, valuePattern, this.functionPattern);
        assertEquals(namePattern, pattern.getNamePattern());
        assertEquals(valuePattern, pattern.getValuePattern());
        assertEquals(this.functionPattern, pattern.getFunctionPattern());
    }

    @Test
    public void notMatchNamePatternTest() {
        LOG.info("Launching notMatchNamePatternTest...");
        INamePattern namePattern = new NamePatternMock(false);
        IValuePattern valuePattern = new ValuePatternMock(true);
        Pattern pattern = new Pattern(namePattern, valuePattern, this.functionPattern);
        assertNull(pattern.apply(rawData));
    }

    @Test
    public void notMatchValuePatternTest() {
        LOG.info("Launching notMatchValuePatternTest...");
        INamePattern namePattern = new NamePatternMock(true);
        IValuePattern valuePattern = new ValuePatternMock(false);
        Pattern pattern = new Pattern(namePattern, valuePattern, this.functionPattern);
        assertNull(pattern.apply(rawData));
    }

    @Test
    public void notMatchALLTest() {
        LOG.info("Launching notMatchALLTest...");
        INamePattern namePattern = new NamePatternMock(false);
        IValuePattern valuePattern = new ValuePatternMock(false);
        Pattern pattern = new Pattern(namePattern, valuePattern, this.functionPattern);
        assertNull(pattern.apply(rawData));
    }

    @Test
    public void matchTest() {
        LOG.info("Launching matchTest...");
        INamePattern namePattern = new NamePatternMock(true);
        IValuePattern valuePattern = new ValuePatternMock(true);
        Pattern pattern = new Pattern(namePattern, valuePattern, this.functionPattern);
        assertNotNull(pattern.apply(rawData));
    }


}
