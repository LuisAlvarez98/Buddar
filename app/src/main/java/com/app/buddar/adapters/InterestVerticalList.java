package com.app.buddar.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.buddar.R;
import com.app.buddar.objects.ChatItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Class by Luis Felipe Alvarez Sanchez
 * Interest Vertical list
 */
public class InterestVerticalList extends RecyclerView.Adapter<InterestVerticalList.ViewHolderDatos> implements View.OnClickListener {
    ArrayList<String> listDatos;

    public InterestVerticalList(ArrayList<String> listDatos) {
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
    public InterestVerticalList.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.interest_item, null, false);
        return new ViewHolderDatos(view);
    }

    /**
     * Where you set the information of the objects
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final InterestVerticalList.ViewHolderDatos holder, final int position) {
        holder.interest.setText(listDatos.get(position));
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
            TextView interest;
        public ViewHolderDatos(View itemView) {
            super(itemView);
            interest = (TextView)itemView.findViewById(R.id.interest);
        }

    }
}