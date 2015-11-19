package seemann.scrollcontroller.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by yangchao on 15-11-19.
 */
public class MyScrollview extends HorizontalScrollView {
    private boolean enabled;
    public MyScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        this.enabled = false;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        super.onLayout(changed, l, t, r, b);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    @Override
    public void computeScroll() {
        // TODO Auto-generated method stub
        super.computeScroll();
    }
}