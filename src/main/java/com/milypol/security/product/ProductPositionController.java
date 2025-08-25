package com.milypol.security.product;

import com.milypol.security.productEvent.ProductEventService;
import com.milypol.security.productEvent.ProductEventType;
import com.milypol.security.warehouse.Warehouse;
import com.milypol.security.warehouse.WarehouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product-positions")
public class ProductPositionController {
    private final ProductService productService;
    private final WarehouseService warehouseService;
    private final ProductEventService productEventService;

    public ProductPositionController(ProductService productService, WarehouseService warehouseService, ProductEventService productEventService) {
        this.productService = productService;
        this.warehouseService = warehouseService;
        this.productEventService = productEventService;
    }
    @GetMapping("{id}")
    public String infoProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("productPosition", productService.getProductById(id));
        model.addAttribute("productEvents", productEventService.getProductEventsByProduct(id));
        model.addAttribute("productEventTypeDelivery", ProductEventType.DELIVERY);
        model.addAttribute("productEventTypeReturn", ProductEventType.RETURN);
        model.addAttribute("productEventTypeTransfer", ProductEventType.TRANSFER);
        model.addAttribute("productEventTypeUsed", ProductEventType.USED);
        return "product/info";
    }
    @GetMapping("/add/{warehouseId}")
    public String addProduct(@PathVariable Integer warehouseId ,Model model) {
        Warehouse warehouse = warehouseService.findById(warehouseId)
                .orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe ID magazynu: " + warehouseId));
        ProductPosition productPosition = new ProductPosition();
        productPosition.setWarehouse(warehouse);
        model.addAttribute("productPosition", productPosition);
        return "product/edit";
    }

    @GetMapping("/edit/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("productPosition", productService.getProductById(id));
        return "product/edit";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute ProductPosition product) {
        productService.saveProduct(product);
        return "redirect:/warehouses/" + product.getWarehouse().getId() ;
    }
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/warehouses/" + productService.getProductById(id).getWarehouse().getId();
    }
}
