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

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.stronker.utils.normalizer.data.BooleanElement;
import com.stronker.utils.normalizer.data.IElement;
import com.stronker.utils.normalizer.data.IntElement;
import com.stronker.utils.normalizer.data.ListElement;
import com.stronker.utils.normalizer.data.ObjectElement;
import com.stronker.utils.normalizer.data.RawData;
import com.stronker.utils.normalizer.data.StringElement;

import java.io.EOFException;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

/**
 * Factory of JsonArrayRawReader.
 */
public final class JsonArrayRawReaderFactory implements IRawReaderFactory {

    public JsonArrayRawReaderFactory() {

    }

    @Override
    public IRawReader getInstance(Reader reader) {
        return new JsonArrayRawReader(reader);
    }

    /**
     * Read information in streaming of an array Json usin GSON library.
     */
    private static final class JsonArrayRawReader extends AbstractRawReader {
        private final JsonReader jsonReader;
        private boolean init;
        private boolean eof;

        private JsonArrayRawReader(Reader reader) {
            super(reader);
            this.jsonReader = new JsonReader(reader);
        }

        @Override
        public ObjectElement getNextElement() throws IOException {
            ObjectElement result = null;
            if (!this.init) {
                if (this.jsonReader.peek() != JsonToken.BEGIN_ARRAY) {
                    throw new InvalidObjectException("Expect JSON_ARRAY");
                }
                this.jsonReader.beginArray();
                this.init = true;
            }
            if (this.eof) {
                throw new EOFException();
            }

            if (this.jsonReader.hasNext()) {
                result = this.nextObject();
            }

            if (!this.jsonReader.hasNext()) {
                this.jsonReader.endArray();
                this.eof = true;
            }
            return result;

        }

        private ObjectElement nextObject() throws IOException {
            final List<RawData> result = new LinkedList<RawData>();
            this.jsonReader.beginObject();
            while (this.jsonReader.hasNext()) {
                final RawData element = this.nextElement();
                if (element != null) {
                    result.add(element);
                }
            }
            this.jsonReader.endObject();
            return new ObjectElement(result);
        }

        private ListElement nextArray() throws IOException {
            final List<IElement> result = new LinkedList<IElement>();
            this.jsonReader.beginArray();
            while (this.jsonReader.hasNext()) {
                final JsonToken token = this.jsonReader.peek();
                switch (token) {
                    case BEGIN_ARRAY:
                        result.add(this.nextArray());
                        break;
                    case BEGIN_OBJECT:
                        result.add(this.nextObject());
                        break;
                    case STRING:
                        result.add(new StringElement(this.jsonReader.nextString()));
                        break;
                    case NUMBER:
                        result.add(new IntElement(this.jsonReader.nextInt()));
                        break;
                    case BOOLEAN:
                        result.add(new BooleanElement(this.jsonReader.nextBoolean()));
                        break;
                    case NULL:
                        this.jsonReader.nextNull();
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
            this.jsonReader.endArray();
            return new ListElement(result);
        }

        private RawData nextElement() throws IOException {
            final String name = this.jsonReader.nextName();
            final JsonToken token = this.jsonReader.peek();
            RawData result = null;
            switch (token) {
                case BEGIN_ARRAY:
                    result = new RawData(name, this.nextArray());
                    break;
                case BEGIN_OBJECT:
                    result = new RawData(name, this.nextObject());
                    break;
                case STRING:
                    result = new RawData(name, new StringElement(this.jsonReader.nextString()));
                    break;
                case NUMBER:
                    result = new RawData(name, new IntElement(this.jsonReader.nextInt()));
                    break;
                case BOOLEAN:
                    result = new RawData(name, new BooleanElement(this.jsonReader.nextBoolean()));
                    break;
                case NULL:
                    this.jsonReader.nextNull();
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            return result;
        }

        @Override
        public boolean isEOF() {
            return this.eof;
        }
    }
}
