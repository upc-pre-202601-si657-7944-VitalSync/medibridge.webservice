package pe.edu.upc.vitalsync.medibridge.messaging.application.internal.queryservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates.Conversation;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.services.ConversationQueryService;
import pe.edu.upc.vitalsync.medibridge.messaging.infrastructure.persistence.jpa.repositories.ConversationRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConversationQueryServiceImpl implements ConversationQueryService {

    private final ConversationRepository repo;

    @Override public List<Conversation> getAll() { return repo.findAll(); }
    @Override public Optional<Conversation> getById(Long id) { return repo.findById(id); }
}
