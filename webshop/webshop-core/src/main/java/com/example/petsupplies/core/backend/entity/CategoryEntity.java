package com.example.petsupplies.core.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.example.petsupplies.core.common.constants.Constants;

/**
 * CategoryEntity is used to define all attributes related product categories.
 * @author Jeetendra
 * @version 1.0
 * @since 2015-06-09
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "product_categories")
@NamedQueries({ @NamedQuery(name = Constants.QueryConstants.FIND_CATEGORIES, query = "Select ce from CategoryEntity ce where ce.obsolete=0") })
public class CategoryEntity extends AbstractJPAEntity
{

   private Long categoryId;

   private String categoryName;

   private String description;

   private int obsolete;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "category_id", length = 10)
   public Long getCategoryId()
   {
      return categoryId;
   }

   public void setCategoryId(Long categoryId)
   {
      this.categoryId = categoryId;
   }

   @Column(name = "name", length = 50, unique = true)
   public String getCategoryName()
   {
      return categoryName;
   }

   public void setCategoryName(String categoryName)
   {
      this.categoryName = categoryName;
   }

   @Column(name = "description", length = 150)
   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   @Column(name = "obsolete", length = 1, nullable = false)
   public int getObsolete()
   {
      return obsolete;
   }

   public void setObsolete(int obsolete)
   {
      this.obsolete = obsolete;
   }

}
