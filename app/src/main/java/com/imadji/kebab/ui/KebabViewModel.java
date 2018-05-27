package com.imadji.kebab.ui;

import android.support.annotation.NonNull;

import com.imadji.kebab.data.DataSource;
import com.imadji.kebab.data.model.Lettuce;
import com.imadji.kebab.data.model.TomatoSlice;
import com.imadji.kebab.data.model.Tortilla;

import io.reactivex.Observable;

/**
 * Created by imadji on 5/27/2018.
 */
public class KebabViewModel {
    @NonNull
    private final DataSource dataSource;

    public KebabViewModel(@NonNull final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    public Observable<Tortilla> getTortillaStream() {
        return dataSource.getTortillaStream();
    }

    @NonNull
    public Observable<Lettuce> getLettuceStream() {
        return dataSource.getLettuceStream();
    }

    @NonNull
    public Observable<TomatoSlice> getTomatoSliceStream() {
        return dataSource.getTomatoSliceStream();
    }

}
