package com.ts.tsadmin.bootstrap;

import com.ts.tsadmin.domain.Role;
import com.ts.tsadmin.domain.User;
import com.ts.tsadmin.enums.RoleType;
import com.ts.tsadmin.service.repo.RoleRepoService;
import com.ts.tsadmin.service.repo.UserRepoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class ApplicationReadyEventHandlerService implements ApplicationListener<ApplicationReadyEvent> {

    Log log = LogFactory.getLog(ApplicationReadyEventHandlerService.class);

    @Value("${skip.bootstrap:false}")
    Boolean skipBootstrap;

    @Autowired
    UserRepoService userRepoService;

    @Autowired
    RoleRepoService roleRepoService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (skipBootstrap) {
            log.info("Bootstrap skipped as config property 'skip.bootstrap' is set to true");
        } else {
            createTestRole();
            createAdminUser();
        }
    }

    private void createAdminUser() {
        log.info("Admin user created");
        String emailId = "admin@ynd.com";
        if (userRepoService.countUserByEmailId(emailId) < 1) {
            User adminUser = new User("firstName", "lastName", emailId, new BCryptPasswordEncoder().encode("admin"));
            HashSet<Role> roleSet = new HashSet<Role>();
            roleSet.add(roleRepoService.findRoleByAuthority(RoleType.ROLE_ADMIN.name()));
            adminUser.setRoles(roleSet);
            userRepoService.save(adminUser);
        }
    }

    private void createTestRole() {
        if (roleRepoService.countRoleByAuthority(RoleType.ROLE_ADMIN.name()) < 1) {
            Role role1 = new Role();
            role1.setAuthority(RoleType.ROLE_ADMIN.name());
            roleRepoService.save(role1);
        }
        if (roleRepoService.countRoleByAuthority(RoleType.ROLE_USER.name()) < 1) {
            Role role2 = new Role();
            role2.setAuthority(RoleType.ROLE_USER.name());
            roleRepoService.save(role2);
        }
    }
}
