package product_store.product;

public class ProductParser {

    public static ProductDTO to(Product product) {
        return ProductDTO.builder()
            .name(product.name())
            .price(product.price())
            .unit(product.unit())
            .build();
    }

    public static Product to(ProductDTO dto) {
        return Product.builder()
            .name(dto.name())
            .price(dto.price())
            .unit(dto.unit())
            .build();
    }
}