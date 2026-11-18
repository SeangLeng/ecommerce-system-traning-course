package mptc.seangleng.ecommerce.customer.client.exception;

public class UserServiceUnavailable extends RuntimeException {
    public UserServiceUnavailable(String message) {
        super(message);
    }
}
