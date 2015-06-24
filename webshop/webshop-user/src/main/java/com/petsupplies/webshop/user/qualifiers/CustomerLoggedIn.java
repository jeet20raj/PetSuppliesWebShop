package com.petsupplies.webshop.user.qualifiers;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * CustomerLoggedIn qualifier created to produce the logged in customer to be injected by other class.
 * 
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-22
 */

@Qualifier
@Retention(RUNTIME)
@Target({ FIELD, TYPE, METHOD })
public @interface CustomerLoggedIn
{
}