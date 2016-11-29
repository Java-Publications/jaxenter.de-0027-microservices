package junit.org.rapidpm.demo.jaxenter.microservices.m01.api;

import java.util.List;
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
 * Created by RapidPM - Team on 29.11.16.
 */
public class Tuple<I,O> {

  public Tuple(final Supplier<List<I>> input, final Optional<O> output) {
    this.input = input;
    this.output = output;
  }

  private Supplier<List<I>> input;
  private Optional<O> output;

  public Supplier<List<I>> input(){ return input; }

  public Optional<O> output(){return output;}

}
