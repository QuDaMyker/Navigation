package com.builtlab.fragment.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.builtlab.model.User;
import com.builtlab.navigation.databinding.UserItemBinding;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    private List<User> userList = new ArrayList<>();
    private OnItemClickListener listener;

    public UserAdapter(OnItemClickListener listener) {
        this.listener = listener;
        setHasStableIds(true);
    }

    public void setUserList(List<User> users) {
        if (users != null) {
            this.userList.clear();
            this.userList.addAll(users);
            notifyDataSetChanged();
        }
    }

    @Override
    public long getItemId(int position) {
        String email = userList.get(position).getEmail();
        return email != null ? email.hashCode() : RecyclerView.NO_ID;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        UserItemBinding binding = UserItemBinding.inflate(inflater, parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.binding.emailTextView.setText(user.getEmail());
        holder.binding.nameTextView.setText(user.getId());

        holder.binding.getRoot().setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        UserItemBinding binding;

        public UserViewHolder(UserItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}