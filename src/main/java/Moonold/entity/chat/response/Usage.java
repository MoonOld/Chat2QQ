package Moonold.entity.chat.response;

import lombok.Data;

@Data
public class Usage {
    Integer prompt_tokens;
    Integer completion_tokens;
    Integer total_tokens;
}
