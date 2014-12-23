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

package com.stronker.utils.json.patterns;


public enum ValueType {
    ANY("any",null),
    SIMPLE_TYPE("simple-type",ANY),
    STRING("string",SIMPLE_TYPE),
    INT("int",SIMPLE_TYPE),
    BOOLEAN("boolean",SIMPLE_TYPE),

    OBJECT("object",ANY),

    LIST("list",ANY,ANY),
    LIST_STRING("list[string]",LIST,STRING),
    LIST_INT("list[int]",LIST,INT),
    LIST_BOOLEAN("list[boolean]",LIST,BOOLEAN),
    LIST_OBJECT("list[object]",LIST,OBJECT);


    private final ValueType parent;
    private final ValueType generic;
    private final String signature;

    ValueType( String signature,ValueType parent) {
        this(signature,parent,null);
    }
    ValueType(String signature,ValueType parent,ValueType generic){
        this.signature=signature;
        this.parent=parent;
        this.generic=generic;
    }

    public ValueType getParent() {
        return parent;
    }

    public ValueType getGeneric() {
        return generic;
    }

    public String getSignature() {
        return signature;
    }
    public static ValueType parse(String signature){
        for(ValueType valueType: ValueType.values()){
            if (valueType.signature.equals(signature)){
                return valueType;
            }
        }
        throw new IllegalArgumentException("This signature doesn't exists");
    }
}
