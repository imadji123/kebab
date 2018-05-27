package com.imadji.kebab;

import android.app.Application;

import com.imadji.kebab.data.DataSource;

/**
 * Created by imadji on 5/27/2018.
 */
public class KebabApplication extends Application {
    private final DataSource dataSource;

    public KebabApplication(DataSource dataSource) {
        this.dataSource = new DataSource();
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
