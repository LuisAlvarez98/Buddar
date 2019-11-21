package com.app.buddar.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.buddar.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Class by Luis Felipe Alvarez Sanchez
 * Chat Vertical list
 */
public class ChatVerticalList extends RecyclerView.Adapter<ChatVerticalList.ViewHolderDatos> implements View.OnClickListener {
    ArrayList<String> listDatos;

    public ChatVerticalList(ArrayList<String> listDatos) {
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
    public ChatVerticalList.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_vertical_item, null, false);
        return new ViewHolderDatos(view);
    }

    /**
     * Where you set the information of the objects
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final ChatVerticalList.ViewHolderDatos holder, final int position) {
        //get current hour
        Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime();
        String res;
        res = currentDate.getHours() + ":" + currentDate.getMinutes() + " ";
        holder.message.setText(listDatos.get(position) + " - " +res);

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

        public ViewHolderDatos(View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.message);
        }

    }
}