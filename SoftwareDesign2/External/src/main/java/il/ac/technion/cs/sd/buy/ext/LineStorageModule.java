package il.ac.technion.cs.sd.buy.ext;

import com.google.inject.AbstractModule;

import java.util.concurrent.CompletableFuture;

public class LineStorageModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(FutureLineStorageFactory.class)
        .toInstance(unused -> CompletableFuture.completedFuture(new TrivialFutureLineStorage()));
  }
}
