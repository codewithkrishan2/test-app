package com.kksg.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kksg.dtos.ProductImageRequestDTO;
import com.kksg.dtos.ProductRequestDTO;
import com.kksg.dtos.ProductResponseDTO;
import com.kksg.dtos.ProductVariantRequestDTO;
import com.kksg.entity.Category;
import com.kksg.entity.OptionValue;
import com.kksg.entity.Product;
import com.kksg.entity.ProductImage;
import com.kksg.entity.ProductVariant;
import com.kksg.repo.CategoryRepo;
import com.kksg.repo.OptionValueRepo;
import com.kksg.repo.ProductImageRepo;
import com.kksg.repo.ProductOptionRepo;
import com.kksg.repo.ProductRepo;
import com.kksg.repo.ProductVarientRepo;
import com.kksg.service.BaseService;
import com.kksg.service.ProductService;

@Service
public class ProductServiceImpl extends BaseService<Product, Long> implements ProductService {

	private ProductRepo productRepository;
	private ModelMapper modelMapper;
	private CategoryRepo categoryRepository;
	private OptionValueRepo optionValueRepository;
	private ProductImageRepo productImageRepository;
	private ProductVarientRepo productVarientRepository;
	private ProductOptionRepo optionRepository;
	
	
	public ProductServiceImpl(ProductRepo productRepository, ModelMapper modelMapper, CategoryRepo categoryRepository, 
			OptionValueRepo optionValueRepository, ProductImageRepo productImageRepository, ProductVarientRepo productVarientRepository,
			ProductOptionRepo optionRepository) {
        super(productRepository, productRepository);
        this.productRepository = productRepository;
		this.modelMapper = modelMapper;
		this.categoryRepository = categoryRepository;
		this.optionValueRepository = optionValueRepository;
		this.productImageRepository = productImageRepository;
		this.productVarientRepository = productVarientRepository;
		this.optionRepository = optionRepository;
    }

	@Override
	protected Specification<Product> prepareFilter() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), false);
	}
	
	@Override
	protected Product preProcessBeforeSave(Product product) {
	    return super.preProcessBeforeSave(product);
	}


	@Override
	public ProductResponseDTO save(ProductRequestDTO productRequestDTO) {
		
		// Fetch the category entity
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Create the product entity
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setDetails(productRequestDTO.getDetails());
        product.setBrand(productRequestDTO.getBrand());
        product.setCategory(category);
        
        // Save the product
        Product savedProduct = productRepository.save(product);

        // Save variants
        if (productRequestDTO.getVariants() != null) {
            for (ProductVariantRequestDTO variantRequest : productRequestDTO.getVariants()) {
                ProductVariant productVariant = new ProductVariant();
                productVariant.setSku(variantRequest.getSku());
                productVariant.setMrp(variantRequest.getMrp());
                productVariant.setSellingPrice(variantRequest.getSellingPrice());
                productVariant.setQuantity(variantRequest.getQuantity());
                productVariant.setProduct(product);

                // Save variant option values
                if (variantRequest.getOptionValueIds() != null) {
                    for (Long optionValueId : variantRequest.getOptionValueIds()) {
                        OptionValue optionValue = optionValueRepository.findById(optionValueId)
                                .orElseThrow(() -> new RuntimeException("OptionValue not found"));
                        productVariant.getOptionValues().add(optionValue);
                    }
                }

                // Save variant
                productVariant = productVarientRepository.save(productVariant);

                // Save variant images
                if (variantRequest.getImages() != null) {
                    for (ProductImageRequestDTO imageRequest : variantRequest.getImages()) {
                        ProductImage productImage = new ProductImage();
                        productImage.setImageUrl(imageRequest.getImageUrl());
                        productImage.setImageType(imageRequest.getImageType());
                        productImage.setImageOrder(imageRequest.getImageOrder());
                        productImage.setAltText(imageRequest.getAltText());
                        productImage.setVariant(productVariant);

                        // Save image
                        productImageRepository.save(productImage);
                    }
                }
            }
        }

        ProductResponseDTO responseDTO = this.modelMapper.map(savedProduct, ProductResponseDTO.class);
        
		return responseDTO;
	};	
}
