package uz.bob.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.appnewssite.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByUsername(String username);

}
