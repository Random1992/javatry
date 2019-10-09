package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult {
    private final int change;
    private int price;
    private int type;

    public TicketBuyResult(int Money,int price, int type) {
        this.change = Money-price;
        this.price = price;
        this.type=type;
    }
    public Ticket getTicket() {
        Ticket x = null;
        if(type==1){x=new OneDayPassport(price);}
        else if(type==2){x=new TwoDayPassport(price);}
        else if(type==4){x=new FourDayPassport(price);}
        return x;
    }

    public int getChange() {
        return change;
    }
}
