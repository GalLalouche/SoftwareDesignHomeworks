package il.ac.technion.cs.sd.sub.ext;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.CompletableFuture;

public interface FutureLineStorage {
    /**
     * Appends a line to the end of the file. The future will complete when the writing is complete.
     * @return true iff the writing was successful.
     */
    CompletableFuture<Boolean> appendLine(String s);

    /**
     * Returns the contents of the string that was stored in the input line number. If the reading failed,
     * returns empty.
     * @throws IndexOutOfBoundsException If the line number is negative or if the index was too large.
     */
    CompletableFuture<Optional<String>> read(int lineNumber);

    /** Returns the total number of lines in the file. If the fetching failed, returns empty. */
    CompletableFuture<OptionalInt> numberOfLines();
}

