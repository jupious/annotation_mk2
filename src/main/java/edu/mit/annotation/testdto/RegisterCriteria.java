package edu.mit.annotation.testdto;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class RegisterCriteria {

    private int pageNum;
    private int amount;
    private int offset;

    private String type;
    private String keyword;
    private Integer flag;
    private String startDate;
    private String endDate;

    public RegisterCriteria() {
        this.pageNum = 1;
        this.amount = 10;
        this.flag=1;
        this.type="IC";
        this.keyword="";
        this.startDate= String.valueOf(LocalDate.now());
        this.endDate = String.valueOf(LocalDate.now().plusMonths(1));
    }

    public LocalDate getParsedStartDate() {
        return startDate != null ? LocalDate.parse(startDate) : LocalDate.now();
    }

    public LocalDate getParsedEndDate() {
        return endDate != null ? LocalDate.parse(endDate) : LocalDate.now().plusMonths(1);
    }

    public String[] getTypeArr()    {
        return type == null ? new String[] {} : type.split("");
    }
}
