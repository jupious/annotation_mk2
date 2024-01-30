package edu.mit.annotation.testdto;

import lombok.*;

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

    public RegisterCriteria() {
        this.pageNum = 1;
        this.amount = 10;
        this.flag=1;
        this.type="IC";
        this.keyword="";
    }


    public String[] getTypeArr()    {
        return type == null ? new String[] {} : type.split("");
    }
}
