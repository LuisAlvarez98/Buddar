package com.app.buddar.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.buddar.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Class by Luis Felipe Alvarez Sanchez
 * Chat Vertical list
 */
public class NotificationVerticalList extends RecyclerView.Adapter<NotificationVerticalList.ViewHolderDatos> implements View.OnClickListener {
    ArrayList<String> listDatos;

    public NotificationVerticalList(ArrayList<String> listDatos) {
        this.listDatos = listDatos;
    }

    /**
     * Creates the view
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public NotificationVerticalList.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item, null, false);
        return new ViewHolderDatos(view);
    }

    /**
     * Where you set the information of the objects
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final NotificationVerticalList.ViewHolderDatos holder, final int position) {
        //get current hour
        holder.message.setText(listDatos.get(position));
        //Accept button listener
        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Accepted connection", Toast.LENGTH_SHORT).show();
                listDatos.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, listDatos.size());
            }
        });
        //Reject button listener
        holder.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Rejected connection", Toast.LENGTH_SHORT).show();
                listDatos.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, listDatos.size());
            }
        });
    }

    /**
     * getItemCount method
     *
     * @return size
     */
    @Override
    public int getItemCount() {
        return (listDatos == null) ? 0 : listDatos.size();
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * ViewHolderDatos
     * Init the view objects
     */
    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView message;
        Button acceptButton, rejectButton;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.notification);
            acceptButton = (Button) itemView.findViewById(R.id.acceptButton);
            rejectButton = (Button) itemView.findViewById(R.id.rejectButton);
        }

    }
}