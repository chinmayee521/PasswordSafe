package com.example.passwordsafe;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PassRVAdapter extends RecyclerView.Adapter<PassRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<PwdSafe> list;
    private Context context;
    Button showHideBtn;

    // constructor
    public PassRVAdapter(ArrayList<PwdSafe> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public PassRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pass_rv_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PassRVAdapter.ViewHolder holder, int position) {

        // on below line we are setting data
        // to our views of recycler view item.
        PwdSafe ps = list.get(position);
        holder.descriptionTV.setText(ps.getDescription());
        holder.usernameTV.setText(ps.getUsername());
        holder.passwordTV.setText(ps.getPassword());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView descriptionTV, usernameTV, passwordTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            descriptionTV = itemView.findViewById(R.id.idTVDecsription);
            usernameTV = itemView.findViewById(R.id.idTVUsername);
            passwordTV = itemView.findViewById(R.id.idTVPassword);

        }
    }
}

