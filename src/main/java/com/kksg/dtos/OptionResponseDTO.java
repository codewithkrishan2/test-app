package com.kksg.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OptionResponseDTO extends BaseResponseDTO {

    private String name;

    private List<OptionValueResponseDTO> optionValues = new ArrayList<>();
}
