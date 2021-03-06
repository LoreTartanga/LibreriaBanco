/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientside.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity representing bank accounts for customers. It contains the following
 * fields: account id, account type, account description, initial balance, initial balance date, 
 * current balance and credit limit. It also contains relational fields for getting
 * customers owning the account and movements or transactions made on the account.  
 * @author javi
 */
@XmlRootElement
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Identification field for the account.
     */
     private Long id;
    /**
     * Type of the account.
     */
    private AccountType type;
    /**
     * Description of the account.
     */
    private String description;
    /**
     * Current balance of the account.
     */
    private Double balance;
    /**
     * Limit for the credit line. The balance can be negative but not below this
     * limit. Do note that the limit is stored always as a positive value. 
     */
    private Double creditLine;
    /**
     * Begin balance of the account. Normally it is set when opening the account.
     * It is useful to reconcile balance and movements in conjuction with its corresponding
     * timestamp.
     */
    private Double beginBalance;
    /**
     * Begin balance timestamp.
     */
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date beginBalanceTimestamp;
    /**
     * Relational field containing Customers owning the account. 
     */
    private List<Customer> customers;
    /**
     * Relational field containing the list of movements on the account.
     */
    private List<Movement> movements;
    /**
     * 
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * 
     * @param id the id to be set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the type
     */
    public AccountType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(AccountType type) {
        this.type = type;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    /**
     * Limit for the credit line. The balance can be negative but not below this
     * limit. Do note that the limit is stored always as a positive value.
     * @return the creditLine
     */
    public Double getCreditLine() {
        return creditLine;
    }

    /**
     * Limit for the credit line. The balance can be negative but not below this
     * limit. Do note that the limit is stored always as a positive value.
     * @param creditLine the creditLine to set
     */
    public void setCreditLine(Double creditLine) {
        this.creditLine = creditLine;
    }

    /**
     * Begin balance of the account. Normally it is set when opening the account.
     * It is useful to reconcile balance and movements in conjuction with its corresponding
     * timestamp.
     * @return the beginBalance
     */
    public Double getBeginBalance() {
        return beginBalance;
    }
    /**
     * Begin balance of the account. Normally it is set when opening the account.
     * It is useful to reconcile balance and movements in conjuction with its corresponding
     * timestamp.
     * @param beginBalance the beginBalance to set
     */
    public void setBeginBalance(Double beginBalance) {
        this.beginBalance = beginBalance;
    }
    /**
     * Begin balance timestamp.
     * @return the beginBalanceTimestamp
     */
    public Date getBeginBalanceTimestamp() {
        return beginBalanceTimestamp;
    }
    /**
     * Begin balance timestamp.
     * @param beginBalanceTimestamp the beginBalanceTimestamp to set
     */
    public void setBeginBalanceTimestamp(Date beginBalanceTimestamp) {
        this.beginBalanceTimestamp = beginBalanceTimestamp;
    }
    /**
     * Relational field containing Customers owning the account.
     * @return the customers
     */
    @XmlTransient
    public List<Customer> getCustomers() {
        return customers;
    }
    /**
     * Relational field containing Customers owning the account.
     * @param customers the customers to set
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    /**
     * Relational field containing the list of movements on the account.
     * @return the movements
     */
    public List<Movement> getMovements() {
        return movements;
    }

    /**
     * Relational field containing the list of movements on the account.
     * @param movements the movements to set
     */
    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }
    /**
     * Integer representation for Account instance.
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    /**
     * Compares two Account objects for equality. This method consider a Account 
     * equal to another Account if their id fields have the same value. 
     * @param object The other Account object to compare to.
     * @return true if ids are equals.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    /**
     * Obtains a string representation of the Account.
     * @return The String representing the Account.
     */
    @Override
    public String toString() {
        return type+" # " + id ;
    }
    
}
