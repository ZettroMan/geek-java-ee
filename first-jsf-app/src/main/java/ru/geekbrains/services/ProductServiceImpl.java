package ru.geekbrains.services;

import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductServiceImpl implements ProductService {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(ProductDto::new).collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);
        if(product == null) return null;
        return new ProductDto(product);
    }

    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(ProductDto productDto) {
        Category category = null;
        if(productDto.getCategoryId() != null) {
            category = categoryRepository.getReference(productDto.getCategoryId());
        }
        productRepository.saveOrUpdate(new Product(productDto, category));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {

    }
}
