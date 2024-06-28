package hedwig.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hedwig.entity.SinchCredentials;

/**
 * Repo for SinchCredential class
 */
public interface SinchRepo extends JpaRepository<SinchCredentials, Integer>{

}
