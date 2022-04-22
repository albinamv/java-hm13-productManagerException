package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Repository {
    private Product[] products = new Product[0];

    public Product[] findAll() {
        return products;
    }

    public void add(Product product) {
        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException("Нельзя добавить товар с id " + product.getId() + ", т.к. такой id уже существует");
        } else {
            int length = products.length + 1;
            Product[] tmp = new Product[length];
            System.arraycopy(products, 0, tmp, 0, products.length);

            int lastIndex = tmp.length - 1;
            tmp[lastIndex] = product;
            products = tmp;
        }

    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Не найден товар с id " + id);
        } else {
            int length = products.length - 1;
            Product[] tmp = new Product[length];
            int index = 0;
            for (Product product : products) {
                if (product.getId() != id) {
                    tmp[index] = product;
                    index++;
                }
            }
            products = tmp;
        }
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
