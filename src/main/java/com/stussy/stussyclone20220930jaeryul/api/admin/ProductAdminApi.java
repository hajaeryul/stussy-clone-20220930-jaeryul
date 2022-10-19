package com.stussy.stussyclone20220930jaeryul.api.admin;

import com.stussy.stussyclone20220930jaeryul.aop.annotation.LogAspect;
import com.stussy.stussyclone20220930jaeryul.aop.annotation.ValidAspect;
import com.stussy.stussyclone20220930jaeryul.dto.CMRespDto;
import com.stussy.stussyclone20220930jaeryul.dto.admin.ProductRegisterReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;//valid 사용해도댄당
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/admin")
public class ProductAdminApi {

    @LogAspect
    @ValidAspect
    @PostMapping("/product")
    public ResponseEntity<?> registerProductMst(@Valid @RequestBody ProductRegisterReqDto productRegisterReqDto,
                                                BindingResult bindingResult) {

        return ResponseEntity.created(null)
                .body(new CMRespDto<>("Register Succesfully", null));
    }
    @GetMapping("/product/category")
    public ResponseEntity<?> getCategoryList() {
        return ResponseEntity.ok().body(new CMRespDto<>("Get successfully", null));
    }
}
