package edu.mit.annotation.testdto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoopCompDTO {
    private String compNum;
    private String compName;
    private String address;
    private String tel;
    private String managerName;
    //거래일자
    private Date today;
}
