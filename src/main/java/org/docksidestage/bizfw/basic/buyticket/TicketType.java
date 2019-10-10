package org.docksidestage.bizfw.basic.buyticket;

public enum TicketType {
    OneDayTicket(1, 7400),
    TwoDayTicket(2,13200),
    FourDayTicket(2,22400);

    private int count;
    private int price;

    private TicketType(int count, int price) {
        this.count = count;
        this.price = price;
    }

    public int getCount(){
        return count;
    }

    public int getPrice(){
        return price;
    }
}
