package com.learning.callsapp;

/**
 * Created by Тичер on 10.06.2017.
 */
public class Call {

    public enum Type {
        INCOMING, OUTGOING, MISSED
    }

    public long id;
    public long date;
    public long duration;
    public boolean read;
    public String number;
    public Type type;

    public long getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public long getDuration() {
        return duration;
    }

    public boolean isRead() {
        return read;
    }

    public String getNumber() {
        return number;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Call call = (Call) o;

        if (id != call.id) return false;
        if (duration != call.duration) return false;
        if (date != call.date) return false;
        if (read != call.read) return false;
        if (type != call.type) return false;
        return number != null ? number.equals(call.number)
                : call.number == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        result = 31 * result + (int) (date ^ (date >>> 32));
        result = 31 * result + (read ? 1 : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + type.ordinal();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Call{");
        sb.append("id=").append(id);
        sb.append(", duration=").append(duration);
        sb.append(", date=").append(date);
        sb.append(", read=").append(read);
        sb.append(", number='").append(number).append('\'');
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
