/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.alvachien.learning.java.acolingo3.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;

/**
 * this class is supposed to declare the metadata of the OData service
 * it is invoked by the Olingo framework e.g. when the metadata document of the service is invoked
 * e.g. http://localhost:8080/ExampleService1/ExampleService1.svc/$metadata
 */
public class DemoEdmProvider extends CsdlAbstractEdmProvider {

  // Service Namespace
  public static final String NAMESPACE = "OData.Demo";

  // EDM Container
  public static final String CONTAINER_NAME = "Container";
  public static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);

  // Entity Types Names
  // public static final String ET_PRODUCT_NAME = "Product";
  // public static final FullQualifiedName ET_PRODUCT_FQN = new FullQualifiedName(NAMESPACE, ET_PRODUCT_NAME);
  public static final String ET_FINACNTCTGY_NAME = "AccountCategory";
  public static final FullQualifiedName ET_FINACNTCTGY_FQN = new FullQualifiedName(NAMESPACE, ET_FINACNTCTGY_NAME);

  // Entity Set Names
  //public static final String ES_PRODUCTS_NAME = "Products";
  public static final String ES_FINACNTCTGY_NAME = "AccountCategories";

  @Override
  public List<CsdlSchema> getSchemas() {

    // create Schema
    CsdlSchema schema = new CsdlSchema();
    schema.setNamespace(NAMESPACE);

    // add EntityTypes
    List<CsdlEntityType> entityTypes = new ArrayList<CsdlEntityType>();
    //entityTypes.add(getEntityType(ET_PRODUCT_FQN));
    entityTypes.add(getEntityType(ET_FINACNTCTGY_FQN));
    schema.setEntityTypes(entityTypes);

    // add EntityContainer
    schema.setEntityContainer(getEntityContainer());

    // finally
    List<CsdlSchema> schemas = new ArrayList<CsdlSchema>();
    schemas.add(schema);

    return schemas;
  }

  @Override
  public CsdlEntityType getEntityType(FullQualifiedName entityTypeName) {

    // this method is called for one of the EntityTypes that are configured in the Schema
    // if(entityTypeName.equals(ET_PRODUCT_FQN)){

    //   //create EntityType properties
    //   CsdlProperty id = new CsdlProperty().setName("ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
    //   CsdlProperty name = new CsdlProperty().setName("Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
    //   CsdlProperty description = new CsdlProperty().setName("Description").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

    //   // create CsdlPropertyRef for Key element
    //   CsdlPropertyRef propertyRef = new CsdlPropertyRef();
    //   propertyRef.setName("ID");

    //   // configure EntityType
    //   CsdlEntityType entityType = new CsdlEntityType();
    //   entityType.setName(ET_PRODUCT_NAME);
    //   entityType.setProperties(Arrays.asList(id, name, description));
    //   entityType.setKey(Collections.singletonList(propertyRef));

    //   return entityType;
    // } 
    //else 
    if(entityTypeName.equals(ET_FINACNTCTGY_NAME)) {
      //create EntityType properties
      CsdlProperty id = new CsdlProperty().setName("ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
      CsdlProperty name = new CsdlProperty().setName("NAME").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
      CsdlProperty assetflg = new CsdlProperty().setName("ASSETFLAG").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
      CsdlProperty comment = new CsdlProperty().setName("COMMENT").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
      CsdlProperty sysflg = new CsdlProperty().setName("SYSFLAG").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
      CsdlProperty crtedby = new CsdlProperty().setName("CREATEDBY").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
      CsdlProperty crtedat = new CsdlProperty().setName("CREATEDAT").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
      CsdlProperty updedby = new CsdlProperty().setName("UPDATEDBY").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
      CsdlProperty updedat = new CsdlProperty().setName("UPDATEDAT").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

      // create CsdlPropertyRef for Key element
      CsdlPropertyRef propertyRef = new CsdlPropertyRef();
      propertyRef.setName("ID");

      // configure EntityType
      CsdlEntityType entityType = new CsdlEntityType();
      entityType.setName(ET_FINACNTCTGY_NAME);
      entityType.setProperties(Arrays.asList(id, name, assetflg, comment, sysflg, crtedby, crtedat, updedby, updedat));
      entityType.setKey(Collections.singletonList(propertyRef));

      return entityType;
    }

    return null;
  }

  @Override
  public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) {

    if(entityContainer.equals(CONTAINER)){
      // if(entitySetName.equals(ES_PRODUCTS_NAME)){
      //   CsdlEntitySet entitySet = new CsdlEntitySet();
      //   entitySet.setName(ES_PRODUCTS_NAME);
      //   entitySet.setType(ET_PRODUCT_FQN);

      //   return entitySet;
      // } 
      // else
      if (entitySetName.equals(ES_FINACNTCTGY_NAME)) {
        CsdlEntitySet entitySet = new CsdlEntitySet();
        entitySet.setName(ES_FINACNTCTGY_NAME);
        entitySet.setType(ET_FINACNTCTGY_FQN);

        return entitySet;
      }
    }

    return null;
  }

  @Override
  public CsdlEntityContainer getEntityContainer() {

    // create EntitySets
    List<CsdlEntitySet> entitySets = new ArrayList<CsdlEntitySet>();
    //entitySets.add(getEntitySet(CONTAINER, ES_PRODUCTS_NAME));
    entitySets.add(getEntitySet(CONTAINER, ES_FINACNTCTGY_NAME));

    // create EntityContainer
    CsdlEntityContainer entityContainer = new CsdlEntityContainer();
    entityContainer.setName(CONTAINER_NAME);
    entityContainer.setEntitySets(entitySets);

    return entityContainer;
  }

  @Override
  public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) {

    // This method is invoked when displaying the service document at e.g. http://localhost:8080/DemoService/DemoService.svc
    if(entityContainerName == null || entityContainerName.equals(CONTAINER)){
      CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();
      entityContainerInfo.setContainerName(CONTAINER);
      return entityContainerInfo;
    }

    return null;
  }
}
