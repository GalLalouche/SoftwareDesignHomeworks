package il.ac.technion.cs.sd.book.ext;

import com.google.inject.AbstractModule;

public class LineStorageModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(LineStorageFactory.class).toInstance(unused -> new TrivialLineStorage());
  }
}
