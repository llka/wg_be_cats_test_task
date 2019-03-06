package ru.ilka.wgforge.cats.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilka.wgforge.cats.service.entity.Cat;

@Repository
public interface CatsRepository extends JpaRepository<Cat, String> {
}
