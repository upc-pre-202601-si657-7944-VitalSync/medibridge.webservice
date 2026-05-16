package pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.vitalsync.medibridge.shared.domain.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;

@Entity
@Table(name = "conversations",
        indexes = {
                @Index(name="idx_conv_doctor", columnList = "doctor_id"),
                @Index(name="idx_conv_patient", columnList = "patient_id")
        })
@Getter @Setter @NoArgsConstructor
public class Conversation extends AuditableAbstractAggregateRoot<Conversation> {

    @NotNull
    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @NotNull
    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "doctor_name", nullable = false)
    private String doctorName;

    @Column(name = "patient_name", nullable = false)
    private String patientName;

    // Resumen del último mensaje para listar rápido
    @Column(name = "last_message_preview")
    private String lastMessagePreview;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_message_at")
    private Date lastMessageAt;

    // Unread por cada actor
    @Column(name = "unread_for_doctor")
    private Integer unreadForDoctor = 0;

    @Column(name = "unread_for_patient")
    private Integer unreadForPatient = 0;

    public Conversation(Long doctorId, Long patientId, String doctorName, String patientName) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.patientName = patientName;
    }

    public void touch(String preview, Date when, boolean messageFromDoctor) {
        this.lastMessagePreview = preview;
        this.lastMessageAt = when;
        if (messageFromDoctor) this.unreadForPatient = (this.unreadForPatient == null ? 0 : this.unreadForPatient) + 1;
        else this.unreadForDoctor = (this.unreadForDoctor == null ? 0 : this.unreadForDoctor) + 1;
    }

    public void markReadByDoctor()  { this.unreadForDoctor  = 0; }
    public void markReadByPatient() { this.unreadForPatient = 0; }
}
