package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult {
    private final int handedMoney;
    private int price;
    private int type;

    public TicketBuyResult(int Money,int price, int type) {
        this.handedMoney = Money;
        this.price = price;
        this.type=type;
    }
    public Ticket getTicket() {
        Ticket x = null;
        if(type==1){x=new OneDayPassport(price);}
        if(type==2){x=new TwoDayPassport(price);}
        return x;
    }

    public int getChange() {
        return handedMoney-price;
    }
}
