package com.bluewasp.themonobly.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluewasp.themonobly.Models.CommunityCardsData;
import com.bluewasp.themonobly.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2/12/2015.
 */
public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {

    private ArrayList<CommunityCardsData> list;

    public CommunityAdapter(ArrayList<CommunityCardsData> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_card_layout, parent, false);

        /*ImageView backgroundImage, circleImage;
        TextView nameTV, positionTV, commityTV, levelTV;

        backgroundImage = (ImageView) v.findViewById(R.id.community_card_member_back_image_view);
        circleImage = (ImageView) v.findViewById(R.id.community_card_member_profile_image_view);

        nameTV = (TextView) v.findViewById(R.id.community_card_member_name_tv);
        positionTV = (TextView) v.findViewById(R.id.community_card_member_position_tv);
        commityTV = (TextView) v.findViewById(R.id.community_card_member_commity_tv);
        levelTV = (TextView) v.findViewById(R.id.community_card_member_level_tv);
        */

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameTV.setText(list.get(position).getName());
        holder.positionTV.setText(list.get(position).getPosition());
        holder.commityTV.setText(list.get(position).getCommity());
        holder.levelTV.setText(list.get(position).getLevel());

        holder.circleImage.setImageBitmap(list.get(position).getProfileImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView backgroundImage, circleImage;
        public TextView nameTV, positionTV, commityTV, levelTV;

        public ViewHolder(View itemView) {
            super(itemView);
            this.backgroundImage = (ImageView) itemView.findViewById(R.id.community_card_member_back_image_view);
            this.circleImage = (ImageView) itemView.findViewById(R.id.community_card_member_profile_image_view);

            this.nameTV = (TextView) itemView.findViewById(R.id.community_card_member_name_tv);
            this.positionTV = (TextView) itemView.findViewById(R.id.community_card_member_position_tv);
            this.commityTV = (TextView) itemView.findViewById(R.id.community_card_member_commity_tv);
            this.levelTV = (TextView) itemView.findViewById(R.id.community_card_member_level_tv);
        }
    }
}
