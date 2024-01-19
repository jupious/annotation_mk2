package edu.mit.annotation.realdto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseDTO {
    private String code;
    private String status;
    private String message;
    private Object item;
}
