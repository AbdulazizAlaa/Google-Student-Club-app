package com.bluewasp.themonobly.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluewasp.themonobly.Models.ProfileListItemData;

import com.bluewasp.themonobly.R;
import com.bluewasp.themonobly.Viewholders.SimpleItemViewholder;

import java.util.ArrayList;

/**
 * Created by Lenovo on 1/8/2015.
 */
public class ProfileListAdapter extends BaseAdapter {


    Context mContext;
    ArrayList<ProfileListItemData> list;

    public ProfileListAdapter(Context mContext, ArrayList<ProfileListItemData> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.profile_list_item, null);

            SimpleItemViewholder viewholder = new SimpleItemViewholder();

            viewholder.titleTV = (TextView) convertView.findViewById(R.id.profile_list_item_description_tv);
            viewholder.iconIV = (ImageView) convertView.findViewById(R.id.profile_list_item_img);

            convertView.setTag(viewholder);
        }
        SimpleItemViewholder viewholder = (SimpleItemViewholder) convertView.getTag();

        viewholder.titleTV.setText(list.get(position).getDescription());
        viewholder.iconIV.setImageBitmap(list.get(position).getImage());

        return convertView;
    }


}
