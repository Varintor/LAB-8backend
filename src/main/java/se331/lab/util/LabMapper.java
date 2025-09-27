package se331.lab.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import se331.lab.entity.*;
import java.util.List;

@Mapper
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);

    EventDTO getEventDTO(Event event);
    List<EventDTO> getEventDTOs(List<Event> events);

    OrganizerDTO getOrganizerDTO(Organizer organizer);
    List<OrganizerDTO> getOrganizerDTO(List<Organizer> organizers);

    @Mapping(target = "eventHistory", source = "eventHistory", qualifiedByName = "toEventSummaryDTOList")
    ParticipantDTO getParticipantDTO(Participant participant);
    List<ParticipantDTO> getParticipantDTOs(List<Participant> participants);

    @Named("toEventSummaryDTO")
    EventSummaryDTO toEventSummaryDTO(Event event);

    @Named("toEventSummaryDTOList")
    default List<EventSummaryDTO> toEventSummaryDTOList(List<Event> events) {
        if (events == null) return null;
        return events.stream().map(this::toEventSummaryDTO).toList();
    }
}
