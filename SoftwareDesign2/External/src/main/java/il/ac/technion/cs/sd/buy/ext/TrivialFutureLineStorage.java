package il.ac.technion.cs.sd.buy.ext;

import java.util.concurrent.CompletableFuture;

class TrivialFutureLineStorage implements FutureLineStorage {
  @Override
  public CompletableFuture<Void> appendLine(String s) {
    return CompletableFuture.completedFuture(null);
  }

  @Override
  public CompletableFuture<String> read(int lineNumber) {
    return CompletableFuture.completedFuture("");
  }

  @Override
  public CompletableFuture<Integer> numberOfLines() {
    return CompletableFuture.completedFuture(0);
  }
}
