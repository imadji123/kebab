package com.imadji.kebab.data;

import android.support.annotation.NonNull;

import com.imadji.kebab.data.model.Lettuce;
import com.imadji.kebab.data.model.Tomato;
import com.imadji.kebab.data.model.TomatoSlice;
import com.imadji.kebab.data.model.Tortilla;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by imadji on 5/27/2018.
 */
public class DataSource {
    @NonNull
    private static final List<Tortilla> TORTILLAS = new ArrayList<>(
            Arrays.asList(new Tortilla(), new Tortilla(), new Tortilla()));

    @NonNull
    private static final List<Tomato> TOMATOES = new ArrayList<>(
            Arrays.asList(new Tomato(), new Tomato()));

    @NonNull
    private static final List<Lettuce> LETTUCES = new ArrayList<>(
            Arrays.asList(new Lettuce(), new Lettuce(), new Lettuce()));


    @NonNull
    public Observable<Tortilla> getTortillaStream() {
        return Observable.fromIterable(TORTILLAS);
    }

    @NonNull
    public Observable<TomatoSlice> getTomatoSliceStream() {
        return Observable.fromIterable(TOMATOES)
                .flatMap(tomato -> Observable.fromIterable(tomato.getTomatoSlices()));
    }

    @NonNull
    public Observable<Lettuce> getLettuceStream() {
        return Observable.fromIterable(LETTUCES);
    }
}
