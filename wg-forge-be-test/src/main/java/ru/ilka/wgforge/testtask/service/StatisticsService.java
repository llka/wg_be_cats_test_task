package ru.ilka.wgforge.testtask.service;

import ru.ilka.wgforge.testtask.entity.Cat;
import ru.ilka.wgforge.testtask.entity.CatsStatistics;
import ru.ilka.wgforge.testtask.entity.Statistics;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class StatisticsService {
    public CatsStatistics calculateCatsStatistics(List<Cat> cats) {
        CatsStatistics catsStatistics = new CatsStatistics();
        List<Integer> tailLengthList = cats.stream().map(Cat::getTailLength).collect(Collectors.toList());
        prepareStatistics(catsStatistics::setTailLengthStat, tailLengthList);

        List<Integer> whiskersLengthList = cats.stream().map(Cat::getWhiskersLength).collect(Collectors.toList());
        prepareStatistics(catsStatistics::setWhiskersLengthStat, whiskersLengthList);
        return catsStatistics;
    }

    public double findMean(List<Integer> values) {
        if (checkForEmptyList(values)) {
            return 0.0;
        }
        if (values.isEmpty()) {
            return 0.0;
        }
        return ((double) values.stream().mapToInt(Integer::intValue).sum()) / ((double) values.size());
    }

    public double findMedian(List<Integer> values) {
        if (checkForEmptyList(values)) {
            return 0.0;
        }
        Collections.sort(values);
        int size = values.size();
        if (size % 2 == 0) {
            return ((double) (values.get(size / 2) + values.get((size / 2) - 1))) / 2.0;
        } else {
            return values.get(size / 2);
        }
    }

    public List<Integer> findMode(List<Integer> values) {
        if (checkForEmptyList(values)) {
            return new ArrayList<>();
        }
        Map<Integer, Long> occurrencesMap = values
                .stream()
                .collect(Collectors.groupingBy(Integer::intValue,
                        (Supplier<HashMap<Integer, Long>>) HashMap::new, Collectors.counting()));
        long max = occurrencesMap.values().stream().max(Comparator.naturalOrder()).get();
        List<Integer> mode = new ArrayList<>();
        occurrencesMap.entrySet().stream().filter(entry -> entry.getValue() == max).forEach(entry -> mode.add(entry.getKey()));
        return mode;
    }

    private void prepareStatistics(Consumer<Statistics> statisticsSetter, List<Integer> values) {
        Statistics statistics = new Statistics();
        statistics.setMean(findMean(values));
        statistics.setMedian(findMedian(values));
        statistics.setMode(findMode(values));
        statisticsSetter.accept(statistics);
    }

    private boolean checkForEmptyList(List<Integer> values) {
        return values == null || values.isEmpty();
    }

}
