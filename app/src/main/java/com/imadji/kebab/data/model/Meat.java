package com.imadji.kebab.data.model;

/**
 * Created by imadji on 5/27/2018.
 */
public class Meat {
    private boolean isFresh;

    public Meat(boolean isFresh) {
        this.isFresh = isFresh;
    }

    public boolean isFresh() {
        return isFresh;
    }

    public Meat cook() {
        return this;
    }
}
