package com.ts.tsadmin.service.repo;

import com.ts.tsadmin.domain.Role;
import com.ts.tsadmin.repository.RoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleRepoService extends BaseRepoService<Role, Long> {

    private final RoleRepository roleRepository;

    public RoleRepoService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    JpaRepository<Role, Long> getRepository() {
        return roleRepository;
    }

    public Long countRoleByAuthority(String authority) {
        return roleRepository.countByAuthority(authority);
    }

    public Role findRoleByAuthority(String authority) {
        return roleRepository.findByAuthority(authority);
    }
}
