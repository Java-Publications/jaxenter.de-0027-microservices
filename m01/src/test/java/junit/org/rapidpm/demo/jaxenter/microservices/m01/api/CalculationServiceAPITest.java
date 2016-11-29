package junit.org.rapidpm.demo.jaxenter.microservices.m01.api;

import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.demo.jaxenter.microservices.m01.api.CalculationService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

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
public abstract class CalculationServiceAPITest {

  //data sets
  public static final Tuple<Integer, Integer> dataset001 = new Tuple<>(() -> null, Optional.empty());
  public static final Tuple<Integer, Integer> dataset002 = new Tuple<>(() -> Collections.singletonList(1), Optional.of(1));
  public static final Tuple<Integer, Integer> dataset003 = new Tuple<>(() -> Arrays.asList(1, 2), Optional.of(3));
  public static final Tuple<Integer, Integer> dataset004 = new Tuple<>(() -> Arrays.asList(1, null), Optional.of(1));


  public static final Function<Optional<Integer>, Optional<Integer>> notNull = (integerOptional) -> {
    Assert.assertNotNull(integerOptional);
    return integerOptional;
  };

  public static final BiFunction<Optional<Integer>, Optional<Integer>, Optional<Integer>> equals = (optionalA, optionalB) -> {
    Assert.assertEquals(optionalA, optionalB);
    return optionalB;
  };

  protected abstract Supplier<CalculationService> createSupplier();

  @Test
  public void apiTest001() throws Exception {
    equals.apply(
        dataset001.output(),
        notNull.apply(
            createSupplier().get().add(dataset001.input())));
  }

  @Test
  public void apiTest002() throws Exception {
    equals.apply(
        dataset002.output(),
        notNull.apply(
            createSupplier().get().add(dataset002.input())));
  }

  @Test
  public void apiTest003() throws Exception {
    equals.apply(
        dataset003.output(),
        notNull.apply(
            createSupplier().get().add(dataset003.input())));
  }

  @Test
  public void apiTest004() throws Exception {
    equals.apply(
        dataset004.output(),
        notNull.apply(
            createSupplier().get().add(dataset004.input())));
  }


}
