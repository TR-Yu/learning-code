package com.yu.threadexception;

/**
 * 银行转转
 *
 * @author YU
 * @date 2022-06-04 17:47
 */
public class Account {
    private int balance;

    void transfer(Account target, int amt) {
        synchronized (Account.class){
            if(this.balance > amt) {
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }

    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance(){
        return this.balance;
    }
}
