package uz.klimuz.soundengineer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.ChannelsViewHolder> {

    private ArrayList<Channel> channels;
    private OnChannelClickListener onChannelClickListener;

    public ChannelsAdapter(ArrayList<Channel> channels) {
        this.channels = channels;
    }

    interface OnChannelClickListener {
        void onChannelClick(int position);

        void onLongClick(int position);
    }

    public void setOnChannelClickListener(OnChannelClickListener onChannelClickListener) {
        this.onChannelClickListener = onChannelClickListener;
    }

    @NonNull
    @Override
    public ChannelsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.channel_item, viewGroup, false);
        return new ChannelsViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ChannelsViewHolder channelsViewHolder, int i) {
        Channel channel = channels.get(i);
        channelsViewHolder.textViewNumber.setText(String.format("%s", channel.getNumber()));
        channelsViewHolder.textViewRioName.setText(channel.getRioName());
        channelsViewHolder.textViewRioNumber.setText(channel.getRioNumber());
        channelsViewHolder.textViewName.setText(channel.getName());
        channelsViewHolder.textViewPickup.setText(channel.getPickup());
        Log.i("MyResult", channel.getNote());
        channelsViewHolder.textViewNote.setText(channel.getNote());
//        if (channel.getNote() != null) {
//            channelsViewHolder.textViewNote.setText(channel.getNote());
//        }
    }
    @Override
    public int getItemCount() {
        return channels.size();
    }
    class ChannelsViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNumber;
        private TextView textViewRioName;
        private TextView textViewRioNumber;
        private TextView textViewName;
        private TextView textViewPickup;
        private TextView textViewNote;
        private LinearLayout channelItemLinearLayout;

        public ChannelsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            textViewRioName = itemView.findViewById(R.id.textViewRioName);
            textViewRioNumber = itemView.findViewById(R.id.textViewRioNumber);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPickup = itemView.findViewById(R.id.textViewPickup);
            textViewNote = itemView.findViewById(R.id.textViewNote);
            channelItemLinearLayout = itemView.findViewById(R.id.channelItemLinearLayout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onChannelClickListener != null) {
                        onChannelClickListener.onChannelClick(getAdapterPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onChannelClickListener != null) {
                        onChannelClickListener.onLongClick(getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }

    private int maxChannelsForStageBox(int numberOfStageBox) {
        switch (numberOfStageBox) {
            case 1:
                return 32;
            case 2:
                return 64;
            case 3:
                return 16;
            default:
                return 1000;
        }
    }
}
