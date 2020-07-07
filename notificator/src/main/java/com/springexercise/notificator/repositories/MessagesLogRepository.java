package com.springexercise.notificator.repositories;

import com.springexercise.notificator.model.MessagesLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesLogRepository extends JpaRepository<MessagesLog, Long> {
}
