package com.simplify.multipleDatasource.controller;

import com.simplify.multipleDatasource.dao.product.ProductRepository;
import com.simplify.multipleDatasource.dao.user.UserRepository;
import com.simplify.multipleDatasource.model.product.Product;
import com.simplify.multipleDatasource.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;

@Controller
public class MultiDataSourceController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/addUser")
    @ResponseBody
    public Object addUser(){

        User user = new User();
        user.setName("John");
        user.setEmail("john@test.com");
        user.setAge(20);

        User savedUser = userRepository.save(user);

        return savedUser;
    }

    @RequestMapping(value = "/addProduct")
    @ResponseBody
    public String addProduct(){

        Product product = new Product();
        product.setName("product1");
        product.setPrice(100);

        Product savedProduct = productRepository.save(product);

        return String.valueOf(savedProduct.getId());
    }

    @RequestMapping(value = "/addUserAndProduct")
    @ResponseBody
    public String addUserAndProduct(){

        User user = new User();
        user.setName("John");
        user.setEmail("john@test.com");
        user.setAge(20);

        User savedUser = userRepository.save(user);

        Product product = new Product();
        product.setName("product1");
        product.setPrice(100);
        product.setUserId(savedUser.getId());

        Product savedProduct = productRepository.save(product);

        return savedUser.getId() + "/" + savedProduct.getId() + "-" + savedProduct.getUserId();
    }

    @RequestMapping(value = "/addUserAndProductNonTransactional")
    @ResponseBody
    public String addUserAndProductNonTransactional(){

        User user = new User();
        user.setName("John");
        user.setEmail("john@test.com");
        user.setAge(20);

        User savedUser = userRepository.save(user);

        Product product = new Product();
        product.setName("product1");
        product.setPrice(100);
        //product.setUserId(savedUser.getId());
        product.setUserId(19);

        Product savedProduct = productRepository.save(product);

        return savedUser.getId() + "/" + savedProduct.getId() + "-" + savedProduct.getUserId();
    }

    @RequestMapping(value = "/addUserAndProductTransactionalOk")
    @ResponseBody
    @Transactional
    public String addUserAndProductTransactionalOk(){

        User user = new User();
        user.setName("John");
        user.setEmail("john@test.com");
        user.setAge(20);

        User savedUser = userRepository.save(user);

        Product product = new Product();
        product.setName("product1");
        product.setPrice(100);
        product.setUserId(savedUser.getId());
        //product.setUserId(19);

        Product savedProduct = productRepository.save(product);

        return savedUser.getId() + "/" + savedProduct.getId() + "-" + savedProduct.getUserId();
    }

    @RequestMapping(value = "/addUserAndProductTransactionalError")
    @ResponseBody
    @Transactional
    public String addUserAndProductTransactionalError(){

        User user = new User();
        user.setName("John");
        user.setEmail("john@test.com");
        user.setAge(20);

        User savedUser = userRepository.save(user);

        Product product = new Product();
        product.setName("product1");
        product.setPrice(100);
        //product.setUserId(savedUser.getId());
        product.setUserId(19);

        Product savedProduct = productRepository.save(product);

        return savedUser.getId() + "/" + savedProduct.getId() + "-" + savedProduct.getUserId();
    }
}
