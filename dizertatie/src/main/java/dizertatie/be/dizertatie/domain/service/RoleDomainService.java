/*
 * Created on 2019-12-26 ( Time 13:27:38 )
 * Generated by Telosys Tools Generator ( version 3.0.0 )
 */

package dizertatie.be.dizertatie.domain.service;

//import io.swagger.models.auth.In;

import dizertatie.be.dizertatie.domain.bean.Role;
import dizertatie.be.dizertatie.domain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleDomainService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findById(Long id) {
        return roleRepository.findById(id).isPresent() ? roleRepository.findById(id).get() : null;
    }

    public List<Role> findAll() {
        return (List<Role>) roleRepository.findAll();
    }

    public long countAll() {
        return roleRepository.count();
    }

    public Role save(Role record) {
        Role saved = roleRepository.save(record);
        return saved;
    }
}