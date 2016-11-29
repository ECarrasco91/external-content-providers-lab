package com.ezequielc.externalcontentproviderslab;

import android.database.Cursor;
import android.provider.CalendarContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    CalendarRecyclerViewAdapter mAdapter;

    public static final int CALENDAR_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new CalendarRecyclerViewAdapter(new ArrayList<CalendarEvent>());
        recyclerView.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(CALENDAR_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case CALENDAR_LOADER:
                return new CursorLoader(this,
                        CalendarContract.Events.CONTENT_URI,
                        new String[]{CalendarContract.Events._ID,
                        CalendarContract.Events.CALENDAR_DISPLAY_NAME},
                        null,
                        null,
                        null);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapData(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapData(null);
    }
}
