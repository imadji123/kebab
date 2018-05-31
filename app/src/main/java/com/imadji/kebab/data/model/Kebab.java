package com.imadji.kebab.data.model;

/**
 * Created by imadji on 5/27/2018.
 */
public class Kebab {
    private Tortilla tortilla;
    private Lettuce lettuce;
    private TomatoSlice tomatoSlice;
    private Meat meat;

    public Kebab(Tortilla tortilla, Lettuce lettuce, TomatoSlice tomatoSlice, Meat meat) {
        this.tortilla = tortilla;
        this.lettuce = lettuce;
        this.tomatoSlice = tomatoSlice;
        this.meat = meat;
    }
}
