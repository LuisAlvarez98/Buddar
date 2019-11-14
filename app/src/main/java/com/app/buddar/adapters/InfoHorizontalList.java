package com.app.buddar.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.buddar.DashboardActivity;
import com.app.buddar.DetailUserActivity;
import com.app.buddar.FirstActivity;
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
        Picasso.get().load(listDatos.get(position).getPicture()).resize(950, 800)
                .centerCrop().into(holder.contentImage);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infoIntent = new Intent(view.getContext(), DetailUserActivity.class);
                infoIntent.putExtra("name",listDatos.get(position).getName());
                infoIntent.putExtra("email",listDatos.get(position).getEmail());
                infoIntent.putExtra("picture",listDatos.get(position).getPicture());
                infoIntent.putExtra("bio",listDatos.get(position).getBio());

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
        ImageView contentImage;
        TextView title;
        TextView info;
        LinearLayout item;
        public ViewHolderDatos(View itemView) {
            super(itemView);
            contentImage = (ImageView) itemView.findViewById(R.id.contentImage);
            item = (LinearLayout)itemView.findViewById(R.id.item);
        }

    }
}