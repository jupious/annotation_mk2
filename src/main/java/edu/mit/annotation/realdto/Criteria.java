package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Criteria {
    private Integer pageNum;
    private Integer amount;
    private String type;
    private String keyword;
    private Date startDate;
    private Date endDate;
    private String order;

    public Criteria(){
        this(0,10);
    }

    public Criteria(int pageNum, int amount){
        this.pageNum = pageNum;
        this.amount = amount;
    }
}
