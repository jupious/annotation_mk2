package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class CommentDTO {
    private Long cno;
    private Long bno;
    private String email;
    private String name;
    private String content;
    private Date comm_regdate;
}
