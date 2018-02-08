package il.ac.technion.cs.sd.sub.ext;

import com.google.inject.AbstractModule;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class LineStorageModule extends AbstractModule {
  private Random random = new Random();

  @Override
  protected void configure() {
    bind(FutureLineStorageFactory.class)
        .toInstance(unused ->
            CompletableFuture.completedFuture(
                Optional.ofNullable(random.nextBoolean() ? new TrivialFutureLineStorage() : null)));
  }
}
