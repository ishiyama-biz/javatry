package org.docksidestage.bizfw.basic.buyticket;

// done ishiyama 大事な空行を目立たせるために、些細な空行もしっかり管理する by jflute (2026/10/14)
// クラスJavaDocの直下の空行、統一性ために削除。意味のない空行は意味のある空行をぼかしてしまう。
// 意味のない空行はどんどん削除した方が良い。
/**
 * @author kazuki.ishiyama
 */
public class TicketBuyResult {
    // done ishiyama まあimmutable想定なので、finalを付けてもいいかなと (安全性と可読性のために) by jflute (2026/10/14)
    // $久しぶりだった...
    private final Ticket ticket;
    private final Integer change;

    public TicketBuyResult(Ticket ticket, Integer change) {
        this.ticket = ticket;
        this.change = change;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Integer getChange() {
        return change;
    }
}
