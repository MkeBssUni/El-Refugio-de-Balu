package com.balu.backend.modules.adoptionRequests.model.dto;

import com.balu.backend.modules.statusses.model.Statusses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStatusAdoptionRequestDto {
    private String adoptionId;
    private Statusses status;
}
