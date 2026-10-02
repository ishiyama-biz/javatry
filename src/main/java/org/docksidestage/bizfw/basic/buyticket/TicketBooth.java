/*
 * Copyright 2019-2025 the original author or authors.
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
    private static final int MAX_ONE_DAY_PASSPORT_QUANTITY = 10;
    private static final int MAX_TWO_DAY_PASSPORT_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int oneDayPassportQuantity = MAX_ONE_DAY_PASSPORT_QUANTITY;
    private int twoDayPassportQuantity = MAX_TWO_DAY_PASSPORT_QUANTITY;
    private Integer salesProceeds; // null allowed: until first purchase

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // /**
    // * 1Dayパスポートを買う、パークゲスト用のメソッド。
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public Ticket buyOneDayPassport(Integer handedMoney) {
        if (oneDayPassportQuantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (!checkIfCanBeBought(handedMoney, ONE_DAY_PRICE)) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --oneDayPassportQuantity;
        addSalesProceeds(ONE_DAY_PRICE);
        return new Ticket(ONE_DAY_PRICE);
    }

    /**
     * Buy two-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public Integer buyTwoDayPassport(Integer handedMoney) {
        if (twoDayPassportQuantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (!checkIfCanBeBought(handedMoney, TWO_DAY_PRICE)) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        twoDayPassportQuantity--;
        addSalesProceeds(TWO_DAY_PRICE);
        return handedMoney - TWO_DAY_PRICE;
    }

    /**
     * 売り上げを追加
     * @param price 売れたものの価格
     */
    private void addSalesProceeds(Integer price) {
        if (salesProceeds != null) { // second or more purchase
            salesProceeds += price;
        } else { // first purchase
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

    /**
     * Check if the user can buy the ticket whose price is `price`.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @param price the price of ticket. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    private boolean checkIfCanBeBought(Integer handedMoney, Integer price) {
        return handedMoney >= price;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getOneDayPassportQuantity() {
        return oneDayPassportQuantity;
    }

    public int getTwoDayPassportQuantity() {
        return twoDayPassportQuantity;
    }
    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
