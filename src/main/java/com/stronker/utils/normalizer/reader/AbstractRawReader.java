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

import com.stronker.utils.normalizer.data.ObjectElement;

import java.io.IOException;
import java.io.Reader;

/**
 * Helper to create new RawReader.
 */
public abstract class AbstractRawReader implements IRawReader {

    private final Reader reader;

    /**
     * Basic constructor.
     * @param reader Original source reader.
     */
    public AbstractRawReader(Reader reader) {

        this.reader = reader;
    }

    public final Reader getReader() {
        return this.reader;
    }

    @Override
    public abstract ObjectElement getNextElement() throws IOException;

    @Override
    public abstract boolean isEOF();


}
