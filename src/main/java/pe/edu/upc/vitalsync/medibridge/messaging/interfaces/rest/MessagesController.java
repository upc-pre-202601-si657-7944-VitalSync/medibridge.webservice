package pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.commands.*;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.services.*;
import pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest.resources.*;
import pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest.transform.MessageResourceAssembler;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessagesController {

    private final MessageCommandService commandService;
    private final MessageQueryService queryService;

    // loadMessages (GET all)
    @GetMapping
    public ResponseEntity<List<MessageResource>> getAll() {
        return ResponseEntity.ok(MessageResourceAssembler.toResourceList(queryService.getAll()));
    }

    // getMessageById
    @GetMapping("/{id}")
    public ResponseEntity<MessageResource> getById(@PathVariable Long id) {
        return queryService.getById(id)
                .map(MessageResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // getMessagesByConversation
    @GetMapping("/conversation/{conversationId}")
    public ResponseEntity<List<MessageResource>> getByConversation(@PathVariable Long conversationId) {
        return ResponseEntity.ok(MessageResourceAssembler.toResourceList(queryService.getByConversation(conversationId)));
    }

    // addMessage
    @PostMapping
    public ResponseEntity<MessageResource> create(@Valid @RequestBody CreateMessageResource body) {
        var created = commandService.create(body.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(MessageResourceAssembler.toResource(created));
    }

    // updateMessage
    @PutMapping("/{id}")
    public ResponseEntity<MessageResource> update(@PathVariable Long id, @Valid @RequestBody UpdateMessageResource body) {
        var updated = commandService.update(body.toCommand(id));
        return ResponseEntity.ok(MessageResourceAssembler.toResource(updated));
    }

    // deleteMessage
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandService.delete(new DeleteMessageCommand(id));
        return ResponseEntity.noContent().build();
    }

    // markAsRead
    @PatchMapping("/{id}/mark-as-read")
    public ResponseEntity<MessageResource> markAsRead(@PathVariable Long id) {
        var updated = commandService.markAsRead(new MarkMessageAsReadCommand(id));
        return ResponseEntity.ok(MessageResourceAssembler.toResource(updated));
    }
}
