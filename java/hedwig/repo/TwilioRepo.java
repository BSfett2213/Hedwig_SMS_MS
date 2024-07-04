package hedwig.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hedwig.entity.TwilioCredentials;

/**
 * Repo for TwilioCredentil class
 */
public interface TwilioRepo extends JpaRepository<TwilioCredentials, Integer>{

}
