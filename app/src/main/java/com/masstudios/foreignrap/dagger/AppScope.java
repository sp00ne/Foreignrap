package com.masstudios.foreignrap.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * The dagger scope that can be implemented to define tbhe scope of the component
 *
 * @author mofa
 * @since 2017-08-20
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}