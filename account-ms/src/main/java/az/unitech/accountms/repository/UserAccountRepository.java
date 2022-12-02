package az.unitech.accountms.repository;

import az.unitech.accountms.model.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity,Long> {

    List<UserAccountEntity> findAllByPin(String pin);

    Optional<UserAccountEntity> findAllByAccountNumber(String accountNumber);

    Boolean existsAllByPinAndAccountNumber(String pin, String accountNumber);
}
