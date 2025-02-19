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
package org.docksidestage.javatry.basic.st6.os;

/**
 * @author jflute
 */
public class St6OperationSystem {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private osType os;
    private String loginId;
    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public St6OperationSystem(osType os) {
        this.os=os;
    }
    // ===================================================================================
    //                                                                      User Directory
    //
    //                                                                      ==============
    protected String getFileSeparator() {
        return os.getSymbol();
    }

    protected String getUserDirectory() {
        return os.getDirectory() + loginId;
    }
    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }

    public void setloginId(String loginId){
        this.loginId=loginId;
    }
}
