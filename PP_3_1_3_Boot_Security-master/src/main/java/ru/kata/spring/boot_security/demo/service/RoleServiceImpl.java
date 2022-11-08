package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @PersistenceContext
    private EntityManager entityManager;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void saveOrUpdateRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role").getResultList();
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    @Transactional
    public void deleteRoleById(Long id) {
        roleRepository.deleteRoleById(id);
    }

    @Override
    @Transactional
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }
}
