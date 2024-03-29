package com.stussy.stussyclone20220930jaeryul.Controller;

import com.stussy.stussyclone20220930jaeryul.dto.CheckoutRespDto;
import com.stussy.stussyclone20220930jaeryul.security.PrincipalDetails;
import com.stussy.stussyclone20220930jaeryul.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/collections/{category}")
    public String loadCollections(@PathVariable String category) {
        return "product/collections_number";
    }

    @GetMapping("/product/{pdtId}")
    public String loadProductDetails(@PathVariable String pdtId) {
        return "product/product_detail";
    }

    @GetMapping("/checkout")
    public String loadPayment(Model model,
                              @RequestParam int pdtDtlId,
                              @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
        CheckoutRespDto checkoutRespDto = productService.getCheckoutProduct(pdtDtlId);
        model.addAttribute("data", checkoutRespDto);
        model.addAttribute("user", principalDetails.getUser());
        return "product/product_order";
    }
}
