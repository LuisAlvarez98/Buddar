package com.app.buddar.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.buddar.ChatActivity;
import com.app.buddar.DetailUserActivity;
import com.app.buddar.R;
import com.app.buddar.objects.ChatItem;
import com.app.buddar.objects.Connection;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Class by Luis Felipe Alvarez Sanchez
 */
public class ChatsHorizontalAdapter extends RecyclerView.Adapter<ChatsHorizontalAdapter.ViewHolderDatos> implements View.OnClickListener {
    ArrayList<ChatItem> listDatos;

    public ChatsHorizontalAdapter(ArrayList<ChatItem> listDatos) {
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
    public ChatsHorizontalAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dash_chat_item, null, false);
        return new ViewHolderDatos(view);
    }

    /**
     * Where you set the information of the objects
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final ChatsHorizontalAdapter.ViewHolderDatos holder, final int position) {
        Picasso.get().load(listDatos.get(position).getUrl()).into(holder.profileImage);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infoIntent = new Intent(view.getContext(), ChatActivity.class);
                infoIntent.putExtra("name",listDatos.get(position).getName());
                infoIntent.putExtra("picture",listDatos.get(position).getUrl());
                view.getContext().startActivity(infoIntent);
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
        CircleImageView profileImage;
        LinearLayout item;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            profileImage = (CircleImageView) itemView.findViewById(R.id.profileImage);
            item = (LinearLayout)itemView.findViewById(R.id.item);
        }

    }
}