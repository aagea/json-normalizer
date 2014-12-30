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
import com.stronker.utils.normalizer.data.RawData;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RawReaderMock implements IRawReader {

    private List<ObjectElement> objects;
    private int index;

    public RawReaderMock() {
        this.objects = new LinkedList<ObjectElement>();
    }

    @Override
    public ObjectElement getNextElement() throws IOException {
        ObjectElement result = null;
        if (index < objects.size()) {
            result = this.objects.get(index++);
        }
        return result;
    }

    @Override
    public boolean isEOF() {
        return index >= objects.size();
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void add(RawData... data) {
        this.add(new ObjectElement(Arrays.asList(data)));
    }

    public void add(ObjectElement object) {
        objects.add(object);
    }
}
