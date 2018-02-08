package il.ac.technion.cs.sd.book.ext;

public interface LineStorage {
    /** Appends a line to the end of the file. */
    void appendLine(String s);

    /**
     * Returns the contents of the string that was stored in the input line number.
     * @throws IndexOutOfBoundsException If the line number is negative or if the index was too large.
     */
    String read(int lineNumber) throws InterruptedException;

    /** Returns the total number of lines in the file. */
    int numberOfLines() throws InterruptedException;
}

