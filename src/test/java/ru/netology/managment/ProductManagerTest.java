package ru.netology.managment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    private Book first = new Book(1, "Книга 1", 100, "Автор 1");
    private Book second = new Book(2, "Книга 2", 200, "Автор 2");
    private Smartphone third = new Smartphone(3, "Смартфон 1", 300, "Производитель 1");
    private Repository repository;
    private ProductManager manager;

    @BeforeEach
    public void setUp() {
        repository = new Repository();
        manager = new ProductManager(repository);

        manager.add(first);
        manager.add(second);
        manager.add(third);
    }

    @Test
    public void shouldSearchBy() {
        Product[] expected = {first, second};
        Product[] actual = manager.searchBy("Книга");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByNumbers() {
        Product[] expected = {first, third};
        Product[] actual = manager.searchBy("1");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnNoSearchResults() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("Производитель");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMatch() {
        assertTrue(manager.matches(third, "Смартфон"));
    }

    @Test
    public void shouldNotMatch() {
        assertFalse(manager.matches(third, "Производитель"));
    }

}