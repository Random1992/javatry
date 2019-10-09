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

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds;
    private int price;
    private int type;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth(int choice) {
        type=choice;
        confirmPrice(type);
    }
    public void confirmPrice(int choice){
        if (choice==1){price=ONE_DAY_PRICE;}
        else if (choice==2){price=TWO_DAY_PRICE;}
        else { throw new TicketBadChoiceException("Bad Choice");}
    }
    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    public TicketBuyResult buyPassport(int handedMoney){
        TicketBuyResult PassportResult=null;
        if(type==1){
            PassportResult= buyOneDayPassport(handedMoney);
        }
        if(type==2){
            PassportResult= buyTwoDayPassport(handedMoney);
        }
        return PassportResult;
    }
    public void buyPassportProcess(int handedMoney){
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity;
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + price;
        } else {
            salesProceeds = price;
        }
    }
    public TicketBuyResult buyOneDayPassport(int handedMoney) {
        buyPassportProcess(handedMoney);
        TicketBuyResult PassportResult = new TicketBuyResult(handedMoney,price,1);
        return PassportResult;
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney){
        buyPassportProcess(handedMoney);
        TicketBuyResult PassportResult = new TicketBuyResult(handedMoney,price,2);
        return PassportResult;
    }
    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketBadChoiceException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketBadChoiceException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
