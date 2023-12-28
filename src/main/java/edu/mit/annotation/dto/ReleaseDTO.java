package edu.mit.annotation.dto;

import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReleaseDTO {
    //출고번호
    private Integer releaseNum;
    //출고내용 리스트
    private List<ReleaseDetailDTO> releaseDetailList;
}