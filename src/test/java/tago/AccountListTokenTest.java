package tago;

import domain.TokenResult;
import model.account.Account;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountListTokenTest {
    
    public AccountListTokenTest() {
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
    public void testListToken() {
        System.out.println("Account token list");
        Account a = new Account();
        
        TokenResult tr = a.tokenList();
        
        assertEquals(tr.status, true);        
    }
}
