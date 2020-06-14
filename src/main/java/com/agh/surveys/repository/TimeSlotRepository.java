package com.agh.surveys.repository;

import com.agh.surveys.model.question.type.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot,Integer> {
}
