package org.docksidestage.bizfw.basic.buyticket;

public class TicketGet implements Ticket {
    private int times;
    private int price;
    private boolean alreadyIn;
    public TicketGet(int type, int price) {
        this.times = type;
        this.price = price;
    }

    public void doInPark() {
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + price);
        }
        if (times < 1) {
            throw new IllegalStateException("Not enough times!");
        }
        --times;
        alreadyIn = true;
    }

    public int getDisplayPrice() {
        return price;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }

    public int ChanceLeft() {
        return times;
    }
}
