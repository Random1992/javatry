/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.supercar;

import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jflute
 */
public class SupercarSteeringWheelComponentDB {

    private final Map<Integer, String> clincherSpecMap; // not null

    public SupercarSteeringWheelComponentDB() {
        clincherSpecMap = new HashMap<>();
        clincherSpecMap.put(1, "(Correct spec text)");
        clincherSpecMap.put(2, "(Correct spec text)");
        clincherSpecMap.put(3, "\\(^_^)/");
    }

    public String findClincherSpecText(Integer clincherSpecId) {
        String specText= clincherSpecMap.get(clincherSpecId);
//        if(specText=="\\(^_^)/"){
//            throw new IlegalSpecTextException("Not Specified Spec Text:"+specText);
//        }
        return specText;
    }

    public Map<Integer, String> getSlincherSpecMap() { // read-only
        return Collections.unmodifiableMap(clincherSpecMap);
    }

    public static class IlegalSpecTextException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public IlegalSpecTextException(String msg) {
            super(msg);
        }
    }
}
