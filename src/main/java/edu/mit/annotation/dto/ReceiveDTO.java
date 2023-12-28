package edu.mit.annotation.dto;

import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiveDTO {
    //입고번호
    private Integer receiveNum;
    //입고내용 리스트
    private List<ReceiveDetailDTO> receiveDetailList;
}
