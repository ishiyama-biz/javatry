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

import java.time.LocalDateTime;

/**
 * @author jflute
 * @author kazuki.ishiyama
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // #1on1: タグコメントについて。なぜ右？なぜ3行？のお話 (2026/10/14)
    // #1on1: タグコメントから、インスタンス変数のカテゴリ化について (2026/10/14)
    // 空行空けるだけでも違う。一番手軽なカテゴリ化。
    private final int displayPrice; // written on ticket, park guest can watch this
    private final int initialUses;
    private final boolean isOnlyNight;

    private int remainingUses;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(int displayPrice, int initialUses, boolean isOnlyNight) {
        this.displayPrice = displayPrice;
        // TODO ishiyama あんまり現場でやらない書き方なので、やらない方が無難かも!? by jflute (2026/10/26)
        // 文法的には正しいけど、残念ながらほぼ見かけないというところがあって...
        // レアなのかレアじゃないのかは人によるが...
        // (会社のプログラミングとOSSの自分のプログラミングの違いも)
        this.remainingUses = this.initialUses = initialUses;
        // TODO ishiyama 英語の文法的な正しさは置いておいて、nightOnly/onlyNightの業務用語は統一した方が良い by jflute (2026/10/26)
        // (英語の表現的に自然だからってことで変えるのはOKだけど、特になければ合わせた方が良い)
        this.isOnlyNight = isOnlyNight;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (isAvailable()) {
            throw new IllegalStateException("This ticket has no remaining uses : displayedPrice=" + displayPrice);
        }
        assertNightOnly();
        remainingUses--;
    }

    private void assertNightOnly() {
        if (!isOnlyNight) {
            return;
        }
        LocalDateTime nowDate = LocalDateTime.now();
        if (nowDate.getHour() < 17) {
            throw new RuntimeException("The night-only ticket is used before 17:00");
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    public int getInitialUses() {
        return initialUses;
    }

    // TODO ishiyama もうAccessor感じゃなくロジック感あるので、定義位置もAccessorの外へ by jflute (2026/10/26)
    // #1on1: $alreadyIn → invalid → (nightOnlyで変) → available (逆になった) (2026/10/26)
    // 実装は合ってたので、名前が逆転しちゃっていただけ。
    // TODO ishiyama 意味を正しましょう by jflute (2026/10/26)
    public boolean isAvailable() {
        return remainingUses <= 0;
    }

}
