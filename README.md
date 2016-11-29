# Microservice Development

This demo is divided into modules. Each module will increase the complexity.
 
## Module 01
In this module we are starting with a "normal" Microservice. The dependency to the
[RapidPM - Microservice]( http://www.java-microservice.org) , a MicroKernel based on Undertow, will be used 
to realize a small demo. It shows how you could organize it, and how you could write the tests
with jUnit.

![Class Diagramm]( ./_data/m01/diagram.png)

The Service itself is really simple and provides only one method.

```java
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
```
Based on the default behavior, you could find an implementation. The only difference here 
in this example is that the stream will be a parallel one.

Now we are ready to make a MicroService out of it. For this we are wrapping the Service 
into a REST Endpoint.

```java
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
```
