package guru.springframework.netfluxexample;

import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.asList;

public class FunctionalProgrammingTest {
	@Test public void countDogs() throws Exception {
		List<String> list = asList("aaabbb", "cccc", "ddddd", "cccddd");
		long count = list.stream().filter(s -> s.length() == 6).count();
		System.out.println(count);
	}

	@Test public void threadRun() throws Exception {
		new Thread(new Runnable() {
			@Override public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		}).start();

		System.out.println(Thread.currentThread().getName());
	}

	@Test public void threadLambda() throws Exception {
		new Thread(() -> System.out.println("thread in lambda " + Thread.currentThread().getId())).start();
		System.out.println("thread in main " + Thread.currentThread().getId());
	}

	@Test public void iterateConsumer() throws Exception {
		List<String> list = asList("aaabbb", "cccc", "ddddd", "cccddd");
		list.forEach(new Consumer<String>() {
			@Override public void accept(String s) {
				System.out.println(s);
			}
		});
	}

	@Test public void iterateLambda() throws Exception {
		List<String> list = asList("aaabbb", "cccc", "ddddd", "cccddd");
		list.forEach(System.out::println);
	}
}

