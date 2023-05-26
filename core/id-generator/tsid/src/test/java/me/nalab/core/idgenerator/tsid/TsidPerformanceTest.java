package me.nalab.core.idgenerator.tsid;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import org.awaitility.Awaitility;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/*
	bench 1.    Test1 : 142 / Test2 : 121
	bench 2.    Test1 : 140 / Test2 : 129
	bench 3.    Test1 : 143 / Test2 : 121
	bench 4.    Test1 : 137 / Test2 : 121
	bench 5.    Test1 : 166 / Test2 : 125
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TsidConfigurer.class, ThreadTsidActor.class, DefaultTsidActor.class})
class TsidPerformanceTest {

	@Autowired
	private ThreadTsidActor threadTsidActor;

	@Autowired
	private DefaultTsidActor defaultTsidActor;

	private static final Logger LOGGER = Logger.getLogger(TsidPerformanceTest.class.getSimpleName());

	@Test
	@DisplayName("[TEST 1] : Default Tsid Generator 성능, id 중복 테스트")
	void DEFAULT_TSID_ACTOR_PERFORMANCE_AND_DUPLICATION_MEASURE() {
		// given
		int expectedGeneratedId = 10000;
		ExecutorService executorService = Executors.newFixedThreadPool(255);
		List<Callable<Long>> defaultTsidRunnerList = getDefaultTsidRunner(expectedGeneratedId);

		// when & then
		Instant beforeTime = Instant.now();

		Awaitility.waitAtMost(Duration.ofMillis(10000))
			.until(() -> generateId(executorService, defaultTsidRunnerList).size(),
				Matchers.equalTo(expectedGeneratedId));

		Instant afterTime = Instant.now();

		// measure
		LOGGER.info(() -> "[TEST 1] : " + Duration.between(beforeTime, afterTime).toMillis());
	}

	@Test
	@DisplayName("[TEST 2] : THREAD Tsid Generator 성능, id 중복 테스트")
	void THREAD_TSID_ACTOR_PERFORMANCE_AND_DUPLICATION_MEASURE() {
		// given
		int expectedGeneratedId = 10000;
		ExecutorService executorService = Executors.newFixedThreadPool(255);
		List<Callable<Long>> defaultTsidRunnerList = getThreadTsidRunner(expectedGeneratedId);

		// when & then
		Instant beforeTime = Instant.now();

		Awaitility.waitAtMost(Duration.ofMillis(10000))
			.until(() -> generateId(executorService, defaultTsidRunnerList).size(),
				Matchers.equalTo(expectedGeneratedId));

		Instant afterTime = Instant.now();

		// measure
		LOGGER.info(() -> "[TEST 2] : " + Duration.between(beforeTime, afterTime).toMillis());
	}

	private List<Callable<Long>> getDefaultTsidRunner(int size) {
		List<Callable<Long>> ans = new ArrayList<>();
		for(int i = 0; i < size; i++) {
			ans.add(defaultTsidActor::act);
		}
		return ans;
	}

	private List<Callable<Long>> getThreadTsidRunner(int size) {
		List<Callable<Long>> ans = new ArrayList<>();
		for(int i = 0; i < size; i++) {
			ans.add(threadTsidActor::act);
		}
		return ans;
	}

	private Set<Long> generateId(ExecutorService executorService, List<Callable<Long>> callableList) throws Exception {
		Set<Long> ans = new HashSet<>();
		List<Future<Long>> futureList = executorService.invokeAll(callableList);
		for(Future<Long> future : futureList) {
			ans.add(future.get());
		}
		return ans;
	}

}
