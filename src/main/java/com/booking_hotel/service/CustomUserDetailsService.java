package com.booking_hotel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.booking_hotel.model.AdminUser;
import com.booking_hotel.model.Customer;
import com.booking_hotel.repositories.AdminUserRepository;
import com.booking_hotel.repositories.CustomerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired private CustomerRepository userRepo;

  @Autowired private AdminUserRepository adminUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Customer user = userRepo.findByEmail(username);
    AdminUser adminUser = adminUserRepository.findByEmail(username);
    if (user == null && adminUser == null) {
      throw new UsernameNotFoundException("User not found");
    }
    else if (user != null) {
      List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
      GrantedAuthority authority = new SimpleGrantedAuthority("USER");
      grantList.add(authority);

      UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,
          user.getPassword(), grantList);
      return userDetails;
    }
    else {
      List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
      GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
      grantList.add(authority);

      UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,
          adminUser.getPassword(), grantList);
      return userDetails;
    }
  }
}
