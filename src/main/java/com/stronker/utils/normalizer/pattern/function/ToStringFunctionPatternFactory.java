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

/**
 * Factory for ToStringFunctionPattern. This pattern normalize object using the method toString().
 */
public final class ToStringFunctionPatternFactory implements IFunctionPatternFactory {

    /**
     * Empty constructor.
     */
    public ToStringFunctionPatternFactory() {

    }

    /**
     * Get a new instance from ToStringFunctionPattern.
     *
     * @return Instance of ToStringFunctionPattern.
     */
    @Override
    public IFunctionPattern getInstance() {
        return new ToStringFunctionPattern();
    }

    /**
     * IPattern function with ToString transformation.
     */
    private static final class ToStringFunctionPattern implements IFunctionPattern {
        private ToStringFunctionPattern() {

        }

        /**
         * Return normalize data using toString() method.
         *
         * @param rawData Not normalized data.
         * @return Nomalized data using toString().
         */
        @Override
        public NormalizedData call(RawData rawData) {
            return new NormalizedData(rawData.getName(), rawData.getValue().toString());
        }
    }

}
