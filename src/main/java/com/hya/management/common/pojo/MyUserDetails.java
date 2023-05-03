package com.hya.management.common.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.hya.management.common.domain.UserDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MyUserDetails implements UserDetails {
    UserDO userDO;
    private List<String> permissions;

    public MyUserDetails(UserDO userDO, List<String> permissions) {
        this.userDO = userDO;
        this.permissions = permissions;
    }
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities!=null){
            return authorities;
        }
        //把permissions中字符串类型的权限信息转换成GrantedAuthority对象存入authorities中
        authorities = permissions.stream().
                map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return userDO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDO.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userDO.getStatus();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userDO.getStatus();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userDO.getStatus();
    }

    @Override
    public boolean isEnabled() {
        return userDO.getStatus();
    }
}
