package hedwig.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hedwig.entity.Merchant;

/**
 * Repo for Merchant class
 */
public interface MerchantRepo extends JpaRepository<Merchant, Integer>{


}
