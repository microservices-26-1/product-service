package product_store.product;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
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

        // if (account.password() == null || account.password().trim().length() == 0) {
        //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is empty");
        // }

        // account.passwordSha256(calcHash(account.password()));

        return productRepository.save(
            new ProductModel(product)
        ).to();
    }
    
    public List<Product> findByAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
            .map(ProductModel::to)
            .toList();
    }

    public Product findById(String id) {
        return productRepository.findById(id).orElse(null).to();
    }

    // public void delete(String id) {
    //     accountRepository.deleteById(id);
    // }



    // public Account findByEmailAndPassword(String email, String password) {
    //     String sha256 = calcHash(password);
    //     return accountRepository.findByEmailAndPasswordSha256(email, sha256).orElse(null).to();
    // }

    // private String calcHash(String text) {
    //     try {
    //         MessageDigest md = MessageDigest.getInstance("SHA-256");
    //         md.update(text.getBytes(StandardCharsets.UTF_8));
    //         byte[] digest = md.digest();
    //         return Base64.getEncoder().encodeToString(digest);
    //     } catch (NoSuchAlgorithmException e) {
    //         throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    //     }
    // }
    
}