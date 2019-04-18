package tower_of_solomon.entity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ButtonItem> {
    public ListAdapter(Context context) {
        super(context, android.R.layout.simple_spinner_item);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
    public ListAdapter(Context context, ArrayList<ButtonItem> list) {
        super(context, android.R.layout.simple_spinner_item, list);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView)super.getView(position, convertView, parent);
        textView.setText(getItem(position).text);
        return textView;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView)super.getDropDownView(position, convertView, parent);
        textView.setText(getItem(position).text);
        return textView;
    }
}
