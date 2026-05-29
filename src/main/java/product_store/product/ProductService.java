package product_store.product;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product) {
        if (product.name() == null || product.name().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field 'name' is required");
        }
        if (product.price() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field 'price' is required");
        }
        if (product.unit() == null || product.unit().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field 'unit' is required");
        }
        return productRepository.save(new ProductModel(product)).to();
    }

    public List<Product> findByAll() {
        List<Product> products = StreamSupport.stream(productRepository.findAll().spliterator(), false)
            .map(ProductModel::to)
            .toList();
        if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products found");
        }
        return products;
    }

    public Product findById(String id) {
        return productRepository.findById(id)
            .map(ProductModel::to)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    public void delete(String id) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        productRepository.deleteById(id);
    }

}