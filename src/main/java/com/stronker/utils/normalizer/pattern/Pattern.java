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

package com.stronker.utils.normalizer.pattern;

import com.stronker.utils.normalizer.data.NormalizedData;
import com.stronker.utils.normalizer.data.RawData;
import com.stronker.utils.normalizer.pattern.function.IFunctionPattern;
import com.stronker.utils.normalizer.pattern.name.INamePattern;
import com.stronker.utils.normalizer.pattern.value.IValuePattern;

/**
 * Basic class to define transformation patterns.
 */
public final class Pattern implements IPattern {
    private final INamePattern namePattern;
    private final IValuePattern valuePattern;
    private final IFunctionPattern functionPattern;

    /**
     * Pattern constructor.
     *
     * @param namePattern     Filter to name attributes.
     * @param valuePattern    Filter to values and value types.
     * @param functionPattern Transformation function.
     */
    public Pattern(INamePattern namePattern, IValuePattern valuePattern, IFunctionPattern functionPattern) {
        this.namePattern = namePattern;
        this.valuePattern = valuePattern;
        this.functionPattern = functionPattern;
    }

    /**
     * Get the name pattern.
     *
     * @return Instance of name pattern.
     */
    public INamePattern getNamePattern() {
        return this.namePattern;
    }

    /**
     * Get the value pattern.
     *
     * @return Instance of value pattern.
     */
    public IValuePattern getValuePattern() {
        return this.valuePattern;
    }

    /**
     * Get the function pattern.
     *
     * @return Instance of function pattern
     */
    public IFunctionPattern getFunctionPattern() {
        return this.functionPattern;
    }

    /**
     * Transform raw data in normalized data, but the element must match the NamePattern and the ValuePattern.
     *
     * @param rawData Information no normalized.
     * @return Information Normalized or null if not match the patterns.
     */
    @Override
    public NormalizedData apply(RawData rawData) {
        NormalizedData result = null;
        if (this.namePattern.match(rawData.getName()) && this.valuePattern.match(rawData.getValue())) {
            result = this.functionPattern.call(rawData);
        }
        return result;
    }
}
