package il.ac.technion.cs.sd.buy.ext;

import java.util.concurrent.CompletableFuture;

public interface FutureLineStorage {
    /** Appends a line to the end of the file. The future will complete when the writing is complete. */
    CompletableFuture<Void> appendLine(String s);

    /**
     * Returns the contents of the string that was stored in the input line number.
     * @throws IndexOutOfBoundsException If the line number is negative or if the index was too large.
     */
    CompletableFuture<String> read(int lineNumber);

    /** Returns the total number of lines in the file. */
    CompletableFuture<Integer> numberOfLines();
}

