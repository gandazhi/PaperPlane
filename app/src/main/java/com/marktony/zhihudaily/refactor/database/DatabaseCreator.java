package com.marktony.zhihudaily.refactor.database;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

public class DatabaseCreator {

    @Nullable
    private static DatabaseCreator INSTANCE = null;

    private AppDatabase mDb;

    private final AtomicBoolean mInitializing = new AtomicBoolean(true);

    private static final Object LOCK = new Object();

    public synchronized static DatabaseCreator getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new DatabaseCreator();
                }
            }
        }
        return INSTANCE;
    }

}
