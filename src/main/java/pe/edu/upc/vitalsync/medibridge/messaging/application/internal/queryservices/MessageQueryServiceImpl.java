package pe.edu.upc.vitalsync.medibridge.messaging.application.internal.queryservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates.Message;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.services.MessageQueryService;
import pe.edu.upc.vitalsync.medibridge.messaging.infrastructure.persistence.jpa.repositories.MessageRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageQueryServiceImpl implements MessageQueryService {

    private final MessageRepository repo;

    @Override
    public List<Message> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Message> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Message> getByConversation(Long conversationId) {
        return repo.findByConversationIdOrderBySentAtAsc(conversationId);
    }
}
