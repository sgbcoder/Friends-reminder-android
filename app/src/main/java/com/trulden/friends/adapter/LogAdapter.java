package com.trulden.friends.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.RecyclerView;

import com.trulden.friends.R;
import com.trulden.friends.adapter.base.BindableViewHolder;
import com.trulden.friends.adapter.base.CustomRVAdapter;
import com.trulden.friends.database.entity.Interaction;

import java.util.HashSet;

import static com.trulden.friends.util.Util.dateFormat;

/**
 * RecyclerView adapter for Interaction objects.
 * Used in LogFragment
 * @see com.trulden.friends.activity.LogFragment LogFragment
 */
public class LogAdapter extends CustomRVAdapter<LogAdapter.ViewHolder, Interaction> {

    // TODO rename to InteractionAdapter

    private LongSparseArray<String> mInteractionTypes = new LongSparseArray<>();

    public LogAdapter(Context context, @NonNull HashSet<Integer> selectedInteractionsPositions){
        super(context, selectedInteractionsPositions);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.log_entry, parent, false));
    }

    public void setInteractionTypes(LongSparseArray<String> interactionTypes){
        mInteractionTypes = interactionTypes;
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder
            implements BindableViewHolder<Interaction> {

        private TextView mTypeAndNames;
        private TextView mDate;
        private TextView mComment;

        private View mLogEntryLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTypeAndNames = itemView.findViewById(R.id.log_entry_type_and_names);
            mDate = itemView.findViewById(R.id.log_entry_date);
            mComment = itemView.findViewById(R.id.log_entry_comment);

            mLogEntryLayout = itemView.findViewById(R.id.log_entry_layout);
        }

        public void bindTo(final Interaction interaction, final int position) {

            // Set data

            String interactionTypeName = mInteractionTypes.get(interaction.getInteractionTypeId());

            mTypeAndNames.setText(interactionTypeName + mContext.getString(R.string.with) + interaction.getFriendNames());
            mDate.setText(dateFormat.format(interaction.getDate()));
            mComment.setText(interaction.getComment());

//            // Strange reaction on click when selected — cards with comment hide it
//            // Couldn't figure why

//            int p = mDate.getPaddingStart();
//            if(mComment.getText().toString().isEmpty() || interaction.getComment() == null || interaction.getComment().isEmpty()){
//                mComment.setPadding(0, 0, 0, 0);
//                mComment.setHeight(0);
//                mDate.setPadding(p, 0, p, p);
//            } else {
//                mComment.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
//                mComment.setPadding(p, 0, p, p);
//                mDate.setPadding(p, 0, p, 0);
//            }

            // Set click listeners

            mLogEntryLayout.setActivated(mSelectedPositions.contains(position));

            mLogEntryLayout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(mOnClickListener == null)
                        return;
                    mOnClickListener.onItemClick(view, interaction, position);
                }
            });

            mLogEntryLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (mOnClickListener == null)
                        return false;

                    mOnClickListener.onItemLongClick(view, interaction, position);
                    return true;
                }
            });

        }
    }
}
