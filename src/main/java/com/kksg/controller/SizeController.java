package com.kksg.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kksg.dtos.SizeRequestDTO;
import com.kksg.dtos.SizeResponseDTO;
import com.kksg.entity.Size;
import com.kksg.service.SizeService;
import com.kksg.service.impl.SizeServiceImpl;

@RestController
@RequestMapping("/api/v1/size")
public class SizeController extends BaseController<Size, SizeResponseDTO, SizeRequestDTO> {

	private final SizeService sizeService;
	private ModelMapper modelMapper;

	public SizeController(SizeServiceImpl sizeService, ModelMapper modelMapper) {
		super(sizeService); // Pass CategoryServiceImpl to BaseController
		this.sizeService = sizeService; // Initialize categoryService field
		this.modelMapper = modelMapper;
	}

	@Override
	protected Size mapToEntity(SizeRequestDTO dto) {
		return modelMapper.map(dto, Size.class);
	}

	@Override
	protected SizeResponseDTO mapToResponse(Size entity) {
		return modelMapper.map(entity, SizeResponseDTO.class);
	}



}