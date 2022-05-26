package me.sa1zer_.sporterbook.payload.request.trainer;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AcceptVisitRequest {

    private Long id;
    @NotNull
    private Long ttinfo;
    @NotNull
    private Long student;

    private boolean visit;
}
