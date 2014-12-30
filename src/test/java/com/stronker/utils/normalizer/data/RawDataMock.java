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

package com.stronker.utils.normalizer.data;

import java.util.ArrayList;
import java.util.List;

public class RawDataMock {
    public final static String STRING_RAW_DATA = "test";
    public final static boolean BOOLEAN_RAW_DATA = true;
    public final static int INT_RAW_DATA = 42;

    private final RawData booleanRawData;
    private final RawData intRawData;
    private final RawData stringRawData;
    private final RawData objectRawData;
    private final RawData listRawData;


    public RawDataMock() {
        List<RawData> rawDataList = new ArrayList<RawData>();
        List<IElement> elementList = new ArrayList<IElement>();

        BooleanElement booleanElement = new BooleanElement(BOOLEAN_RAW_DATA);
        elementList.add(booleanElement);
        this.booleanRawData = new RawData("boolean", booleanElement);
        rawDataList.add(this.booleanRawData);

        IntElement intElement = new IntElement(INT_RAW_DATA);
        elementList.add(intElement);
        this.intRawData = new RawData("int", intElement);
        rawDataList.add(this.intRawData);

        StringElement stringElement = new StringElement(STRING_RAW_DATA);
        elementList.add(stringElement);
        this.stringRawData = new RawData("string", stringElement);
        rawDataList.add(this.stringRawData);

        List<RawData> objectRawDataList = new ArrayList<RawData>(rawDataList.size());
        objectRawDataList.addAll(rawDataList);
        ObjectElement objectElement = new ObjectElement(objectRawDataList);
        elementList.add(objectElement);
        this.objectRawData = new RawData("object", objectElement);

        ListElement listElement = new ListElement(elementList);
        this.listRawData = new RawData("list", listElement);
    }

    public RawData getObjectRawData() {
        return this.objectRawData;
    }

    public RawData getListRawData() {
        return this.listRawData;
    }

    public RawData getStringRawData() {
        return this.stringRawData;
    }

    public RawData getIntRawData() {
        return this.intRawData;
    }

    public RawData getBooleanRawData() {
        return this.booleanRawData;
    }
}
