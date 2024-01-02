package edu.mit.annotation.testdto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemporalReceiveDTO {
    private Integer receiveNum;
    private Date receiveDate;
    private String itemCode;
    private Integer fairQuantity;
    private Integer returnQuantity;
}
