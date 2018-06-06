package guru.springframework.netfluxexample;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;

public class ClosureTests {

	private List<Integer> integers;

	@Before
	public void init() {
		integers = asList(1, 2, 3, 4, 5, 6);
	}

	@Test public void mapNumbers() throws Exception {
		integers.stream().map(integer -> integer * 2).forEach(System.out::println);
	}
}
