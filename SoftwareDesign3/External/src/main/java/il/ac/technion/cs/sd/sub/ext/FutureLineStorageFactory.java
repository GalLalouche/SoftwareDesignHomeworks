package il.ac.technion.cs.sd.sub.ext;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface FutureLineStorageFactory {
    /**
     * Opens the file with the given fileName. If the file does not exist, it will be created.
     * If the process failed, returns empty.
     */
    CompletableFuture<Optional<FutureLineStorage>> open(String fileName);
}

