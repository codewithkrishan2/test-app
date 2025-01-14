package com.kksg.service;

import com.kksg.dtos.ProductRequestDTO;
import com.kksg.dtos.ProductResponseDTO;

public interface ProductService {

	ProductResponseDTO save(ProductRequestDTO request);

}
