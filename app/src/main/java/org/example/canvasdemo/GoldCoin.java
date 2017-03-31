package org.example.canvasdemo;

/**
 * Created by Camilla on 16-03-2017.
 */

public class GoldCoin {
    private int coinx;
    private int coiny;
    private int h,w;
    private boolean taken;

    public GoldCoin(int coinx, int coiny, int h, int w, boolean taken) {
        this.coinx = coinx;
        this.coiny = coiny;
        this.h = h;
        this.w = w;
        this.taken = taken;
    }

    public int getCoinx() {
        return coinx;
    }

    public int getCoiny() {
        return coiny;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setCoinx(int coinx) {
        this.coinx = coinx;
    }

    public void setCoiny(int coiny) {
        this.coiny = coiny;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
