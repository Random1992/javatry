package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult {
    private final int handedMoney;
    private int price;
    private int choice;

    public TicketBuyResult(int Money,int price) {
        this.handedMoney = Money;
        this.price = price;
    }
    public Ticket getTicket() {
        Ticket x = null;
        if(choice==1){x=new OneDayPassport(price);}
        if(choice==2){x=new TwoDayPassport(price);}
        return x;
    }

    public int getChange() {
        return handedMoney-price;
    }
}
