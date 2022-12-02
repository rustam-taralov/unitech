package az.unitech.authuserms.repository;

import az.unitech.authuserms.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByPin(String pin);

    Boolean existsAllByPin(String pin);
}
