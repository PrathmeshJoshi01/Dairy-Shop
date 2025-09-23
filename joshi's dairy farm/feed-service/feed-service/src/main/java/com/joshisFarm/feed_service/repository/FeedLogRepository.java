package com.joshisFarm.feed_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joshisFarm.feed_service.entity.FeedLog;

public interface FeedLogRepository extends JpaRepository<FeedLog, Long>{
	List<FeedLog>findByAnimalId(Long animalId);
}
