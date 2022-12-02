package az.unitech.currencyratems.repository;

import az.unitech.currencyratems.model.CurrencyRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRateEntity,Long> {

    CurrencyRateEntity findByFromCurrencyAndToCurrency(String from, String to);
}
