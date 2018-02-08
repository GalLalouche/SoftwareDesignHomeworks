package il.ac.technion.cs.sd.book.ext;

public interface LineStorageFactory {
    /** Opens the file with the given fileName. If the file does not exist, it will be created. */
    LineStorage open(String fileName);
}

