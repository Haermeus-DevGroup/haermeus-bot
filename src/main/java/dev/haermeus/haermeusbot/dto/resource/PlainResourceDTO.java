package dev.haermeus.haermeusbot.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlainResourceDTO {

    private Long id;
    private Long parentId;
    private String title;

}
