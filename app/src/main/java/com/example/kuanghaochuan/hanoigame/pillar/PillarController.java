package com.example.kuanghaochuan.hanoigame.pillar;

import com.example.kuanghaochuan.hanoigame.hanoi.HanoiBean;

/**
 * Created by kuanghaochuan on 2017/1/9.
 */

public class PillarController {
    private PillarBean firstPillar = new PillarBean();
    private PillarBean secondPillar = new PillarBean();
    private PillarBean thirdPillar = new PillarBean();
    private PillarBean[] pillars = new PillarBean[]{firstPillar, secondPillar, thirdPillar};

    public PillarController() {
        firstPillar
                .add(new HanoiBean(5, 6))
                .add(new HanoiBean(4, 5))
                .add(new HanoiBean(3, 4))
                .add(new HanoiBean(2, 3))
                .add(new HanoiBean(1, 2))
                .add(new HanoiBean(0, 1));
    }

    private PillarBean getPillar(int pillarIndex) {
        if (pillarIndex < 0 || pillarIndex > 3) {
            throw new IndexOutOfBoundsException("Total 3 pillars!");
        } else {
            return pillars[pillarIndex];
        }
    }

    public HanoiBean getMoveHanoiBean(int moveIndex) {
        PillarBean pillarBean = getPillar(moveIndex);
        return pillarBean.getFirstHanoiBean();
    }

    public boolean canMoveToTargetPillar(HanoiBean hanoiBean, int targetPillarIndex) {
        PillarBean pillarBean = getPillar(targetPillarIndex);
        return pillarBean.canMoveToTargetPillar(hanoiBean);
    }

    public void moveToTargetPillar(HanoiBean hanoiBean, int targetPillarIndex) {
        PillarBean pillarBean = getPillar(targetPillarIndex);
        pillarBean.moveToTargetPillar(hanoiBean);
    }

    public int[] getAllHanoiSize(int pillarIndex) {
        PillarBean pillarBean = getPillar(pillarIndex);
        return pillarBean.getAllHanoiBeanSize();
    }

    public int getHanoiSize(int pillarIndex) {
        PillarBean pillarBean = getPillar(pillarIndex);
        return pillarBean.getSize();
    }

    public void popMovedHanoiBean(int movedIndex) {
        PillarBean pillarBean = getPillar(movedIndex);
        pillarBean.popRemovedHanoiBean();
    }
}
