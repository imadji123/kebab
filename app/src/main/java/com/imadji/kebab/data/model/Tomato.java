package com.imadji.kebab.data.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by imadji on 5/27/2018.
 */
public class Tomato {
    private List<TomatoSlice> tomatoSlices;

    public Tomato() {
        int slices = new Random().nextInt(2) + 1;

        tomatoSlices = new ArrayList<>(slices);
        for (int i = 0; i < slices; i++) {
            tomatoSlices.add(new TomatoSlice());
        }
    }

    public List<TomatoSlice> getTomatoSlices() {
        return new ArrayList<>(tomatoSlices);
    }
}
