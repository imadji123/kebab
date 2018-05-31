package com.imadji.kebab.ui;

import android.support.annotation.NonNull;

import com.imadji.kebab.data.DataSource;
import com.imadji.kebab.data.model.Kebab;
import com.imadji.kebab.data.model.Lettuce;
import com.imadji.kebab.data.model.Meat;
import com.imadji.kebab.data.model.TomatoSlice;
import com.imadji.kebab.data.model.Tortilla;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by imadji on 5/27/2018.
 */
public class KebabViewModel {
    @NonNull
    private final DataSource dataSource;

    @NonNull
    private final BehaviorSubject<Meat> meatSubject = BehaviorSubject.create();

    public KebabViewModel(@NonNull final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void meatAvailable(boolean isFresh) {
        meatSubject.onNext(new Meat(isFresh));
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

    @NonNull
    public Observable<Meat> getMeatStream() {
        return meatSubject.hide();
    }

    @NonNull
    public Observable<Kebab> getKebabStream() {
        return Observable.zip(getTortillaStream(),
                getLettuceStream(),
                getTomatoSliceStream(),
                getCookedMeatStream(),
                this::makeKebab);
    }

    @NonNull
    private Observable<Meat> getCookedMeatStream() {
        return getMeatStream()
                .filter(Meat::isFresh)
                .map(Meat::cook);
    }

    @NonNull
    private Kebab makeKebab(Tortilla tortilla,
                            Lettuce lettuce,
                            TomatoSlice tomatoSlice,
                            Meat meat) {
        return new Kebab(tortilla, lettuce, tomatoSlice, meat);
    }

}
