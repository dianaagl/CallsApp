package com.learning.callsapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Тичер on 10.06.2017.
 */
public class CallsLoader extends AsyncTaskLoader<List<Call>> {

    private static final String TAG = "CallsLoader";

    private final CallsContentObserver mCallsObserver;

    public CallsLoader(Context context) {
        super(context);
        mCallsObserver = new CallsContentObserver();
        context.getContentResolver().registerContentObserver(
                CallLog.Calls.CONTENT_URI,
                false, mCallsObserver
        );
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        getContext().getContentResolver().unregisterContentObserver(mCallsObserver);
    }

    @Override
    public List<Call> loadInBackground() {
        List<Call> calls = new ArrayList<>();

        Cursor cursor = getCallsCursor();
        if (cursor != null) {
            CallInflater.fillList(cursor, calls);
            cursor.close();
        } else {
            Log.e(TAG, "cursor is null");
        }

        return calls;
    }

    private Cursor getCallsCursor() {
        ContentResolver resolver = getContext().getContentResolver();
        Cursor cursor = null;
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
                cursor = resolver.query(
                    CallLog.Calls.CONTENT_URI,
                    null, null, null, null
            );
        }
        return cursor;
    }

    private class CallsContentObserver extends ContentObserver {

        public CallsContentObserver() {
            super(new Handler(Looper.getMainLooper()));
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            onContentChanged();
        }
    }
}
