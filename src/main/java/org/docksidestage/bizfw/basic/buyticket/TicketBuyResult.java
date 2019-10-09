package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult {
    private final int change;
    private int price;
    private int type;

    public TicketBuyResult(int Money, int price, int type) {
        this.change = Money - price;
        this.price = price;
        this.type = type;
    }
    public TicketGet getTicket() {
        TicketGet x = new TicketGet(type, price);
        return x;
    }

    public int getChange() {
        return change;
    }
}
