package seemann.scrollcontrollerview;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import seemann.scrollcontroller.adapter.MoveAdapter;
import seemann.scrollcontroller.view.MoveLayout;
import seemann.scrollcontroller.view.MyScrollview;

public class MainActivity extends Activity {
    private MoveLayout movieLayout;
    private MoveAdapter adapter;
    MyScrollview scrollView;
    private int dur;
    private int offset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dur =  getWidth()/5;
        scrollView = (MyScrollview) findViewById(R.id.scrollView);
        movieLayout = (MoveLayout) findViewById(R.id.moveLayout);
        adapter = new MoveAdapter(this,15,"#6223e3");

        /************set week of 2015 in adapter**************/
        Calendar c_begin = new GregorianCalendar();
        Calendar c_end = new GregorianCalendar();
        c_begin.set(2015, 0, 5);
        c_end.set(2015, 11, 31);
        c_end.add(Calendar.DAY_OF_YEAR, 1);  //结束日期下滚一天是为了包含最后一天
        List weekbegin = new ArrayList();
        List weekend = new ArrayList();
        ArrayList<List> weekList = Utils.getWeekBeginlist(c_begin, c_end);
        weekbegin = weekList.get(0);
        weekend = weekList.get(1);
        for (int i = 0; i < weekbegin.size() - 1; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("text", weekbegin.get(i) + "~" + weekend.get(i));
            adapter.addObject(map);
        }
        movieLayout.setAdapter(adapter);
        /***********************controller of scrollView************************/
        scrollView.setOnTouchListener(new MyScrollview.OnTouchListener() {
            private int lastY = 0;
            private int touchEventId = -1;
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    MyScrollview scroller = (MyScrollview) msg.obj;
                    if (msg.what == touchEventId) {
                        if (lastY == scroller.getScrollY()) {
                            offset = scrollView.getScrollX() / dur;
                            int diff;
                            int to = 0;
                            offset = scrollView.getScrollX() / dur;
                            diff = scrollView.getScrollX() % dur;
                            if (diff < dur / 2) {
                                scrollView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        scrollView.smoothScrollTo(offset * dur, 0);
                                    }
                                });
                                to = offset;
                            } else {
                                scrollView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        scrollView.smoothScrollTo((offset + 1) * dur, 0);
                                    }
                                });
                                to = offset + 1;
                            }
                        }
                    } else {
                        handler.sendMessageDelayed(handler.obtainMessage(touchEventId, scroller), 1);
                        lastY = scroller.getScrollY();
                    }
                }
            };

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int eventAction = event.getAction();
                int y = (int) event.getRawY();
                switch (eventAction) {
                    case MotionEvent.ACTION_UP:
                        handler.sendMessageDelayed(handler.obtainMessage(touchEventId, v), 500);
                        break;
                    default:
                        break;
                }
                return false;
            }

        });
    }

    private float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }
    private  int getWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        return width;
    }
}