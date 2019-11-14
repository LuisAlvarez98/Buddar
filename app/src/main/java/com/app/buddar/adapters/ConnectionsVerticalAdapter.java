package com.app.buddar.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.buddar.R;
import com.app.buddar.objects.Connection;
import com.app.buddar.objects.History;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Class by Luis Felipe Alvarez Sanchez
 */
public class ConnectionsVerticalAdapter extends RecyclerView.Adapter<ConnectionsVerticalAdapter.ViewHolderDatos> implements View.OnClickListener {
    ArrayList<Connection> listDatos;

    public ConnectionsVerticalAdapter(ArrayList<Connection> listDatos) {
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
    public ConnectionsVerticalAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item, null, false);
        return new ViewHolderDatos(view);
    }

    /**
     * Where you set the information of the objects
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final ConnectionsVerticalAdapter.ViewHolderDatos holder, final int position) {
        holder.name.setText(listDatos.get(position).getName());
        holder.bio.setText(listDatos.get(position).getBio());
        Picasso.get().load(listDatos.get(position).getPicture()).into(holder.profileImage);
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
        TextView name,bio;
        CircleImageView profileImage;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            bio = (TextView)itemView.findViewById(R.id.bio);
            profileImage = (CircleImageView) itemView.findViewById(R.id.profileImage);
        }

    }
}