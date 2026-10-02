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

// TODO ishiyama せっかくなので自分名前を刻みましょう、authorの追加をお願いします by jflute (2026/10/02)
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
    // #1on1: $quantityという名前から、トータル的な在庫スタイルなのかな？と思ったけど... (2026/10/02)
    // $エクササイズやっていく上で、別物扱いされてるかな？とも思った。
    // 実装コードの方を優先したというのが良いと思いました。
    // quantityだけシンプルな名前になっているのがちょっとした罠。
    // #1on1: 在庫分ける方向にしたとして、quantityをoneDayPassportQuantityにするの迷わなかったか (2026/10/02)
    // $迷わん
    // ここでまたおじゃまします感話。
    // プルリクの差分の行数を減らすプレッシャーを持ってたり...
    //  → $であれば、コミットを分けてあげると工夫はできる
    // quantityのままだとしたら、トータル感が出やすいので、直した方がベターだと思う。
    // コード体裁デザインのバランスの話につながる。
    // twoDayが追加されたことによって、このクラスのバランスは変わったと言える。
    // なので、今のバランスに合わせた体裁にする責任は、いまこの修正をする人にある。
    // $keep it simple で、多少将来が見えてたとしても、今実装はしない方がいいか？
    // 確かに、ケースバイケースにはなります。自信があるか？それだけのスキルがあるか？
    // 無難なのは keep it simple と言える。
    // やるにしても、その意図をしっかりコメントにしておく。
    // コメントあれば、違う方向に進んだとしても、後の人ががっつり直すことを決断しやすい。
    //
    // keep it simple でも、思考は easy であってはいけない。
    // oneDayニュアンスとtotalニュアンスはしっかり見出して切り分けてないといけない。
    //
    // jflute的には、keep it obvious の方がしっくりくるかも。
    // (simpleはeasyと混同されやすい宿命にあるかも!?)
    //
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
    // TODO ishiyama @returnを追加で by jflute (2026/10/02)
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public Ticket buyOneDayPassport(Integer handedMoney) {
        // #1on1: $例外までメソッド化すると流れがわかりにくかもと思った!? (2026/10/02)
        // 確かに例外throw隠蔽しすぎると、どんな前提条件をクリアしてるのかわかりにくくなる。
        // 程度の問題もあるかも。e.g. validatePurchable() とかでまとめることは多い。
        // $validateならわかりやすいかも。
        // 前提条件のチェックなのか？サブの処理なのか？
        // 一方で、流れを重視するなら、
        // assertQuantityExists();
        // assertHandedMoneyEnough();
        // というようにallでまとめるのではなく、個別にチェックを表現するようにするとか。
        // (ただ、1こ1こはメソッド化してしまう)
        // assertと言ったら、ダメだったときは例外が投げられるというイメージ。
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

    // TODO ishiyama canBeBought() でもいいかな by jflute (2026/10/02)
    // 戻り値がbooleanな時点で、checkIfのニュアンスがすでに入っている。
    // さらに、booleanのメソッド名、助動詞よく使われる。(世界的な慣習として)
    // TODO ishiyama @throws消し忘れ ($最初validateイメージだった) by jflute (2026/10/02)
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
    // #1on1: 直したのGood (2026/10/02)
    // getQuantity()の呼び出し側が、OneDayニュアンスなのか？Totalニュアンスなのか？
    // そこは気にしないといけないところ。
    // IntelliJを使おうがなんにせよ、呼び出し側一箇所一箇所を見て辻褄が合うか確認は必要。
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
