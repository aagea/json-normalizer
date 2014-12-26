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

import com.stronker.utils.normalizer.data.NormalizedData;
import com.stronker.utils.normalizer.data.RawData;

public class PatternFunctionToStringFactory extends PatternFunctionFactory {
    @Override
    public PatternFunction getInstance() {
        return new PatternFunctionToString();
    }

    private final class PatternFunctionToString extends PatternFunction{
        private PatternFunctionToString(){

        }

        @Override
        public NormalizedData call(RawData rawData) {
            return new NormalizedData(rawData.getName(),rawData.getValue().toString());
        }
    }

}
