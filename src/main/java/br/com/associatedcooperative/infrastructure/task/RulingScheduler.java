package br.com.associatedcooperative.infrastructure.task;

import br.com.associatedcooperative.domain.ruling.Ruling;
import br.com.associatedcooperative.infrastructure.persistence.RulingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RulingScheduler {

    private final RulingRepository rulingRepository;

    @Scheduled(cron = Cron.EVERY_1_MINUTES)
    @SchedulerLock(name = "RulingScheduler_rulingSchedulerTask")
    @Transactional(Transactional.TxType.REQUIRED)
    public void rulingSchedulerTask(){
        rulingRepository.findByFimBeforeNowAndStatus_Started().forEach(Ruling::closeRuling);
    }
}
