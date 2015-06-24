package com.example.petsupplies.core.backend.dao;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Jeetendra BaseDAO is abstract class having EntityManager and Logger to be used in All DAO classes extending it.
 */
public abstract class BaseDAO
{
   @Inject
   protected EntityManager entityManager;

   @Inject
   protected transient Logger logger;
}
