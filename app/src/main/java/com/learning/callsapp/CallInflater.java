package com.learning.callsapp;

import android.database.Cursor;
import android.provider.CallLog;

import java.util.List;

/**
 * Created by Тичер on 10.06.2017.
 */
public class CallInflater {
    public static void fillList(Cursor source, List<Call> target) {
        if (source.moveToFirst()) {
            while (!source.isAfterLast()) {
                target.add(createCallFromCursor(source));
                source.moveToNext();
            }
        }
    }

    private static Call createCallFromCursor(Cursor cursor) {
        Call call = new Call();
        call.id = getLong(cursor, CallLog.Calls._ID);
        call.date = getLong(cursor, CallLog.Calls.DATE);
        call.duration = getLong(cursor, CallLog.Calls.DURATION);
        call.number = getString(cursor, CallLog.Calls.NUMBER);
        call.type = getCallType(getInt(cursor, CallLog.Calls.TYPE));
        call.read = getInt(cursor, CallLog.Calls.IS_READ) != 0;
        return call;
    }

    private static long getLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    private static String getString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    private static int getInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    private static Call.Type getCallType(int typeOrdinal) {
        Call.Type type = null;
        switch (typeOrdinal) {
            case CallLog.Calls.INCOMING_TYPE: {
                type = Call.Type.INCOMING;
                break;
            }
            case CallLog.Calls.OUTGOING_TYPE: {
                type = Call.Type.OUTGOING;
                break;
            }
            case CallLog.Calls.MISSED_TYPE: {
                type = Call.Type.MISSED;
                break;
            }
        }
        return type;
    }
}
