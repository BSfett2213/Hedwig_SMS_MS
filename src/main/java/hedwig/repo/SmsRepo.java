package hedwig.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hedwig.entity.SmsRequest;

/**
 * Repo for SmsRequest class
 */
public interface SmsRepo extends JpaRepository<SmsRequest, Integer>{

}
