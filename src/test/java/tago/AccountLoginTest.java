package tago;

import domain.AccountProfileResult;
import model.account.Account;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountLoginTest {
    
    public AccountLoginTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testLogin() {
        System.out.println("Account login");
        Account a = new Account();
        
        String email = System.getenv("TAGO_ACCOUNT_EMAIL");
        String password = System.getenv("TAGO_ACCOUNT_PASSWORD");
        AccountProfileResult log = a.login(email, password);

        assertEquals(log.status, true);
    }
}
