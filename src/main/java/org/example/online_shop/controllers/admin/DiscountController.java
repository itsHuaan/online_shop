package org.example.online_shop.controllers.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.online_shop.utils.Const;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "04. Discount")
@RestController
@RequestMapping(value = Const.API_PREFIX + "/discount")
public class DiscountController {

}
