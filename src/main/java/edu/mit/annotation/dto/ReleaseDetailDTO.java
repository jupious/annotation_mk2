package edu.mit.annotation.dto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReleaseDetailDTO {
    //출고번호
    private Integer releaseNum;
    //품목코드
    private String itemCode;
    //출고일
    private Date releaseDate;
    //출고수량
    private Integer releaseQuantity;
}
