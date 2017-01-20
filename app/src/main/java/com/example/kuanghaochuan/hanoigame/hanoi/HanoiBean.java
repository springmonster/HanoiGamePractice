package com.example.kuanghaochuan.hanoigame.hanoi;

/**
 * Created by kuanghaochuan on 2017/1/9.
 */

public class HanoiBean {
    private int size;
    private int imgIndex;

    public HanoiBean(int imgIndex, int size) {
        this.imgIndex = imgIndex;
        this.size = size;
    }

    public int getImgIndex() {
        return imgIndex;
    }

    public void setImgIndex(int imgIndex) {
        this.imgIndex = imgIndex;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "HanoiBean{" +
                "imgIndex=" + imgIndex +
                ", size=" + size +
                '}';
    }
}
