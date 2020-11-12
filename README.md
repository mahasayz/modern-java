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
  
* collectors mainly offer 3 functionalities:
    * reduce/summarize stream to single value
    * group elements
    * partition elements
    
<h4>Threads</h4>

* parallel stream splits its elements into chunks and processes each chunk with a different thread
    * parallel streams internally use `ForkJoinPool` which by default has as many threads as number of processors `Runtime.getRuntime().availableProcessors()`
    * but you can change the size of the pool as follows
    ```java
    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");
    ```
* Java benchmarking library - Java Microbenchmark Harness (JMH)

* using parallel streams on `Stream.iterate()` is extremely slow in comparison to iterative process because:
    * `iterate` generates boxed objects
    * `iterate` is difficult to divide as the input of one function is the result of previous one
* if you look at the `parallelSum()` example it shows why parallel programming is tricky and counterintuitive
* So, insteead of `Stream.iterate` should use `LongStream` as there is not boxing/unboxing and the stream is generated first before use

> Caveats of Parallelization: Parallelizaton process includes partition stream, assign substream to thread, and combine results to single value.
> Moving data between multiple cores is very expensive, so we should choose tasks for parallelization which take longer to compute than to move data. 

**Fork/Join Framework**
* designed to recursively split parallelizable task into smaller tasks and then combine to produce overall result
* implementation of `ExecutorService` interface - which distributes subtasks to worker threads in thread pool `ForkJoinPool`
    * example shown in code
* real-life code, use `ForkJoinPool` just once and keep it in static field, making it singleton
* total threads in pool = num of processors + virtual processors provided by **hyperthreading**

