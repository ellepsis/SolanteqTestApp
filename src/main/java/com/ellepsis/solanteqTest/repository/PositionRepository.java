package com.ellepsis.solanteqTest.repository;

import com.ellepsis.solanteqTest.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ellepsis on 07.09.2016.
 */
@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
}
