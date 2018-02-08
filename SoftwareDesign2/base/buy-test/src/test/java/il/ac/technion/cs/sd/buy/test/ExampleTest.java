package il.ac.technion.cs.sd.buy.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import il.ac.technion.cs.sd.buy.app.BuyProductInitializer;
import il.ac.technion.cs.sd.buy.app.BuyProductReader;
import il.ac.technion.cs.sd.buy.ext.FutureLineStorage;
import il.ac.technion.cs.sd.buy.ext.LineStorageModule;
import io.reactivex.subjects.PublishSubject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExampleTest {

  @Rule public Timeout globalTimeout = Timeout.seconds(20);

  private static Injector setupAndGetInjector(String fileName) throws FileNotFoundException {
    String fileContents =
        new Scanner(new File(ExampleTest.class.getResource(fileName).getFile())).useDelimiter("\\Z").next();
    Injector injector = Guice.createInjector(new BuyProductModule(), new LineStorageModule());
    BuyProductInitializer bpi = injector.getInstance(BuyProductInitializer.class);
    if (fileName.endsWith("xml"))
      bpi.setupXml(fileContents);
    else {
      assert fileName.endsWith("json");
      bpi.setupJson(fileContents);
    }
    return injector;
  }

  @Test
  public void testSimpleXml() throws Exception {
    Injector injector = setupAndGetInjector("small.xml");

    BuyProductReader reader = injector.getInstance(BuyProductReader.class);
    assertEquals(Arrays.asList(5, 10, -1), reader.getHistoryOfOrder("1").get());
  }

  @Test
  public void testSimpleJson() throws Exception {
    Injector injector = setupAndGetInjector("small.json");

    BuyProductReader reader = injector.getInstance(BuyProductReader.class);
    assertEquals(2 * 1000 + 5 * 100 + 100 * 1, reader.getTotalAmountSpentByUser("1").get().intValue());
  }

  private Random r = new Random();

  private static <T> CompletableFuture<T> properHandle(Supplier<CompletableFuture<T>> s, CompletableFuture<T> f) {
    return f.handle((r, e) -> r).thenCompose(r -> r == null ? s.get() : CompletableFuture.completedFuture(r));
  }

  private static <T> CompletableFuture<T> retry(Supplier<CompletableFuture<T>> s) {
    return properHandle(() -> retry(s), s.get());
  }

  private CompletableFuture<Integer> plusOne(int i) {
    return r.nextBoolean() ?
        CompletableFuture.completedFuture(i + 1) :
        CompletableFuture.supplyAsync(() -> {
          throw new IllegalArgumentException();
        });
  }

  private CompletableFuture<Integer> plusOneFixed(int i) {
    return retry(() -> plusOne(i));
  }


  @Test
  public void name() throws Exception {
    int i =
        Stream.iterate(CompletableFuture.completedFuture(0), f -> f.thenCompose(this::plusOneFixed))
            .limit(11)
            .reduce((first, second) -> second).get().get();
    System.out.println(i);
  }

  @Test
  public void testSimpleJson2() throws Exception {
    Injector injector = setupAndGetInjector("small_2.json");

    BuyProductReader reader = injector.getInstance(BuyProductReader.class);
    assertTrue(reader.isValidOrderId("foo1234").get());
    assertTrue(reader.isModifiedOrder("foo1234").get());
    assertTrue(reader.isCanceledOrder("foo1234").get());
  }
}
