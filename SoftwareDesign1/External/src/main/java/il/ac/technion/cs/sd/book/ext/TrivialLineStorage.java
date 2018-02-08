package il.ac.technion.cs.sd.book.ext;

class TrivialLineStorage implements LineStorage{
  @Override
  public void appendLine(String s) { }

  @Override
  public String read(int lineNumber) throws InterruptedException {
    return "";
  }

  @Override
  public int numberOfLines() throws InterruptedException {
    return 0;
  }
}
