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

import java.io.IOException;

/**
 * Interface for creating new object element reader.
 */
public interface IRawReader {

    /**
     * Recovery new ObjectElement for different formats.
     * @return New Object element.
     * @throws IOException Problems with the source.
     */
    ObjectElement getNextElement() throws IOException;

    /**
     * Indicate if you release the end of file.
     * @return True if EOF.
     */
    boolean isEOF();
}
