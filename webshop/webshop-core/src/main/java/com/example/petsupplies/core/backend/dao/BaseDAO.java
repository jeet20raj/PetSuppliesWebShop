package com.example.petsupplies.core.backend.dao;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * BaseDAO is abstract class having EntityManager and Logger to be used in All DAO classes extending it.
 * @author Jeetendra 
 * @version 1.0
 * @since 2015-06-11
 */
public abstract class BaseDAO
{
   @Inject
   protected EntityManager entityManager;

   @Inject
   protected transient Logger logger;
}
