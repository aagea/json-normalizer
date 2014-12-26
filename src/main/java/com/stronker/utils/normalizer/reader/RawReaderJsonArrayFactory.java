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
import com.stronker.utils.normalizer.data.*;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class RawReaderJsonArrayFactory extends RawReaderFactory {
    @Override
    public RawReader getInstance(Reader reader) {
        return new RawReaderJsonArray(reader);
    }

    private final class RawReaderJsonArray extends RawReader {
        private final JsonReader jsonReader;
        private boolean init = false;
        private boolean eof = false;

        private RawReaderJsonArray(Reader reader) {
            super(reader);
            jsonReader = new JsonReader(reader);
        }

        @Override
        public ObjectData getNextElement() throws IOException {
            ObjectData result = null;
            if (!init) {
                jsonReader.beginArray();
                init = true;
            }

            if (jsonReader.hasNext()) {
                result = nextObject();
            } else {
                throw new EOFException();
            }

            if (!jsonReader.hasNext()) {
                jsonReader.endArray();
                eof = true;
            }
            return result;

        }

        private ObjectData nextObject() throws IOException {
            List<RawData> result = new ArrayList<RawData>();
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                RawData element = nextElement();
                if (element != null) {
                    result.add(element);
                }
            }
            jsonReader.endObject();
            return new ObjectData(result);
        }

        private ListData nextArray() throws IOException {
            List<ValueData> result = new ArrayList<ValueData>();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                JsonToken token = jsonReader.peek();
                switch (token) {
                    case BEGIN_ARRAY:
                        result.add(nextArray());
                        break;
                    case BEGIN_OBJECT:
                        result.add(nextObject());
                        break;
                    case STRING:
                        result.add(new StringData(jsonReader.nextString()));
                        break;
                    case NUMBER:
                        result.add(new IntData(jsonReader.nextInt()));
                        break;
                    case BOOLEAN:
                        result.add(new BooleanData(jsonReader.nextBoolean()));
                        break;
                    case NULL:
                        break;
                }
            }
            jsonReader.endArray();
            return new ListData(result);
        }

        private RawData nextElement() throws IOException {
            String name = jsonReader.nextName();
            JsonToken token = jsonReader.peek();
            RawData result = null;
            switch (token) {
                case BEGIN_ARRAY:
                    result = new RawData(name, nextArray());
                    break;
                case BEGIN_OBJECT:
                    result = new RawData(name, nextObject());
                    break;
                case STRING:
                    result = new RawData(name, new StringData(jsonReader.nextString()));
                    break;
                case NUMBER:
                    result = new RawData(name, new IntData(jsonReader.nextInt()));
                    break;
                case BOOLEAN:
                    result = new RawData(name, new BooleanData(jsonReader.nextBoolean()));
                    break;
                case NULL:
                    break;
            }

            return result;
        }

        @Override
        public boolean isEOF() {
            return eof;
        }
    }
}
