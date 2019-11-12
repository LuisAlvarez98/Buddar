package com.app.buddar.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.buddar.R;
import com.app.buddar.objects.ChatItem;
import com.app.buddar.objects.InfoItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Class by Luis Felipe Alvarez Sanchez
 */
public class InfoHorizontalList extends RecyclerView.Adapter<InfoHorizontalList.ViewHolderDatos> implements View.OnClickListener {
    ArrayList<InfoItem> listDatos;

    public InfoHorizontalList(ArrayList<InfoItem> listDatos) {
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
    public InfoHorizontalList.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.info_item, null, false);
        return new ViewHolderDatos(view);
    }

    /**
     * Where you set the information of the objects
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final InfoHorizontalList.ViewHolderDatos holder, final int position) {
        Picasso.get().load("https://scontent.fntr6-1.fna.fbcdn.net/v/t1.0-9/57738860_2189787707771861_2357075513719128064_n.jpg?_nc_cat=108&_nc_oc=AQkR0FD2ezHKLqilzxbqqTf3eXHYpCDCupqHbq-2uEivl5Fne-blS9-ngdYJKIhsFt8&_nc_ht=scontent.fntr6-1.fna&oh=ee0518b253fc53a1bd863c6079fee62f&oe=5E4F1BD5").into(holder.contentImage);
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
        ImageView contentImage;
        TextView title;
        TextView info;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            contentImage = (ImageView) itemView.findViewById(R.id.contentImage);
        }

    }
}