package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult {
    private final int change;
    private TicketType type;


    public TicketBuyResult(int Money, TicketType type) {
        this.change = Money - type.getPrice();
        this.type = type;
    }
    public TicketGet getTicket() {
        TicketGet x = new TicketGet(type);
        return x;
    }

    public int getChange() {
        return change;
    }
}
