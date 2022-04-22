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
        if (containsId(product.getId())) {
            return;
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
        Product removeItem = findById(id);
        if (removeItem == null) {
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

    public boolean containsId(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return true;
            }
        }
        return false;
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
