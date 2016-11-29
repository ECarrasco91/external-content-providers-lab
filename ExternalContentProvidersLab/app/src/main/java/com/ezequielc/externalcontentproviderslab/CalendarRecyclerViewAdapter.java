package com.ezequielc.externalcontentproviderslab;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by student on 11/29/16.
 */

public class CalendarRecyclerViewAdapter extends RecyclerView.Adapter<CalendarRecyclerViewAdapter.CalendarViewHolder> {
    private List<CalendarEvent> mCalendarEventList;

    public CalendarRecyclerViewAdapter(List<CalendarEvent> calendarEventList){
        mCalendarEventList = calendarEventList;
    }

    @Override
    public CalendarRecyclerViewAdapter.CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CalendarViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @Override
    public void onBindViewHolder(final CalendarRecyclerViewAdapter.CalendarViewHolder holder, int position) {
        holder.mName.setText(mCalendarEventList.get(position).getTitle());
//        holder.mDate.setText(mCalendarEventList.get(position).getDate());

        holder.mName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                long id = mCalendarEventList.get(holder.getAdapterPosition()).getID();

                Uri uriWithId = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, id);
                view.getContext().getContentResolver().delete(uriWithId, null, null);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCalendarEventList.size();
    }

    public void swapData(Cursor cursor){
        mCalendarEventList.clear();

        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                //
//                mCalendarEventList.add(new CalendarEvent());
                cursor.moveToNext();
            }
        }
        notifyDataSetChanged();
    }

    public class CalendarViewHolder extends RecyclerView.ViewHolder{
        TextView mName, mDate;

        public CalendarViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(android.R.id.text1);
            mDate = (TextView) itemView.findViewById(android.R.id.text2);
        }
    }
}
