package com.adi.doctordoctor.Utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.adi.doctordoctor.databinding.DoctorViewHolderBinding;
import com.adi.doctordoctor.models.Doctor;

public class DoctorListAdapter extends ListAdapter<Doctor, DoctorListAdapter.DoctorViewHolder> {

    private static DiffUtil.ItemCallback<Doctor> diffCallback = new DiffUtil.ItemCallback<Doctor>() {
        @Override
        public boolean areItemsTheSame(@NonNull Doctor oldItem, @NonNull Doctor newItem) {
            return oldItem.getDoctors_id().equals(newItem.getDoctors_id());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Doctor oldItem, @NonNull Doctor newItem) {
            return oldItem.equals(newItem);
        }
    };

    public DoctorListAdapter() {
        super(diffCallback);
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DoctorViewHolderBinding binding = DoctorViewHolderBinding.inflate(
                LayoutInflater.from(parent.getContext()));
        return new DoctorViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        holder.binding.setDoctor(getItem(position));
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder{
        DoctorViewHolderBinding binding;
        public DoctorViewHolder(@NonNull DoctorViewHolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
