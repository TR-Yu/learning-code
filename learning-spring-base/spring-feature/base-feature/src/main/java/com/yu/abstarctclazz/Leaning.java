package com.yu.abstarctclazz;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * leaning 类
 *
 * @author YU
 * @date 2022-05-09 18:00
 */
@Component("leaning")
public class Leaning extends AbstractLearn{
    @Override
    public void print() {
        System.out.println("implant the method's print of abstract LeanAbstract");
    }

    @Override
    public void plant() {
        System.out.println("plant the leaning's plant method");
    }

    private static class Book{
        private String bookName;
        private BigDecimal price;

        public Book() {
        }

        public Book(String bookName, BigDecimal price) {
            this.bookName = bookName;
            this.price = price;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}
