package com.example.kuanghaochuan.hanoigame.pillar;

import com.example.kuanghaochuan.hanoigame.hanoi.HanoiBean;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by kuanghaochuan on 2017/1/9.
 */

public class PillarBean {
    private Stack<HanoiBean> mHanoiBeanStack = new Stack<>();

    public boolean canMoveToTargetPillar(HanoiBean hanoiBean) {
        int listSize = mHanoiBeanStack.size();
        if (listSize == 0) {
            return true;
        } else {
            int firstHanoiBeanSize = mHanoiBeanStack.peek().getSize();
            return hanoiBean.getSize() < firstHanoiBeanSize;
        }
    }

    public void popRemovedHanoiBean() {
        if (mHanoiBeanStack.isEmpty())
            return;
        mHanoiBeanStack.pop();
    }

    public void moveToTargetPillar(HanoiBean hanoiBean) {
        mHanoiBeanStack.push(hanoiBean);
    }

    public HanoiBean getFirstHanoiBean() {
        if (mHanoiBeanStack.size() > 0) {
            return mHanoiBeanStack.peek();
        } else {
            return null;
        }
    }

    public PillarBean add(HanoiBean hanoiBean) {
        mHanoiBeanStack.push(hanoiBean);
        return this;
    }

    public int[] getAllHanoiBeanSize() {
        int[] result = new int[mHanoiBeanStack.size()];
        int index = 0;
        Iterator<HanoiBean> iterator = mHanoiBeanStack.iterator();
        while (iterator.hasNext()) {
            HanoiBean resultHanoiBean = iterator.next();
            result[index++] = resultHanoiBean.getSize();
        }
        return result;
    }

    public int getSize() {
        return mHanoiBeanStack.size();
    }
}
