package com.learning.callsapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public class CallsActivity extends AppCompatActivity {

    private static final String TAG = "CallsActivity";

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int LOADER_ID = 1;
    private List<Call> callsList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calls_activity);
        checkCallLogPermission();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        checkCallLogPermission();
    }

    private void checkCallLogPermission() {
        int permissionResult = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALL_LOG);
        if (permissionResult == PackageManager.PERMISSION_GRANTED) {
            getSupportLoaderManager().initLoader(LOADER_ID, null,
                    new CallsLoaderCallbacks());
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_CALL_LOG},
                    PERMISSION_REQUEST_CODE);
        }
    }

    private class CallsLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<Call>> {

        @Override
        public Loader<List<Call>> onCreateLoader(int id, Bundle args) {

            return new CallsLoader(CallsActivity.this);
        }

        @Override
        public void onLoadFinished(Loader<List<Call>> loader, List<Call> data) {
            Log.e(TAG, "calls = "+data);
            callsList = new ArrayList<>(data);
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CallsActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            CallsAdapter adapter = new CallsAdapter(callsList,CallsActivity.this);

            recyclerView.setAdapter(adapter);
        }

        @Override
        public void onLoaderReset(Loader<List<Call>> loader) {

        }
    }
}
