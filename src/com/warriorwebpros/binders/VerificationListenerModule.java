package com.warriorwebpros.binders;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.eclipse.swt.events.VerifyListener;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.warriorwebpros.listeners.DigitVerificationListener;

import javax.inject.Qualifier;

public class VerificationListenerModule extends AbstractModule {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Digit{}

    @Provides
    @Digit
    public VerifyListener provideDigitVerificationListener() {
        return new DigitVerificationListener();
    }
}

