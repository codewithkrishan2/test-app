package com.kksg.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kksg.dtos.OptionValueRequestDTO;
import com.kksg.dtos.OptionValueResponseDTO;
import com.kksg.entity.OptionValue;
import com.kksg.service.OptionValueService;
import com.kksg.service.impl.OptionValueServiceImpl;

@RestController
@RequestMapping("/api/v1/optionvalue")	
public class OptionValueController extends BaseController<OptionValue, OptionValueResponseDTO, OptionValueRequestDTO> {

	private OptionValueService optionValueService;
	private ModelMapper modelMapper;
	
	public OptionValueController(OptionValueServiceImpl optionValueService, ModelMapper modelMapper) {
		super(optionValueService);
		this.optionValueService = optionValueService;
	}

	@Override
	protected OptionValue mapToEntity(OptionValueRequestDTO dto) {
		return modelMapper.map(dto, OptionValue.class);
	}

	@Override
	protected OptionValueResponseDTO mapToResponse(OptionValue entity) {
		return modelMapper.map(entity, OptionValueResponseDTO.class);
	}
	
}
