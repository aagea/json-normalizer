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

package com.stronker.utils.normalizer.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.stronker.utils.normalizer.data.ListElement;
import com.stronker.utils.normalizer.data.ObjectElement;
import com.stronker.utils.normalizer.data.ValueType;

import java.io.EOFException;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonArrayRawReaderFactoryTest {

    private final static Logger LOG = LoggerFactory.getLogger(JsonArrayRawReaderFactoryTest.class);

    private static final String NOT_ARRAY_JSON = "{ \"age\": 24, \"name\": \"TOM\" }";
    private static final String BASIC_ARRAY_JSON = "[{ \"age\": 24, \"active\": true , \"job\": null, \"name\": \"TOM\" }]";
    private static final String MULTILEVEL_JSON =
            "[ { \"name\": \"TOM\", \"age\" : 24, \"college\": { \"name\":" +
                    "\"Stanford\", \"state\":\"California\" } }, { \"name\": \"ANNE\", \"age\" : 21, \"college\":\n" +
                    "{ \"name\":\"Harvard\", \"state\":\"Massachusetts\" } } ]";
    private static final String MULTI_LIST_JSON =
            "[{ \"age\": 24, \"name\": \"TOM\", \"topics\":[\"computer\", \"ai\"] }]";

    private static final String BAD_FOMAT_JSON =
            "[{ \"age\": 24, \"name\": \"TOM\" ]";
    private static final String COMPLEX_ARRAY_JSON =
            "[ { \"complex\": [ { \"name\": \"TOM\" }, 12, true, \"test\", [ 1, 2, 3 ], null ] } ]";
    private static final String EMPTY_ARRAY_JSON = "[]";

    @Test
    public void instanceJsonArrayRawReaderFactoryTest() {
        LOG.info("Launching instanceJsonArrayRawReaderFactoryTest...");
        JsonArrayRawReaderFactory factory = new JsonArrayRawReaderFactory();
        Reader reader = this.getReader("This is a test");
        IRawReader rawReader = factory.getInstance(reader);
        assertNotNull(rawReader);
        assertEquals(reader, ((AbstractRawReader) rawReader).getReader());
    }

    @Test(expected = InvalidObjectException.class)
    public void notArrayJsonTest() throws IOException {
        LOG.info("Launching notArrayJsonTest...");
        JsonArrayRawReaderFactory factory = new JsonArrayRawReaderFactory();
        Reader reader = this.getReader(NOT_ARRAY_JSON);
        IRawReader rawReader = factory.getInstance(reader);
        rawReader.getNextElement();
    }

    @Test(expected = EOFException.class)
    public void basicArrayJsonTest() throws IOException {
        LOG.info("Launching basicArrayJsonTest...");
        JsonArrayRawReaderFactory factory = new JsonArrayRawReaderFactory();
        Reader reader = this.getReader(BASIC_ARRAY_JSON);
        IRawReader rawReader = factory.getInstance(reader);
        ObjectElement objectElement = rawReader.getNextElement();
        assertNotNull(objectElement);
        assertEquals(3, objectElement.getValue().size());
        assertTrue(rawReader.isEOF());
        rawReader.getNextElement();
    }

    @Test
    public void multiLevelJsonTest() throws IOException {
        LOG.info("Launching multiLevelJsonTest...");
        JsonArrayRawReaderFactory factory = new JsonArrayRawReaderFactory();
        Reader reader = this.getReader(MULTILEVEL_JSON);
        IRawReader rawReader = factory.getInstance(reader);

        ObjectElement objectElement = rawReader.getNextElement();
        assertNotNull(objectElement);
        assertEquals(3, objectElement.getValue().size());
        assertEquals(ValueType.OBJECT, objectElement.getValue().get(2).getValue().getType());
        assertFalse(rawReader.isEOF());

        objectElement = rawReader.getNextElement();
        assertNotNull(objectElement);
        assertEquals(3, objectElement.getValue().size());
        assertEquals(ValueType.OBJECT, objectElement.getValue().get(2).getValue().getType());
        assertTrue(rawReader.isEOF());
    }

    @Test
    public void multiListJsonTest() throws IOException {
        LOG.info("Launching multiListJsonTest...");
        JsonArrayRawReaderFactory factory = new JsonArrayRawReaderFactory();
        Reader reader = this.getReader(MULTI_LIST_JSON);
        IRawReader rawReader = factory.getInstance(reader);
        ObjectElement objectElement = rawReader.getNextElement();
        assertNotNull(objectElement);
        assertEquals(3, objectElement.getValue().size());
        assertEquals(ValueType.LIST, objectElement.getValue().get(2).getValue().getType());
        assertEquals(2, ((ListElement) objectElement.getValue().get(2).getValue()).getValue().size());
        assertTrue(rawReader.isEOF());
    }

    @Test
    public void complexArrayJsonTest() throws IOException {
        LOG.info("Launching complexArrayJsonTest...");
        JsonArrayRawReaderFactory factory = new JsonArrayRawReaderFactory();
        Reader reader = this.getReader(COMPLEX_ARRAY_JSON);
        IRawReader rawReader = factory.getInstance(reader);
        ObjectElement objectElement = rawReader.getNextElement();
        assertNotNull(objectElement);
        assertEquals(1, objectElement.getValue().size());
        assertEquals(ValueType.LIST, objectElement.getValue().get(0).getValue().getType());
    }

    @Test(expected = Exception.class)
    public void badFormatJsonTest() throws IOException {
        LOG.info("Launching badFormatJsonTest...");
        JsonArrayRawReaderFactory factory = new JsonArrayRawReaderFactory();
        Reader reader = this.getReader(BAD_FOMAT_JSON);
        IRawReader rawReader = factory.getInstance(reader);
        rawReader.getNextElement();
    }

    @Test
    public void emptyArrayJsonTest() throws IOException {
        LOG.info("Launching complexArrayJsonTest...");
        JsonArrayRawReaderFactory factory = new JsonArrayRawReaderFactory();
        Reader reader = this.getReader(EMPTY_ARRAY_JSON);
        IRawReader rawReader = factory.getInstance(reader);
        ObjectElement objectElement = rawReader.getNextElement();
        assertNull(objectElement);
        assertTrue(rawReader.isEOF());
    }


    private Reader getReader(String test) {
        return new StringReader(test);
    }

}
