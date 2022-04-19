package ru.netology.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    private Book first = new Book(1, "Книга 1", 100, "Автор 1");
    private Book second = new Book(2, "Книга 2", 200, "Автор 2");
    private Smartphone third = new Smartphone(3, "Смартфон 1", 300, "Производитель 1");
    private Repository repository;

    @BeforeEach
    public void setUp() {
        repository = new Repository();
    }

    @Test
    public void shouldAddIfNoItems() {
        repository.add(first);

        Product[] expected = {first};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddIfContainsItems() {
        repository.add(first);
        repository.add(second);

        Product[] expected = {first, second};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddDifferentProducts() {
        repository.add(first);
        repository.add(third);

        Product[] expected = {first, third};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotAddDuplicateIds() {
        repository.add(first);
        repository.add(first);

        Product[] expected = {first};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        repository.add(first);
        repository.add(second);
        repository.add(third);
        repository.removeById(2);

        Product[] expected = {first, third};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIdIfContainsOneItem() {
        repository.add(second);
        repository.removeById(2);

        Product[] expected = {};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFailIfNoId() {
        repository.add(second);
        repository.removeById(1);

        Product[] expected = {second};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldContainId() {
        repository.add(first);
        repository.add(third);

        assertTrue(repository.containsId(3));
    }

    @Test
    public void shouldNotContainId() {
        repository.add(first);
        repository.add(third);

        assertFalse(repository.containsId(50));
    }
}