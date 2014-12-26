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

package com.stronker.utils.normalizer;


import com.stronker.utils.normalizer.data.NormalizedData;
import com.stronker.utils.normalizer.data.ObjectData;
import com.stronker.utils.normalizer.data.RawData;
import com.stronker.utils.normalizer.pattern.Pattern;
import com.stronker.utils.normalizer.reader.RawReader;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Normalizer {
    private final RawReader reader;
    private final NormalizerConfiguration configuration;

    public Normalizer(RawReader reader, NormalizerConfiguration configuration){
        this.reader=reader;
        this.configuration=configuration;

    }

    public RawReader getReader() {
        return reader;
    }

    public NormalizerConfiguration getConfiguration() {
        return configuration;
    }


    public Map<String,String> getNext() throws IOException{
        Map<String,String> result;
        if(reader.isEOF()){
            throw new EOFException();
        }else {
            ObjectData element = reader.getNextElement();
            result=normalize(element);
        }

        return result;
    }

    private Map<String,String> normalize(ObjectData element){
        Map<String,String> result=new HashMap<String, String>();
        for (RawData rawData:element.getValue()){
            NormalizedData data=normalize(rawData);
            if(data!=null){
                result.put(data.getKey(),data.getValue());
            }
        }
        return result;
    }

    private NormalizedData normalize(RawData data){
        NormalizedData result=null;
        for(Pattern pattern: configuration.getPatterns()){
            result=pattern.apply(data);
            if(data!=null){
                break;
            }
        }
        return  result;
    }
}
