package edu.umbc.dmutlu1.caesarcipher;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    private ArrayList<Message> dataSet = new ArrayList<>();
    private final AppCompatActivity activity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        final TextView userMessage;
        final TextView cipherMessage;
        final ImageButton btnDelete;
        final ImageButton btnShare;
        final ImageButton btnCopy;

        public ViewHolder(View v)
        {
            super(v);
            this.userMessage = itemView.findViewById(R.id.textUsrMsg);
            this.cipherMessage = itemView.findViewById(R.id.textCipherMsg);
            this.btnDelete = itemView.findViewById(R.id.imgBtnClose);
            this.btnShare = itemView.findViewById(R.id.imgBtnShare);
            this.btnCopy = itemView.findViewById(R.id.imgBtnCopy);
        }
    }

    /*Message Card Action Methods*/
    public void addMessage(Message message)
    {
        //Index integer is used to place new cards on the top.
        dataSet.add(0, message);
        notifyDataSetChanged();
    }

    private void removeMessage(int pos)
    {
        dataSet.remove(pos);
        notifyDataSetChanged();
    }

    private void shareMessage(int pos)
    {
        Intent share = new Intent();

        String shareMsg = activity.getString(R.string.share_extra_key) +
                "\u0020" + dataSet.get(pos).getKey() + "\n" + "\n" + dataSet.get(pos).getCipherMessage();

        share.setAction(Intent.ACTION_SEND);
        share.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.share_subject));
        share.putExtra(Intent.EXTRA_TEXT, shareMsg);
        share.setType("text/plain");

        activity.startActivity(Intent.createChooser(share, "Send Cipher Message"));
    }

    private void copyMessage(int pos)
    {
        // Gets a handle to the clipboard service.
        ClipboardManager clipboard = (ClipboardManager)
                activity.getSystemService(Context.CLIPBOARD_SERVICE);

        // Creates a new text clip to put on the clipboard
        ClipData clip = ClipData.newPlainText(activity.getString(R.string.share_subject),
                dataSet.get(pos).getCipherMessage());

        //Notifies user that message is now in their devices clipboard.
        Toast toast = Toast.makeText(activity, activity.getString(R.string.toast_copy), Toast.LENGTH_SHORT);
        toast.show();

        // Set the clipboard's primary clip.
        clipboard.setPrimaryClip(clip);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerAdapter(AppCompatActivity activity)
    {
        //Used to provide context to the adapter on which activity is being called.
        //In this case, the activity is MainActivity.
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType)
    {
        // Create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cipher_card, parent, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.userMessage.setText(dataSet.get(position).getUserMessage());
        holder.cipherMessage.setText(dataSet.get(position).getCipherMessage());
        holder.btnDelete.setOnClickListener(view -> removeMessage(position));
        holder.btnShare.setOnClickListener(view -> shareMessage(position));
        holder.btnCopy.setOnClickListener(view -> copyMessage(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return dataSet.size();
    }
}