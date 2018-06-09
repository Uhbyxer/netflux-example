package guru.springframework.netfluxexample;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class StreamsTest {

	private List<String> list;

	@Before
	public void init() {
		list = asList("zzzz", "aaabbb", "bbb", "cccc", "ddddd", "eeeee");
	}

	@Test
	public void parallel() throws Exception {
		list.parallelStream().forEach(s -> {
			System.out.println(Thread.currentThread().getName().concat(". ").concat(s));
		});
	}

	@Test public void map() throws Exception {
		list.stream().map(String::length).forEach(System.out::println);
	}

	@Test public void limit() throws Exception {
		list.stream().limit(3).forEach(System.out::println);
	}

	@Test public void sort() throws Exception {
		list.stream().limit(3).sorted().forEach(System.out::println);
	}

	@Test public void join() throws Exception {
		String collect = list.stream()
		.limit(3)
		.map(String::toUpperCase)
		.sorted()
		.collect(Collectors.joining(";"));
		System.out.println(collect);
	}

	@Test public void stat() throws Exception {
		List<Integer> integers = asList(1, 4, 6, 7, 8, 9);
		IntSummaryStatistics statistics =
		integers.stream().mapToInt(value -> value).summaryStatistics();
		System.out.println(statistics);

	}

	@Test public void sumCollector() throws Exception {
		List<Integer> integers = asList(1, 4, 6, 7, 8, 9);

		IntSummaryStatistics collect = integers.stream().collect(Collectors.summarizingInt(value -> value));
		System.out.println(collect);
	}

	@Test public void groupBy() throws Exception {
		Map<Integer, Set<String>> collect = list.stream()
				.collect(Collectors.groupingBy(o -> o.length(), Collectors.toSet()));

		collect.entrySet().stream().forEach(System.out::println);
	}

	@Test public void flatMap() throws Exception {
		List<List<Integer>> lists = asList(asList(1, 8, 3), asList(4, 5, 6));

		List<Integer> collect = lists.stream().flatMap(Collection::stream).collect(Collectors.toList());

		collect.forEach(System.out::println);

	}

	@Test public void reduct() throws Exception {
		Optional<String> reduce = list.stream().reduce((s, s2) -> s + "*" + s2);
		System.out.println(reduce.get());
	}
}
