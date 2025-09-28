package nazeem.web.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import nazeem.web.model.Product;
import nazeem.web.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private nazeem.web.service.ProductService productService;

    @Autowired
    private nazeem.web.service.ProductTypeService productTypeService;

    private String add_edit_template="/admin/product/add-edit-product";
    private String list_template="/admin/product/list-product";
    private String list_redirect="redirect:/product/list";


    @GetMapping("/add")
    public String addProduct(Product product, Model model){
        model.addAttribute("product", product);
        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        return add_edit_template;
    }


    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") long id, Model model){
        Product product = productService.get(id);
        model.addAttribute("product", product);

        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model){
        model.addAttribute("product", product);
        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        if(result.hasErrors()){
            return add_edit_template;
        }

        productService.save(product);
        return list_redirect+"?success";
    }



    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        productService.delete(id);

        return list_redirect+"?deleted";
    }

    @GetMapping("/list")
    public String listProduct(Model model) {
        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        List<Product> listProducts = productService.listAll();
        model.addAttribute("listProducts", listProducts);

        return list_template;
    }
    
    
    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=product_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
         
        List<Product> listProduct = productService.listAll();
 
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Product Id", "product", " brand", "madein", "price"};
        String[] nameMapping = {"id", "name", "brand", "madein", "price"};
         
        csvWriter.writeHeader(csvHeader);
         
        for (Product product : listProduct) {
            csvWriter.write(product, nameMapping);
        }
         
        csvWriter.close();
         
    }
}