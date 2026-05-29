package product_store.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ProductResource implements ProductController {

    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<ProductDTO> create(ProductDTO dto) {
        final Product product = ProductParser.to(dto);
        final Product created = productService.create(product);
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri()
        ).body(ProductParser.to(created));
    }

    @Override
    public ResponseEntity<List<ProductDTO>> findAll() {
        return ResponseEntity.ok(
            productService.findByAll()
                .stream()
                .map(ProductParser::to)
                .toList()
        );
    }

    @Override
    public ResponseEntity<ProductDTO> findById(String id) {
        Product product = productService.findById(id);
        return (product == null) ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(ProductParser.to(product));
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> healthCheck() {
        return ResponseEntity.ok().build();
    }
}