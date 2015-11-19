package seemann.scrollcontroller.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import seemann.scrollcontroller.R;

/**
 * Created by yangchao on 15-11-19.
 */
public class MoveAdapter extends BaseAdapter {

    private List<Map<String,Object>> list;
    private Context context;
    public MoveAdapter(Context context){
        this.context=context;
        this.list=new ArrayList<Map<String,Object>>();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Map<String,Object> getItem(int location) {
        return list.get(location);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    public void clear(){
        list.clear();
    }
    public void addObject(Map<String,Object> map){
        list.add(map);
        notifyDataSetChanged();
    }
    @Override
    public View getView(int location, View arg1, ViewGroup arg2) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_move,null);
        TextView text=(TextView)view.findViewById(R.id.move_text);
        text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        Map<String,Object> map=getItem(location);
        text.setText(map.get("text").toString());
        return view;
    }

}