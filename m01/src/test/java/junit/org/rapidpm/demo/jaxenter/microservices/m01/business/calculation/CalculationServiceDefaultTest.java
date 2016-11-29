package junit.org.rapidpm.demo.jaxenter.microservices.m01.business.calculation;

import junit.org.rapidpm.demo.jaxenter.microservices.m01.api.CalculationServiceAPITest;
import org.rapidpm.demo.jaxenter.microservices.m01.api.CalculationService;
import org.rapidpm.demo.jaxenter.microservices.m01.business.calculation.CalculationServiceDefault;

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
public class CalculationServiceDefaultTest extends CalculationServiceAPITest {

  @Override
  protected Supplier<CalculationService> createSupplier() {
    return CalculationServiceDefault::new;
  }

  // add special Tests here

}
