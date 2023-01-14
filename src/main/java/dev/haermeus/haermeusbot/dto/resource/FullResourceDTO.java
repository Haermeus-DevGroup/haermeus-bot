package dev.haermeus.haermeusbot.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullResourceDTO {

    private Long id;
    private Long parentId;
    private String title;
    private String content;
}
