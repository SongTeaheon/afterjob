package com.song.afterjob.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    private String email;

    @Column
    @NotNull
    private String password;

    @Column
    private String roles;

    @Column
    private String authorities;

    public List<String> getRoleList() {
        if(this.roles == null || this.roles.equals("")){
            return new ArrayList<>();
        }
        return Arrays.asList(roles.split(","));
    }

    public List<String> getAuthorityList() {
        if(this.authorities == null || this.authorities.equals("")){
            return new ArrayList<>();
        }
        return Arrays.asList(authorities.split(","));
    }

    public UserEntity(String email, String password, String roles){
        this.email = email;
        this.password = password;
        this.roles = roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        List<String> roleList = getRoleList();
        if(!roleList.isEmpty()){
            grantedAuthorityList.addAll(roleList.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()));
        }

        List<String> authorityList = getAuthorityList();
        if(!authorityList.isEmpty()){
            grantedAuthorityList.addAll(authorityList.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()));
        }
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
