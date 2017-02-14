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
import java.util.List;

import com.alvachien.learning.java.acolingo3.model.*;
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

  @Override
  public List<CsdlSchema> getSchemas() {

    // create Schema
    CsdlSchema schema = new CsdlSchema();
    schema.setNamespace(Constants.NAMESPACE);

    // add EntityTypes
    List<CsdlEntityType> entityTypes = new ArrayList<CsdlEntityType>();
    entityTypes.add(getEntityType(Constants.ET_PRODUCT_FQN));
    entityTypes.add(getEntityType(Constants.ET_FINACNTCTGY_FQN));
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

    CsdlEntityType entityType = null;

    // this method is called for one of the EntityTypes that are configured in the Schema
    if(entityTypeName.equals(Constants.ET_PRODUCT_FQN)){

      //create EntityType properties
      CsdlProperty id = new CsdlProperty().setName("ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
      CsdlProperty name = new CsdlProperty().setName("Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
      CsdlProperty description = new CsdlProperty().setName("Description").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

      // create CsdlPropertyRef for Key element
      CsdlPropertyRef propertyRef = new CsdlPropertyRef();
      propertyRef.setName("ID");

      // // navigation property: many-to-one, null not allowed (product must have a category)
      // CsdlNavigationProperty navProp = new CsdlNavigationProperty().setName("Category")
      //     .setType(ET_CATEGORY_FQN).setNullable(false).setPartner("Products");
      // List<CsdlNavigationProperty> navPropList = new ArrayList<CsdlNavigationProperty>();
      // navPropList.add(navProp);

      // configure EntityType
      entityType = new CsdlEntityType();
      entityType.setName(Constants.ET_PRODUCT_NAME);
      entityType.setProperties(Arrays.asList(id, name, description));
      entityType.setKey(Arrays.asList(propertyRef));
      //entityType.setNavigationProperties(navPropList);
    } 
    else if(entityTypeName.equals(Constants.ET_FINACNTCTGY_FQN)) {
      //create EntityType properties
      CsdlProperty id = new CsdlProperty().setName("ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
      CsdlProperty name = new CsdlProperty().setName("NAME").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
      CsdlProperty assetflg = new CsdlProperty().setName("ASSETFLAG").setType(EdmPrimitiveTypeKind.Boolean.getFullQualifiedName());
      CsdlProperty comment = new CsdlProperty().setName("COMMENT").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
      CsdlProperty sysflg = new CsdlProperty().setName("SYSFLAG").setType(EdmPrimitiveTypeKind.Boolean.getFullQualifiedName());
      CsdlProperty crtedby = new CsdlProperty().setName("CREATEDBY").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
      CsdlProperty crtedat = new CsdlProperty().setName("CREATEDAT").setType(EdmPrimitiveTypeKind.Date.getFullQualifiedName());
      CsdlProperty updedby = new CsdlProperty().setName("UPDATEDBY").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
      CsdlProperty updedat = new CsdlProperty().setName("UPDATEDAT").setType(EdmPrimitiveTypeKind.Date.getFullQualifiedName());

      // create CsdlPropertyRef for Key element
      CsdlPropertyRef propertyRef = new CsdlPropertyRef();
      propertyRef.setName("ID");

      // // navigation property: one-to-many
      // CsdlNavigationProperty navProp = new CsdlNavigationProperty().setName("Products")
      //     .setType(ET_PRODUCT_FQN).setCollection(true).setPartner("Category");
      // List<CsdlNavigationProperty> navPropList = new ArrayList<CsdlNavigationProperty>();
      // navPropList.add(navProp);

      // configure EntityType
      entityType = new CsdlEntityType();
      entityType.setName(Constants.ET_FINACNTCTGY_NAME);
      entityType.setProperties(Arrays.asList(id, name, assetflg, comment, sysflg, crtedby, crtedat, updedby, updedat));
      entityType.setKey(Arrays.asList(propertyRef));
      //entityType.setNavigationProperties(navPropList);
    }

    return entityType;
  }

  @Override
  public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) {

    CsdlEntitySet entitySet = null;

    if(entityContainer.equals(Constants.CONTAINER_FQN)){
      if(entitySetName.equals(Constants.ES_PRODUCTS_NAME)){
        entitySet = new CsdlEntitySet();
        entitySet.setName(Constants.ES_PRODUCTS_NAME);
        entitySet.setType(Constants.ET_PRODUCT_FQN);

        // // navigation
        // CsdlNavigationPropertyBinding navPropBinding = new CsdlNavigationPropertyBinding();
        // navPropBinding.setTarget("Categories"); // the target entity set, where the navigation property points to
        // navPropBinding.setPath("Category"); // the path from entity type to navigation property
        // List<CsdlNavigationPropertyBinding> navPropBindingList = new ArrayList<CsdlNavigationPropertyBinding>();
        // navPropBindingList.add(navPropBinding);
        // entitySet.setNavigationPropertyBindings(navPropBindingList);

      } 
      else if (entitySetName.equals(Constants.ES_FINACNTCTGIES_NAME)) {
        entitySet = new CsdlEntitySet();
        entitySet.setName(Constants.ES_FINACNTCTGIES_NAME);
        entitySet.setType(Constants.ET_FINACNTCTGY_FQN);

        // // navigation
        // CsdlNavigationPropertyBinding navPropBinding = new CsdlNavigationPropertyBinding();
        // navPropBinding.setTarget("Products"); // the target entity set, where the navigation property points to
        // navPropBinding.setPath("Products"); // the path from entity type to navigation property
        // List<CsdlNavigationPropertyBinding> navPropBindingList = new ArrayList<CsdlNavigationPropertyBinding>();
        // navPropBindingList.add(navPropBinding);
        // entitySet.setNavigationPropertyBindings(navPropBindingList);
      }
    }

    return entitySet;
  }

  @Override
  public CsdlEntityContainer getEntityContainer() {

    // create EntitySets
    List<CsdlEntitySet> entitySets = new ArrayList<CsdlEntitySet>();
    entitySets.add(getEntitySet(Constants.CONTAINER_FQN, Constants.ES_PRODUCTS_NAME));
    entitySets.add(getEntitySet(Constants.CONTAINER_FQN, Constants.ES_FINACNTCTGIES_NAME));

    // create EntityContainer
    CsdlEntityContainer entityContainer = new CsdlEntityContainer();
    entityContainer.setName(Constants.CONTAINER_NAME);
    entityContainer.setEntitySets(entitySets);

    return entityContainer;
  }

  @Override
  public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) {

    // This method is invoked when displaying the service document at e.g. http://localhost:8080/DemoService/DemoService.svc
    if(entityContainerName == null || entityContainerName.equals(Constants.CONTAINER_FQN)){
      CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();
      entityContainerInfo.setContainerName(Constants.CONTAINER_FQN);
      
      return entityContainerInfo;
    }

    return null;
  }
}
