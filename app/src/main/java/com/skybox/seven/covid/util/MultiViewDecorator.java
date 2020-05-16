package com.skybox.seven.covid.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.epoxy.EpoxyControllerAdapter;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.model.HeaderModel_;

public class MultiViewDecorator extends RecyclerView.ItemDecoration {
    private Drawable mDivider;
    private Paint paint;
    private Context context;

    public MultiViewDecorator(Context context) {
        this.context = context;
        paint = new Paint();
        paint.setColor(R.color.colorAccent);
        paint.setStrokeWidth(2);

        this.context = context;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        c.save();
        if (parent.getAdapter() instanceof EpoxyControllerAdapter) {
            EpoxyControllerAdapter epoxyAdapter = (EpoxyControllerAdapter) parent.getAdapter();
            final int left;
            final int right;
            if (parent.getClipToPadding()) {
                left = parent.getPaddingLeft();
                right = parent.getWidth() - parent.getPaddingRight();
                c.clipRect(left, parent.getPaddingTop(), right,
                        parent.getHeight() - parent.getPaddingBottom());
            } else {
                left = 0;
                right = parent.getWidth();
            }
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (epoxyAdapter.getModelAtPosition(i).getClass() != HeaderModel_.class) {
                    final View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                            .getLayoutParams();
                    final int top = child.getBottom() + params.bottomMargin;
                    final int bottom = 32 + Math.round(child.getTranslationY());
//                    final int top = bottom - mDivider.getIntrinsicHeight();
                    Log.e("TAG", "(top: " + top + ")");
                    c.drawLine(left, top, right, bottom, paint);
                }

            }
            c.restore();
        }

    }
}
