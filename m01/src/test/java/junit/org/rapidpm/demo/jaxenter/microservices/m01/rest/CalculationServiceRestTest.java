package junit.org.rapidpm.demo.jaxenter.microservices.m01.rest;

import com.google.gson.Gson;
import junit.org.rapidpm.demo.jaxenter.microservices.m01.api.CalculationServiceAPITest;
import junit.org.rapidpm.demo.jaxenter.microservices.m01.api.Tuple;
import junit.org.rapidpm.microservice.BasicRestTest;
import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.demo.jaxenter.microservices.m01.rest.CalculationServiceRest;
import sun.misc.BASE64Encoder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static junit.org.rapidpm.demo.jaxenter.microservices.m01.api.CalculationServiceAPITest.equals;
import static junit.org.rapidpm.demo.jaxenter.microservices.m01.api.CalculationServiceAPITest.notNull;

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
public class CalculationServiceRestTest extends BasicRestTest {

  @Test
  public void add001() throws Exception {
    final Tuple<Integer, Integer> dataSet = CalculationServiceAPITest.dataset001;
    final Optional<Integer> result = execute(dataSet);
    equals.apply(
        dataSet.output(),
        notNull.apply(result));
  }

  @Test
  public void add002() throws Exception {
    final Tuple<Integer, Integer> dataSet = CalculationServiceAPITest.dataset002;
    final Optional<Integer> result = execute(dataSet);
    equals.apply(
        dataSet.output(),
        notNull.apply(result));
  }

  @Test
  public void add003() throws Exception {
    final Tuple<Integer, Integer> dataSet = CalculationServiceAPITest.dataset003;
    final Optional<Integer> result = execute(dataSet);
    equals.apply(
        dataSet.output(),
        notNull.apply(result));
  }

  @Test
  public void add004() throws Exception {
    final Tuple<Integer, Integer> dataSet = CalculationServiceAPITest.dataset004;
    final Optional<Integer> result = execute(dataSet);
    equals.apply(
        dataSet.output(),
        notNull.apply(result));
  }

  private Optional<Integer> execute(final Tuple<Integer, Integer> dataSet) {
    final String basicReqURL = generateBasicReqURL(CalculationServiceRest.class);
    System.out.println("basicReqURL = " + basicReqURL);
    final Gson gson = new Gson();
    final String inputJSON = gson.toJson(dataSet.input().get(),
        CalculationServiceRest.INPUT_TYPE);
    final String encode = new BASE64Encoder().encode(inputJSON.getBytes());
    Client client = ClientBuilder.newClient();
    final Invocation.Builder request = client
        .target(basicReqURL + "/" + CalculationServiceRest.PATH_ADD)
        .queryParam(CalculationServiceRest.VALUE_LIST, encode)
        .request();
    final Response response = request.get();

    Assert.assertEquals(200, response.getStatus());
    Assert.assertEquals("OK", response.getStatusInfo().toString());
    client.close();
    final String data = response.readEntity(String.class);
    return gson.fromJson(data, CalculationServiceRest.OUTPUT_TYPE);
  }

}
