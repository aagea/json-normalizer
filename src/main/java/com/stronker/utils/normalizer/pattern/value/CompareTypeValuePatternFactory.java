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

package com.stronker.utils.normalizer.pattern.value;

import com.stronker.utils.normalizer.data.IElement;
import com.stronker.utils.normalizer.data.ValueType;

/**
 * Factory of CompareTypeValuePattern.
 */
public  final class CompareTypeValuePatternFactory implements IValuePatternFactory {

    public CompareTypeValuePatternFactory() {

    }

    @Override
    public IValuePattern getInstance(String pattern) {
        return new CompareTypeValuePattern(pattern);
    }

    /**
     * Compare value types with a pattern. It's compatible with type parent dependencies.
     */
    private static final class CompareTypeValuePattern extends AbstractValuePattern {

        private CompareTypeValuePattern(String pattern) {
            super(pattern);
        }

        @Override
        public boolean match(IElement value) {
            final ValueType valueTypePattern = ValueType.parse(this.getPattern());
            return this.check(valueTypePattern, value.getType());
        }

        private boolean check(ValueType pattern, ValueType valueType) {
            return pattern == valueType || valueType.getParent() != null && this.check(pattern, valueType.getParent());
        }

    }
}
