<h2>Fundamentals</h2>
<h4>Points to Remember</h4>

* Creating list via `List.of()` creates an **immutable** list. So, sorting this list raises an `UnsupportedOperationException`
    * To sort a list, use arrays instead like `Arrays.asList()`
* Lambdas can only be used in methods that expect a **functional interface**

> Functional Interface is an interface that specifies exactly one abstract method

> [PATTERN] execute-around pattern: popular in resource processing like opening a resource, processing something and then closing the resource.

* **autoboxing** comes at a performance cost and hence should be avoided
    * to solve this, Java 8 introduced optimized functional interfaces, eg `IntPredicate` is more optimal than than `Predicate<Integer>`
    
* lambdas can access local variables only that are declared final or are effectively final

```java
int port = 8080;
Runnable r = () -> System.out.println(port);    // throws an error as port is not final or effectively final
port = 8081;
```

* method references example
```java
// Before
inventory.sort((Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight());

// Using method reference and java.util.Comparator.comparing
inventory.sort(comparing(Apple::getWeight));
```

* 3 main kinds of method references:
    * static method `Integer::parseInt`
    * method to an object that is supplied as parameter in lambda expression e.g.
    `(String str) -> str.toUpperCase()` can be written as `String::toUpperCase`
    * instance method of existing object or expression
    `() -> expensiveTransaction.getValue()` can be written as `expensiveTransaction::getValue`
    
* constructor reference `ClassName::new`
* Composing lambda expressions:
    * **Comparators**
    ```java
    // basic
    apples.sort(comparing(Apple::getWeight))
    // reversed
    apples.sort(comparing(Apple::getWeight).reversed())
    // secondary sort on apples having same weight
    apples.sort(comparing(Apple::getWeight)
        .reversed()
        .thenComparing(Apple::getCountry))
    ```
    
    * **Predicates**
    ```java
    // negate
    Predicate<Apple> notRedApples = redApple.negate();
    Predicate<Apple> redAndHeavyApples =
      redApple.and(apple -> apple.getWeight() > 150);
    ```
  
    * **Functions**
    ```java
    Function<Integer, Integer> f = x -> x + 1;
    Function<Integer, Integer> g = x -> x * 1;
    Function<Integer, Integer> h = f.andThen(g);      // also written as f.compose(g)
    int result = h.apply(1);
    ```