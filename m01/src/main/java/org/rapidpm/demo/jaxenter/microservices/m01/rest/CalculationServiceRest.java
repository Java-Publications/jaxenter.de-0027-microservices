package org.rapidpm.demo.jaxenter.microservices.m01.rest;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.rapidpm.demo.jaxenter.microservices.m01.api.CalculationService;
import sun.misc.BASE64Decoder;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

/**
 * Copyright (C) 2010 RapidPM
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by RapidPM - Team on 28.11.16.
 */
@Path("/service/calculation")
public class CalculationServiceRest {

  public static final String PATH_ADD = "add";
  public static final String VALUE_LIST = "valueList";

  //@formatter:off
  public static final Type INPUT_TYPE = new TypeToken<List<Integer>>() { }.getType();
  public static final Type OUTPUT_TYPE = new TypeToken<Optional<Integer>>() { }.getType();
  //@formatter:on

  @Inject CalculationService calculationService;


  @GET()
  @Path(PATH_ADD)
  @Produces(MediaType.APPLICATION_JSON)
  public String add(@QueryParam(VALUE_LIST) final String values) {
    final Gson gson = new Gson();
    try {
      final String decode = new String(new BASE64Decoder().decodeBuffer(values));

      final List<Integer> intList = gson.fromJson(decode, INPUT_TYPE);
      final Optional<Integer> add = calculationService.add(intList);
      return gson.toJson(add, OUTPUT_TYPE);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return gson.toJson(Optional.empty(), OUTPUT_TYPE);
  }


}
