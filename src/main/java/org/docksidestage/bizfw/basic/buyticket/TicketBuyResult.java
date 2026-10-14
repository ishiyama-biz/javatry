package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author kazuki.ishiyama
 */

public class TicketBuyResult {
    private Ticket ticket;
    private Integer change;

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
