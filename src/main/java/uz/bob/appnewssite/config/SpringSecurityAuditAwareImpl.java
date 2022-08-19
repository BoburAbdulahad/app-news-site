package uz.bob.appnewssite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.bob.appnewssite.component.DataLoader;
import uz.bob.appnewssite.entity.User;
import uz.bob.appnewssite.repository.RoleRepository;
import uz.bob.appnewssite.utils.AppConstants;

import java.util.Optional;

public class SpringSecurityAuditAwareImpl implements AuditorAware<Long> {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Optional<Long> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null &&
                authentication.isAuthenticated() &&
                !authentication.getPrincipal().equals("anonymousUser")){
            User user = (User) authentication.getPrincipal();
            return Optional.ofNullable(user.getId());
        }

            return Optional.empty();
    }
}
