package com.alvachien.learning.java.acolingo3.model;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

public final class Constants {
  // Service Namespace
  public static final String NAMESPACE = "OData.Demo";

  // EDM Container
  public static final String CONTAINER_NAME = "Container";
  public static final FullQualifiedName CONTAINER_FQN = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);

  // Entity Types Names
  public static final String ET_PRODUCT_NAME = "Product";
  public static final FullQualifiedName ET_PRODUCT_FQN = new FullQualifiedName(NAMESPACE, ET_PRODUCT_NAME);

  public static final String ET_FINACNTCTGY_NAME = "AccountCategory";
  public static final FullQualifiedName ET_FINACNTCTGY_FQN = new FullQualifiedName(NAMESPACE, ET_FINACNTCTGY_NAME);

  public static final String ET_FINACNT_NAME = "Account";
  public static final FullQualifiedName ET_FINACNT_FQN = new FullQualifiedName(NAMESPACE, ET_FINACNT_NAME);

  // Entity Set Names
  public static final String ES_PRODUCTS_NAME = "Products";
  public static final String ES_FINACNTCTGIES_NAME = "AccountCategories";    
  public static final String ES_FINACNTS_NAME = "Accounts";
}
