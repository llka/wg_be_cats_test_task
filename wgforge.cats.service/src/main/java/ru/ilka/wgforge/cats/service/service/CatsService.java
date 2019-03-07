package ru.ilka.wgforge.cats.service.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.ilka.wgforge.cats.service.entity.Cat;
import ru.ilka.wgforge.cats.service.entity.enums.CatSortingAttributeEnum;
import ru.ilka.wgforge.cats.service.entity.enums.OrderEnum;
import ru.ilka.wgforge.cats.service.exception.RestException;
import ru.ilka.wgforge.cats.service.repository.CatsRepository;

import java.util.Comparator;
import java.util.List;

@Service
public class CatsService {
    private static final Logger logger = LogManager.getLogger(CatsService.class);

    @Autowired
    private CatsRepository catsRepository;

    public List<Cat> findCats(CatSortingAttributeEnum attribute, OrderEnum order, Integer offset, Integer limit) {
        List<Cat> cats = catsRepository.findAll();

        if (attribute != null && order != null) {
            switch (order) {
                case ASC:
                    cats.sort(Comparator.comparing(attribute.getCompareBy()));
                    break;
                case DESC:
                    cats.sort(Comparator.comparing(attribute.getCompareBy()).reversed());
                    break;
                default:
                    logger.error("Unknown order!");
            }
        }

        if (offset != null && offset != 0) {
            int size = cats.size();
            if (offset < size) {
                cats = cats.subList(offset, size);
            } else {
                throw new RestException("Provided offset is bigger than list size! There are only "
                        + size + " cats.", HttpStatus.BAD_REQUEST);
            }
        }

        if (limit != null) {
            cats = cats.subList(0, Math.min(cats.size(), limit));
        }

        return cats;
    }

    public Cat create(Cat cat, boolean updateIfExists) {
        if (catsRepository.findById(cat.getName()).isPresent()) {
            if (updateIfExists) {
                return catsRepository.save(cat);
            } else {
                throw new RestException("Cat with name: " + cat.getName() + " already exists! " +
                        "Use updateIfExists param to update existing entity with new values.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return catsRepository.save(cat);
        }
    }
}
