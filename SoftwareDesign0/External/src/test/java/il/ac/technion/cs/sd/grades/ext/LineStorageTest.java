package il.ac.technion.cs.sd.grades.ext;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LineStorageTest {
  @Test
  public void name() throws Exception {
    assertThat(new ArrayList<Integer>(), is(Matchers.empty()));
  }
}