package com.example.kuanghaochuan.hanoigame;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.kuanghaochuan.hanoigame.hanoi.HanoiBean;
import com.example.kuanghaochuan.hanoigame.pillar.PillarController;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private static final int[] images = new int[]{R.drawable.h_1, R.drawable.h_2,
            R.drawable.h_3, R.drawable.h_4, R.drawable.h_5, R.drawable.h_6};
    private FrameLayout mFirstFrameLayout;
    private FrameLayout mSecondFrameLayout;
    private FrameLayout mThirdFrameLayout;
    private PillarController mPillarController = new PillarController();
    private int mClickedIndex = -1;
    private HanoiBean mMoveHanoiBean;
    private FrameLayout[] mFrameLayouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstFrameLayout = (FrameLayout) findViewById(R.id.first_frame);
        mSecondFrameLayout = (FrameLayout) findViewById(R.id.second_frame);
        mThirdFrameLayout = (FrameLayout) findViewById(R.id.third_frame);

        mFrameLayouts = new FrameLayout[]{mFirstFrameLayout, mSecondFrameLayout, mThirdFrameLayout};

        mFirstFrameLayout.setOnClickListener(new FrameLayoutOnClickImpl(0));
        mSecondFrameLayout.setOnClickListener(new FrameLayoutOnClickImpl(1));
        mThirdFrameLayout.setOnClickListener(new FrameLayoutOnClickImpl(2));

        initFirstPillarAllHanoiViews();
    }

    private void initFirstPillarAllHanoiViews() {
        LinearLayout linearLayout = (LinearLayout) mFirstFrameLayout.findViewById(R.id.hanoi_list_ll);
        for (int i = 0, n = linearLayout.getChildCount(); i < n; i++) {
            linearLayout.getChildAt(i).setVisibility(View.VISIBLE);
        }
    }

    private void drawHanoiViewByIndexForVisible(FrameLayout indexFL, int targetPillar, int imageIndex) {
        ImageView[] imageViews = getImageViewList(indexFL);

        int hanoiSize = mPillarController.getHanoiSize(targetPillar);
        int shouldChangeBitmapIndex = images.length - hanoiSize;
        imageViews[shouldChangeBitmapIndex].setImageResource(images[imageIndex]);
        imageViews[shouldChangeBitmapIndex].setVisibility(View.VISIBLE);
    }

    @NonNull
    private ImageView[] getImageViewList(FrameLayout indexFL) {
        ImageView img1 = (ImageView) indexFL.findViewById(R.id.hanoi_1_img);
        ImageView img2 = (ImageView) indexFL.findViewById(R.id.hanoi_2_img);
        ImageView img3 = (ImageView) indexFL.findViewById(R.id.hanoi_3_img);
        ImageView img4 = (ImageView) indexFL.findViewById(R.id.hanoi_4_img);
        ImageView img5 = (ImageView) indexFL.findViewById(R.id.hanoi_5_img);
        ImageView img6 = (ImageView) indexFL.findViewById(R.id.hanoi_6_img);
        return new ImageView[]{img1, img2, img3, img4, img5, img6};
    }

    private void drawHanoiViewByIndexForInVisible(FrameLayout indexFL, int oriPillar) {
        ImageView[] imageViews = getImageViewList(indexFL);

        int hanoiSize = mPillarController.getHanoiSize(oriPillar);
        int shouldChangeBitmapIndex = images.length - hanoiSize - 1;

        imageViews[shouldChangeBitmapIndex].setVisibility(View.INVISIBLE);
    }

    private class FrameLayoutOnClickImpl implements View.OnClickListener {
        private int mPillarIndex = -1;

        public FrameLayoutOnClickImpl(int pillarIndex) {
            this.mPillarIndex = pillarIndex;
        }

        @Override
        public void onClick(View v) {
            if (mClickedIndex != -1) {
                if (mMoveHanoiBean == null) {
                    mClickedIndex = -1;
                } else {
                    startMoveHanoi();
                }
            } else {
                clickHanoiForMove();
            }
        }

        private void startMoveHanoi() {
            Log.d(TAG, "startMoveHanoi mClickedIndex " + mClickedIndex);
            Log.d(TAG, "startMoveHanoi mPillarIndex " + mPillarIndex);
            if (mClickedIndex == mPillarIndex) {
                return;
            }

            boolean canMove = mPillarController.canMoveToTargetPillar(mMoveHanoiBean, mPillarIndex);
            if (canMove) {
                Log.d(TAG, "startMoveHanoi: mMoveHanoibean " + mMoveHanoiBean);
                mPillarController.popMovedHanoiBean(mClickedIndex);
                mPillarController.moveToTargetPillar(mMoveHanoiBean, mPillarIndex);
                drawHanoiViewByIndexForInVisible(mFrameLayouts[mClickedIndex], mClickedIndex);
                drawHanoiViewByIndexForVisible(mFrameLayouts[mPillarIndex], mPillarIndex, mMoveHanoiBean.getImgIndex());
                mClickedIndex = -1;
            } else {
                mClickedIndex = -1;
            }
        }

        private void clickHanoiForMove() {
            mClickedIndex = mPillarIndex;
            mMoveHanoiBean = mPillarController.getMoveHanoiBean(mClickedIndex);
        }
    }
}
