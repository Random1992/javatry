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

import java.util.ArrayList;
import java.util.List;

import org.docksidestage.bizfw.basic.supercar.SupercarManufacturer.Supercar;

/**
 * The client(顧客) of supercar.
 * @author jflute
 */
public class SupercarClient {

    private final List<Supercar> myCarList = new ArrayList<>(4);

    public void buySupercar() {
            SupercarDealer dealer = createDealer();
            String clientRequirement = "steering wheel has many shop";
        try {
            Supercar orderedCustomCar = dealer.orderSupercar(clientRequirement);
            myCarList.add(orderedCustomCar);
        } catch (SupercarDealer.SupercarDealerCannotMakeBySpecException e){
            throw new SupercarClientCannotMakeBySpecException("The SupercarClient function is ended with error..",e);
        }
    }

    protected SupercarDealer createDealer() {
        return new SupercarDealer();
    }

    public static class SupercarClientCannotMakeBySpecException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public SupercarClientCannotMakeBySpecException(String msg, Throwable cause) {
            super(msg,cause);
        }
    }
}
