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


    public RegisterCriteria() {
        this.pageNum = 1;
        this.amount = 10;
    }


    public String[] getTypeArr()    {
        return type == null ? new String[] {} : type.split("");
    }
}
