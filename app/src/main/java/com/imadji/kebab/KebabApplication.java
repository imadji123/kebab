package com.imadji.kebab;

import android.app.Application;
import android.support.annotation.NonNull;

import com.imadji.kebab.data.DataSource;

/**
 * Created by imadji on 5/27/2018.
 */
public class KebabApplication extends Application {
    @NonNull
    private final DataSource dataSource;

    public KebabApplication() {
        this.dataSource = new DataSource();
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
