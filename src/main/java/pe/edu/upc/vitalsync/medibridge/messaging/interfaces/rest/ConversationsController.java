package pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.commands.DeleteConversationCommand;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.services.ConversationCommandService;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.services.ConversationQueryService;
import pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest.resources.*;
import pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest.transform.ConversationResourceAssembler;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conversations")
@RequiredArgsConstructor
public class ConversationsController {

    private final ConversationCommandService commandService;
    private final ConversationQueryService queryService;

    // loadConversations
    @GetMapping
    public ResponseEntity<List<ConversationResource>> getAll() {
        var list = ConversationResourceAssembler.toResourceList(queryService.getAll());
        return ResponseEntity.ok(list);
    }

    // getConversationById
    @GetMapping("/{id}")
    public ResponseEntity<ConversationResource> getById(@PathVariable Long id) {
        return queryService.getById(id)
                .map(ConversationResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // addConversation
    @PostMapping
    public ResponseEntity<ConversationResource> create(@Valid @RequestBody CreateConversationResource body) {
        var created = commandService.create(body.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ConversationResourceAssembler.toResource(created));
    }

    // updateConversation
    @PutMapping("/{id}")
    public ResponseEntity<ConversationResource> update(@PathVariable Long id,
                                                       @Valid @RequestBody UpdateConversationResource body) {
        var updated = commandService.update(body.toCommand(id));
        return ResponseEntity.ok(ConversationResourceAssembler.toResource(updated));
    }

    // deleteConversation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandService.delete(new DeleteConversationCommand(id));
        return ResponseEntity.noContent().build();
    }
}
