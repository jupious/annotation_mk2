package edu.mit.annotation.testdto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Criteria {
    private int pageNum;
    private int amount;
    private int offset;

    private String type;
    private String keyword;

    public Criteria()   {
        this(1,10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum*amount;
        this.amount = amount;
        this.offset = (pageNum - 1) * amount;
    }

    public String[] getTypeArr()    {
        return type == null ? new String[] {} : type.split("");
    }
}
