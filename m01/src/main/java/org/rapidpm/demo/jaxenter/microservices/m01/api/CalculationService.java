package org.rapidpm.demo.jaxenter.microservices.m01.api;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
public interface CalculationService {

  default Optional<Integer> add(final List<Integer> values) {
    return (values == null)     ? Optional.empty() :
           (values.size() == 1) ? Optional.ofNullable(values.get(0)) :
                                  Optional.of(values.stream().filter(Objects::nonNull).mapToInt(value -> value).sum());
  }

  default Optional<Integer> add(final Supplier<List<Integer>> values) {
    return (values == null) ? Optional.empty() : add(values.get());
  }


}
