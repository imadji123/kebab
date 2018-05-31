package com.imadji.kebab.ui;

import com.imadji.kebab.data.DataSource;
import com.imadji.kebab.data.model.Kebab;
import com.imadji.kebab.data.model.Lettuce;
import com.imadji.kebab.data.model.TomatoSlice;
import com.imadji.kebab.data.model.Tortilla;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.BehaviorSubject;

import static org.mockito.Mockito.when;

/**
 * Created by imadji on 5/29/2018.
 */
public class KebabViewModelTest {
    private static final Tortilla TORTILLA = new Tortilla();
    private static final TomatoSlice TOMATO_SLICE = new TomatoSlice();
    private static final Lettuce LETTUCE = new Lettuce();

    @Mock
    private DataSource dataSource;

    private KebabViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        viewModel = new KebabViewModel(dataSource);
    }

    @Test
    public void getTortillaStream_emits_whenDataSourceEmits() {
        ArrangeBuilder builder = new ArrangeBuilder();
        TestObserver<Tortilla> testObserver = new TestObserver<>();
        viewModel.getTortillaStream().subscribeWith(testObserver);

        builder.withTortilla(TORTILLA);

        testObserver.assertNoErrors();
        testObserver.assertValue(TORTILLA);
    }

    @Test
    public void getTomatoSliceStream_emits_whenDataSourceEmits() {
        ArrangeBuilder builder = new ArrangeBuilder();
        TestObserver<TomatoSlice> testObserver = new TestObserver<>();
        viewModel.getTomatoSliceStream().subscribeWith(testObserver);

        builder.withTomatoSlice(TOMATO_SLICE);

        testObserver.assertNoErrors();
        testObserver.assertValue(TOMATO_SLICE);
    }

    @Test
    public void getLettuceStream_emits_whenDataSourceEmits() {
        ArrangeBuilder builder = new ArrangeBuilder();
        TestObserver<Lettuce> testObserver = new TestObserver<>();
        viewModel.getLettuceStream().subscribeWith(testObserver);

        builder.withLettuce(LETTUCE);

        testObserver.assertNoErrors();
        testObserver.assertValue(LETTUCE);
    }

    @Test
    public void getKebabStream_emits_whenMeatIsFresh() {
        new ArrangeBuilder()
                .withTortilla(TORTILLA)
                .withLettuce(LETTUCE)
                .withTomatoSlice(TOMATO_SLICE);
        TestObserver<Kebab>  testObserver = new TestObserver<>();
        viewModel.getKebabStream().subscribeWith(testObserver);

        viewModel.meatAvailable(true);

        testObserver.assertValueCount(1);
    }

    @Test
    public void getKebabStream_doesNotEmit_whenMeatIsNotFresh() {
        new ArrangeBuilder()
                .withTortilla(TORTILLA)
                .withLettuce(LETTUCE)
                .withTomatoSlice(TOMATO_SLICE);
        TestObserver<Kebab>  testObserver = new TestObserver<>();
        viewModel.getKebabStream().subscribeWith(testObserver);

        viewModel.meatAvailable(false);

        testObserver.assertNoValues();
    }

    private class ArrangeBuilder {
        private BehaviorSubject<Tortilla> tortillaSubject = BehaviorSubject.create();
        private BehaviorSubject<TomatoSlice> tomatoSliceSubject = BehaviorSubject.create();
        private BehaviorSubject<Lettuce> lettuceSubject = BehaviorSubject.create();

        public ArrangeBuilder() {
            when(dataSource.getTortillaStream()).thenReturn(tortillaSubject);
            when(dataSource.getTomatoSliceStream()).thenReturn(tomatoSliceSubject);
            when(dataSource.getLettuceStream()).thenReturn(lettuceSubject);
        }

        ArrangeBuilder withTortilla(Tortilla tortilla) {
            tortillaSubject.onNext(tortilla);
            return this;
        }

        ArrangeBuilder withTomatoSlice(TomatoSlice tomatoSlice) {
            tomatoSliceSubject.onNext(tomatoSlice);
            return this;
        }

        ArrangeBuilder withLettuce(Lettuce lettuce) {
            lettuceSubject.onNext(lettuce);
            return this;
        }
    }

}