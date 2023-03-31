package com.warriorwebpros.binders;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.warriorwebpros.listeners.ActorListChangedListener;
import com.warriorwebpros.model.Actor;
import com.warriorwebpros.service.ActorDataService;
import com.warriorwebpros.service.ActorSortingService;

import javax.inject.Qualifier;

public class ActorDataModule extends AbstractModule {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RandomTimeSeed {}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ActorList {}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ActorListeners {}

    @Provides
    @RandomTimeSeed
    public RandomGenerator provideRandom() {
        return new Random(java.time.LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
    }

    @Provides
    @ActorList
    public List<Actor> provideActorList() {
        return new ArrayList<>();
    }

    @Provides
    @ActorListeners
    public List<ActorListChangedListener> provideListChangeListeners() {
        return new ArrayList<>();
    }

}
