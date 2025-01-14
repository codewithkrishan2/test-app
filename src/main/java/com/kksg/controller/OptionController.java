package com.kksg.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kksg.dtos.OptionRequestDTO;
import com.kksg.dtos.OptionResponseDTO;
import com.kksg.entity.Option;
import com.kksg.service.OptionService;
import com.kksg.service.impl.OptionServiceImpl;

@RestController
@RequestMapping("/api/v1/option")	
public class OptionController extends BaseController<Option, OptionResponseDTO, OptionRequestDTO> {

	private OptionService optionService;
	private ModelMapper modelMapper;
	
	public OptionController(OptionServiceImpl optionService, ModelMapper modelMapper) {
		super(optionService);
		this.optionService = optionService;
		this.modelMapper = modelMapper;
	}
	@Override
	protected Option mapToEntity(OptionRequestDTO dto) {
		return modelMapper.map(dto, Option.class);
	}
	@Override
	protected OptionResponseDTO mapToResponse(Option entity) {
		return modelMapper.map(entity, OptionResponseDTO.class);
	}
	
}
