package com.learning.callsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Диана on 10.06.2017.
 */
public class CallsAdapter extends RecyclerView.Adapter< CallsAdapter.CallViewHolder> {

    private final static String TAG="Adapter";
    List<Call> listCalls = new ArrayList<>();
    LayoutInflater inflater;



    public CallsAdapter(List<Call> list, Context context) {
        inflater = LayoutInflater.from(context);
        this.listCalls = new ArrayList<>(list);
        Log.e(TAG,"list="+ list);

    }

    @Override
    public CallsAdapter.CallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.call_item,parent,false);
        Log.e(TAG,"msg");
        return new CallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CallsAdapter.CallViewHolder holder, int position) {
        Log.e(TAG,"bindView "+ position);
        holder.bindView(listCalls.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return listCalls.size();
    }
    public class CallViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "ViewHolder";
        private final TextView idView;
        private final TextView numberView;
        private final TextView dateView;
        private final TextView typeView;
        private final TextView durationView;
        private final TextView readView;

        public CallViewHolder(View itemView) {
            super(itemView);
            idView = (TextView) itemView.findViewById(R.id.id);
            numberView = (TextView) itemView.findViewById(R.id.number);
            dateView = (TextView) itemView.findViewById(R.id.date);
            typeView = (TextView) itemView.findViewById(R.id.type);
            durationView = (TextView) itemView.findViewById(R.id.duration);
            readView = (TextView) itemView.findViewById(R.id.read);

        }

        public void bindView(Call call) {
            // Log.e(TAG,"call="+call);
            idView.setText("id="+String.valueOf(call.getId()));
            numberView.setText("number = "+String.valueOf(call.getNumber()));
            dateView.setText("date="+String.valueOf(call.getDate()));
            typeView.setText("type="+String.valueOf(call.getType()));
            durationView.setText("duration="+String.valueOf(call.getDuration()));
            readView.setText("read="+String.valueOf(call.isRead()));
            Log.e(TAG,"call= "+ call);
        }
    }

}
