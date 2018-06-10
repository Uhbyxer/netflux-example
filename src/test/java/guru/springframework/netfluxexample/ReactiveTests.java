package guru.springframework.netfluxexample;

import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class ReactiveTests {

	private Flux<String> flux;

	@Before public void init() {
		flux = Flux.just("zzzz", "aaabbb", "bbb", "cccc", "ddddd", "eeeee", "ssss");
	}

	@Test public void flux() throws Exception {
		flux.toStream().forEach(System.out::println);

	}

	@Test public void subscribe() throws Exception {
		flux.subscribe(System.out::println);
	}

	@Test public void doOnEach() throws Exception {
		flux.doOnEach(stringSignal -> System.out.println(stringSignal.get())).subscribe();
	}

	@Test public void name() throws Exception {
		flux.subscribe(s -> System.out.println(s), null, () -> System.out.println("Done"));
	}

	@Test public void subscriberConsumers() throws Exception {
		Consumer<String> println = System.out::println;
		Consumer<Throwable> throwableConsumer = Throwable::printStackTrace;
		Runnable runnable = () -> System.out.println("Done");

		flux.subscribe(println, throwableConsumer, runnable);

	}

	@Test public void mapStream() throws Exception {
		flux.map(String::length).doOnEach(s -> System.out.println(s.get())).subscribe();

	}

	@Test public void filter() throws Exception {
		flux.filter(s -> s.length() == 4).take(2).subscribe(System.out::println);
	}

	@Test public void collect() throws Exception {
		flux.filter(s -> s.length() == 4).take(2).sort().collect(Collectors.joining(", "))
				.subscribe(System.out::println);
	}

	@Test public void flatMap() throws Exception {
		List<List<Integer>> lists = asList(asList(1, 2, 3), asList(7, 8, 9, 0));
		Flux<List<List<Integer>>> just = Flux.just(lists);

		just
			.flatMap(Flux::fromIterable)
			.flatMap(Flux::fromIterable)
			.subscribe(System.out::println);
	}

	@Test public void flatMapStream() throws Exception {
		List<List<Integer>> lists = asList(asList(1, 2, 3), asList(7, 8, 9, 0));
		Flux<List<List<Integer>>> just = Flux.just(lists);

		just.flatMap(x -> Flux.fromIterable(x.stream().flatMap(Collection::stream).collect(Collectors.toList())))
		.subscribe(System.out::println);
	}

	@Test public void reduct() throws Exception {
		flux.reduce((s, s2) -> s.concat(";").concat(s2)).subscribe(System.out::println);
	}
}
