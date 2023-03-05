package com.example.vehiclemanagemennt.Data.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DataTransaction {
    private static DataTransaction instance;
    private List<OnSaveSuccessListener> mOnSaveSuccessListener;
    private List<OnDoneCreateMapListener> mOnDoneCreateMapListener;

    public DataTransaction() {
    }

    public static DataTransaction getInstance() {
        if (instance == null) {
            instance = new DataTransaction();
        }
        return instance;
    }

    public void registerSaveSuccessListener(OnSaveSuccessListener screenListener) {
        if (mOnSaveSuccessListener == null) {
            mOnSaveSuccessListener = new ArrayList<>();
        }
        mOnSaveSuccessListener.add(screenListener);
    }

    public void unregisterSaveSuccessListener(OnSaveSuccessListener screenListener) {
        if (screenListener != null && mOnSaveSuccessListener != null) {
            mOnSaveSuccessListener.remove(screenListener);
        }
    }

    public void pushSaveSuccessListener(String locate) {
        if (mOnSaveSuccessListener != null && mOnSaveSuccessListener.size() > 0) {
            for (OnSaveSuccessListener listener : mOnSaveSuccessListener) {
                listener.onSaveSuccess(locate);
            }
        }
    }

    public void registerOnDoneCreateMapListener(OnDoneCreateMapListener screenListener) {
        if (mOnDoneCreateMapListener == null) {
            mOnDoneCreateMapListener = new ArrayList<>();
        }
        mOnDoneCreateMapListener.add(screenListener);
    }

    public void unregisterOnDoneCreateMapListener(OnDoneCreateMapListener screenListener) {
        if (screenListener != null && mOnDoneCreateMapListener != null) {
            mOnDoneCreateMapListener.remove(screenListener);
        }
    }

    public void pushOnDoneCreateMapListener() {
        if (mOnDoneCreateMapListener != null && mOnDoneCreateMapListener.size() > 0) {
            for (OnDoneCreateMapListener listener : mOnDoneCreateMapListener) {
                listener.onDone();
            }
        }
    }


}
