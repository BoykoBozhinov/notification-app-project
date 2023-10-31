package com.notification.notificationapp.repository;

import com.notification.notificationapp.model.entity.SmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<SmsEntity, Long> {

}
