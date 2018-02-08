package il.ac.technion.cs.sd.buy.ext;

import java.util.concurrent.CompletableFuture;

public interface FutureLineStorageFactory {
    /** Opens the file with the given fileName. If the file does not exist, it will be created. */
    CompletableFuture<FutureLineStorage> open(String fileName);
}

