package com.ennbou.secservice.repositories;

import com.ennbou.secservice.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface RoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleName);
}
