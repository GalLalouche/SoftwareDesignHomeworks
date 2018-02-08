package il.ac.technion.cs.sd.sub.ext;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.CompletableFuture;

class TrivialFutureLineStorage implements FutureLineStorage {
  @Override
  public CompletableFuture<Boolean> appendLine(String s) {
    return CompletableFuture.completedFuture(false);
  }

  @Override
  public CompletableFuture<Optional<String>> read(int lineNumber) {
    return CompletableFuture.completedFuture(Optional.empty());
  }

  @Override
  public CompletableFuture<OptionalInt> numberOfLines() {
    return CompletableFuture.completedFuture(OptionalInt.empty());
  }
}
