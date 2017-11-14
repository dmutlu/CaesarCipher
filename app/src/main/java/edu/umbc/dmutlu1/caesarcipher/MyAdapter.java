package edu.umbc.dmutlu1.caesarcipher;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private ArrayList<Message> dataSet = new ArrayList<>();
    private final ShareHandler handler;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView userMessage;
        TextView cipherMessage;

        public ViewHolder(View v)
        {
            super(v);
            this.userMessage = itemView.findViewById(R.id.textUsrMsg);
            this.cipherMessage = itemView.findViewById(R.id.textCipherMsg);
        }
    }

    public void addMessage(Message message)
    {
        dataSet.add(message);
        notifyDataSetChanged();
    }

    public void removeM

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ShareHandler handler)
    {
        this.handler = handler;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cipher_card, parent, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.userMessage.setText(dataSet.get(position).getUserMessage());
        holder.cipherMessage.setText(dataSet.get(position).getCipherMessage());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}