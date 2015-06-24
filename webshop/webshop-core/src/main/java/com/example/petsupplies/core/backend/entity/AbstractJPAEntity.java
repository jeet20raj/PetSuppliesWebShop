package com.example.petsupplies.core.backend.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * AbstractJPAEntity is used to have all the common columns
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-09
 */
@MappedSuperclass
public class AbstractJPAEntity implements Serializable
{
   private Date updatedDate;

   private Date creationDate;

   public void setUpdatedDate(Date updatedDate)
   {
      this.updatedDate = updatedDate;
   }

   public void setCreationDate(Date creationDate)
   {
      this.creationDate = creationDate;
   }

   @Column(name = "updated_date")
   public Date getUpdatedDate()
   {
      return updatedDate;
   }

   @Column(name = "creation_date")
   public Date getCreationDate()
   {
      return creationDate;
   }

   @PreUpdate
   void updateLastUpdatedDate()
   {
      updatedDate = new Date();
   }

   @PrePersist
   void storeCreationDate()
   {
      creationDate = new Date();
   }
}
