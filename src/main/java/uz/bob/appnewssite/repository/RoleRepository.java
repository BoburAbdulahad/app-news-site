package uz.bob.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.appnewssite.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
