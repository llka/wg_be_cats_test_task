package ru.ilka.wgforge.cats.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ilka.wgforge.cats.service.entity.Cat;

//@Repository
public interface CatsRepository extends CrudRepository<Cat, String> {
}
