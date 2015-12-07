package seemann.scrollcontroller.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Map;

import seemann.scrollcontroller.adapter.MoveAdapter;

/**
 * Created by yangchao on 15-11-19.
 */
public class MoveLayout extends LinearLayout {

    private MoveAdapter adapter;
    private Context context;
    public MoveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }
    private int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }
    public void setAdapter(MoveAdapter adapter) {
        this.adapter = adapter;
        for(int i=0;i<adapter.getCount();i++){
            final Map<String,Object> map=adapter.getItem(i);
            View view=adapter.getView(i, null, null);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "click:" + map.get("text"), Toast.LENGTH_SHORT).show();
                }
            });
            this.setOrientation(HORIZONTAL);
            this.addView(view,new LinearLayout.LayoutParams((int)dp2px(context.getResources(),72),LayoutParams.WRAP_CONTENT));
        }
    }
    public void clear(){
        this.removeAllViews();
    }
}
