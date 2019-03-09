package ru.ilka.wgforge.testtask.statistics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.ilka.wgforge.testtask.service.StatisticsService;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FindMeanTest {
    private static final Logger logger = LogManager.getLogger(FindMeanTest.class);
    private StatisticsService statisticsService;
    private static final double DELTA = 0.0001;

    private List<Integer> values;
    private double expectedResult;

    public FindMeanTest(Integer[] values, double expectedResult) {
        this.values = Arrays.asList(values);
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters(name = "{index}: {0} - {1}")
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {new Integer[]{1, 2, 3, 4, 5}, 3},
                {new Integer[]{1, 2, 3, 4, 5, 6}, 3.5},
                {new Integer[]{0, 0, 0}, 0},
                {new Integer[]{0}, 0},
                {new Integer[0], 0},
                {new Integer[]{1, 1, 1, 1, 1}, 1},
                {new Integer[]{1, 2}, 1.5},

        });
    }

    @Before
    public void init() {
        statisticsService = new StatisticsService();
    }

    @Test
    public void findMeanTest() {
        assertEquals(expectedResult, statisticsService.findMean(values), DELTA);
    }
}
