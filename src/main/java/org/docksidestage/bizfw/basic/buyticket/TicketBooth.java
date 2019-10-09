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
package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;

    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15

    private static final int TWO_DAY_PRICE = 13200;

    private static final int FOUR_DAY_PRICE = 22400;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity_ONE = MAX_QUANTITY;
    private int quantity_TWO = MAX_QUANTITY;
    private int quantity_FOUR = MAX_QUANTITY;
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }
    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    public TicketBuyResult buyOneDayPassport(int handedMoney) {
        buyPassportProcess(handedMoney, ONE_DAY_PRICE, quantity_ONE);
        TicketBuyResult PassportResult = new TicketBuyResult(handedMoney, ONE_DAY_PRICE, 1);
        --quantity_ONE;
        return PassportResult;
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        buyPassportProcess(handedMoney, TWO_DAY_PRICE, quantity_TWO);
        TicketBuyResult PassportResult = new TicketBuyResult(handedMoney, TWO_DAY_PRICE, 2);
        --quantity_TWO;
        return PassportResult;
    }

    public TicketBuyResult buyFourDayPassport(int handedMoney) {
        buyPassportProcess(handedMoney, FOUR_DAY_PRICE, quantity_FOUR);
        TicketBuyResult PassportResult = new TicketBuyResult(handedMoney, FOUR_DAY_PRICE, 4);
        --quantity_FOUR;
        return PassportResult;
    }

    public void buyPassportProcess(int handedMoney, int price, int quantity) {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + price;
        } else {
            salesProceeds = price;
        }
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    public static class TicketUnkownTypeException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketUnkownTypeException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity_ONE() {
        return quantity_ONE;
    }

    public int getQuantity_TWO() {
        return quantity_TWO;
    }

    public int getQuantity_FOUR() {
        return quantity_FOUR;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
