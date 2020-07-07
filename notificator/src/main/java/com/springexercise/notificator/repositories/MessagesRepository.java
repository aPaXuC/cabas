package com.springexercise.notificator.repositories;

import com.springexercise.common.model.Severity;
import com.springexercise.notificator.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Severity> {
}
