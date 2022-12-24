package com.adi.doctordoctor.Utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adi.doctordoctor.R;
import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerCardHolder> {

    @NonNull
    @Override
    public ViewPagerCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_card, parent, false);
        return new ViewPagerCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerCardHolder holder, int position) {
        Picasso.get()
                .load(Util.getImageAt(position))
                .placeholder(android.R.color.darker_gray)
                .fit()
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return Util.getStaticDataSize();
    }

    public static class ViewPagerCardHolder extends RecyclerView.ViewHolder{
        ImageView image;
        public ViewPagerCardHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.view_pager_card_img);
        }
    }

}
