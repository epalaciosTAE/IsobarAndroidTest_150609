package com.roundarch.codetest.part3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roundarch.codetest.R;

import model.Person;

/**
 * Created by Eduardo on 10/01/2016.
 */
public class Part3Adapter extends RecyclerView.Adapter<Part3Adapter.ViewHolder> {

    private Person person;
    private Context context;

    public Part3Adapter(Person person, Context context) {
        this.person = person;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.part3_listview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(person.getListing().get(position).getName());
        holder.textView.setText(person.getListing().get(position).getCategoryId());
    }

    @Override
    public int getItemCount() {
        return person.getListing().size();
    }

    public void updateList(Person person) {
        this.person = person;
        notifyDataSetChanged();
    }

    private int apiListCount() {
        return person.getListing().size();
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView, textView2;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);
        }
    }
}
