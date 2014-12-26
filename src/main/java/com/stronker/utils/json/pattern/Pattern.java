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

package com.stronker.utils.json.pattern;

import com.stronker.utils.json.data.NormalizerData;
import com.stronker.utils.json.data.ValueType;
import com.stronker.utils.json.pattern.function.PatternFunction;
import com.stronker.utils.json.pattern.name.PatternName;
import com.stronker.utils.json.pattern.value.PatternValue;

public class Pattern {
    private final PatternName patternName;
    private final PatternValue patternValue;
    private final PatternFunction patternFunction;

    public Pattern(PatternName patternName, PatternValue patternValue, PatternFunction patternFunction) {
        this.patternName = patternName;
        this.patternValue = patternValue;
        this.patternFunction = patternFunction;
    }

    public PatternName getPatternName() {
        return patternName;
    }

    public PatternValue getPatternValue() {
        return patternValue;
    }

    public PatternFunction getPatternFunction() {
        return patternFunction;
    }

    public NormalizerData apply(String name, Object value, ValueType valueType){
        NormalizerData result=null;
        if(patternName.match(name) && patternValue.match(value,valueType)){
            result=patternFunction.call(name,value,valueType);
        }
        return result;
    }
}
