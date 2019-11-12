package com.app.buddar.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.buddar.R;
import com.app.buddar.objects.FAQ;
import com.app.buddar.objects.History;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Class by Luis Felipe Alvarez Sanchez
 */
public class FAQVerticalAdapter extends RecyclerView.Adapter<FAQVerticalAdapter.ViewHolderDatos> implements View.OnClickListener {
    ArrayList<FAQ> listDatos;

    public FAQVerticalAdapter(ArrayList<FAQ> listDatos) {
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
    public FAQVerticalAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.faq_item, null, false);
        return new ViewHolderDatos(view);
    }

    /**
     * Where you set the information of the objects
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final FAQVerticalAdapter.ViewHolderDatos holder, final int position) {
        holder.question.setText(listDatos.get(position).getQuestion());
        holder.answer.setText(listDatos.get(position).getAnswer());
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
        TextView question,answer;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            question = (TextView) itemView.findViewById(R.id.question);
            answer = (TextView)itemView.findViewById(R.id.answer);
        }

    }
}