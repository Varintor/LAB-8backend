// ParticipantSummaryDTO.java
package se331.lab.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParticipantSummaryDTO {
    Long id;
    String name;
    String telNo;
}
