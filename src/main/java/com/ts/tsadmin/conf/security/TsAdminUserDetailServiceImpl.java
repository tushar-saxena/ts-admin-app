package com.ts.tsadmin.conf.security;

import com.ts.tsadmin.service.repo.UserRepoService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("tsAdminUserDetailServiceImpl")
public class TsAdminUserDetailServiceImpl implements UserDetailsService {

    private final UserRepoService userRepoService;

    public TsAdminUserDetailServiceImpl(UserRepoService userRepoService) {
        this.userRepoService = userRepoService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepoService.findUserByEmailId(username);
    }
}
