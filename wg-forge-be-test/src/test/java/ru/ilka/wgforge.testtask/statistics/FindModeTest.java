package ru.ilka.wgforge.testtask.statistics;

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
public class FindModeTest {
    private StatisticsService statisticsService;

    private List<Integer> values;
    private List<Integer> expectedResult;

    public FindModeTest(Integer[] values, Integer[] expectedResult) {
        this.values = Arrays.asList(values);
        this.expectedResult = Arrays.asList(expectedResult);
    }

    @Parameterized.Parameters(name = "{index}: {0} - {1}")
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 2, 3, 4, 5}},
                {new Integer[]{0, 0, 0}, new Integer[]{0}},
                {new Integer[]{0}, new Integer[]{0}},
                {new Integer[]{}, new Integer[]{}},
                {new Integer[]{1, 1, 1, 1, 1, 9, 9}, new Integer[]{1}},
                {new Integer[]{1, 1, 1, 9, 9, 6, 6, 6}, new Integer[]{1, 6}},
        });
    }

    @Before
    public void init() {
        statisticsService = new StatisticsService();
    }

    @Test
    public void findModeTest() {
        assertEquals(expectedResult, statisticsService.findMode(values));
    }
}
