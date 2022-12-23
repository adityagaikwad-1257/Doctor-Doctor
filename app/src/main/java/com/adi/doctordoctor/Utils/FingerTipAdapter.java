package com.adi.doctordoctor.Utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adi.doctordoctor.R;
import com.adi.doctordoctor.databinding.FingertipItemBinding;

public class FingerTipAdapter extends RecyclerView.Adapter<FingerTipAdapter.FingerTipViewHolder> {
    private final Context context;

    private final int[] icons = {
            R.drawable.check_up,
            R.drawable.vaccine,
            R.drawable.clinic,
            R.drawable.ambulance,
            R.drawable.nurse,
            R.drawable.medicine
    };

    private final String[] names = {
            "Check up",
            "Vaccine",
            "Clinic",
            "Ambulance",
            "Nurse",
            "medicine"
    };

    public FingerTipAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public FingerTipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FingertipItemBinding binding = FingertipItemBinding.inflate(LayoutInflater.from(context));
        return new FingerTipViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FingerTipViewHolder holder, int position) {
        int icon = icons[3];
        String name = names[3];

        if (position < 6){
            icon = icons[position];
            name = names[position];
        }

        holder.binding.itemImg.setImageResource(icon);
        holder.binding.itemName.setText(name);
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public static class FingerTipViewHolder extends RecyclerView.ViewHolder{
        FingertipItemBinding binding;
        public FingerTipViewHolder(@NonNull FingertipItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
