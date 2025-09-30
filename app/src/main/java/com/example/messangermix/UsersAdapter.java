package com.example.messangermix;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<User> users = new ArrayList<>();
    private OnUserClikListner onUserClikListner;

    public void setOnUserClikListner(OnUserClikListner onUserClikListner) {
        this.onUserClikListner = onUserClikListner;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        String userInfo = String.format("%s, %s, %s", user.getName(), user.getLastName(), user.getAge());
        holder.textViewUserInfo.setText(userInfo);

        int bgResId;
        if (user.getOnline()) {
            bgResId = R.drawable.circle_green;
        } else {
            bgResId = R.drawable.circle_red;
        }
        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(), bgResId);
        holder.onlineStatus.setBackground(background);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onUserClikListner != null) {
                    onUserClikListner.onUserClick(user);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    interface OnUserClikListner {
        void onUserClick(User user);
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewUserInfo;
        private View onlineStatus;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserInfo = itemView.findViewById(R.id.textViewUserInfo);
            onlineStatus = itemView.findViewById(R.id.onlineStatus);
        }
    }
}
