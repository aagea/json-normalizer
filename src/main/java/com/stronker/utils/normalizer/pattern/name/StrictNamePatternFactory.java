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

package com.stronker.utils.normalizer.pattern.name;

/**
 * Factory to create new instance of StrictNamePattern. This pattern is only true if the name is equal to the pattern.
 */
public final class StrictNamePatternFactory implements INamePatternFactory {

    /**
     * Empty constructor.
     */
    public StrictNamePatternFactory() {

    }

    /**
     * Get a new instance of StrictNamePattern.
     *
     * @param pattern Compare pattern.
     * @return New instance of StrictNamePattern.
     */
    @Override
    public INamePattern getInstance(String pattern) {
        return new StrictNamePattern(pattern);
    }

    /**
     * This pattern is only true if the name is equal to the pattern.
     */
    private static final class StrictNamePattern extends AbstractNamePattern {

        private StrictNamePattern(String pattern) {
            super(pattern);
        }

        /**
         * Compare names with the pattern.
         *
         * @param value Name of the document.
         * @return True, if it is equals.
         */
        @Override
        public boolean match(String value) {
            return this.getPattern().equals(value);
        }
    }
}
