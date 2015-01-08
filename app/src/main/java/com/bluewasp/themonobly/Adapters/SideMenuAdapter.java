package com.bluewasp.themonobly.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluewasp.themonobly.Models.SideMenuItemData;
import com.bluewasp.themonobly.R;
import com.bluewasp.themonobly.Viewholders.SimpleItemViewholder;

import java.util.ArrayList;

/**
 * Created by Lenovo on 1/6/2015.
 */
public class SideMenuAdapter extends BaseAdapter{


    Context mContext;
    ArrayList<SideMenuItemData> itemsList;

    public SideMenuAdapter(Context mContext,
                           ArrayList<SideMenuItemData> itemsList) {
        super();
        this.mContext = mContext;
        this.itemsList = itemsList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView == null){

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.side_menu_item, null);

            SimpleItemViewholder viewholder = new SimpleItemViewholder();

            viewholder.titleTV = (TextView) convertView.findViewById(R.id.side_menu_item_titleTV);
            viewholder.iconIV = (ImageView) convertView.findViewById(R.id.side_menu_item_iconIV);

            convertView.setTag(viewholder);
        }
        SimpleItemViewholder viewholder = (SimpleItemViewholder) convertView.getTag();

        viewholder.titleTV.setText(itemsList.get(position).getTitle());
        viewholder.iconIV.setImageResource(itemsList.get(position).getIcon());

        return convertView;
    }

}
