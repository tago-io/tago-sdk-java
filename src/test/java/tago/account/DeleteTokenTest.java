package tago.account;

import domain.CreateTokenResult;
import domain.StringResult;
import model.account.Account;
import model.account.Token;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeleteTokenTest {
    
    public DeleteTokenTest() {
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
    public void testCreateToken() {
        System.out.println("Account create token");
        Account a = new Account();
        
        Token t = new Token();
        t.name = "JAVA SDK test";
        t.permission = "full";
        t.email = System.getenv("TAGO_ACCOUNT_EMAIL");
        t.password = System.getenv("TAGO_ACCOUNT_PASSWORD");
        CreateTokenResult tsr = a.tokenCreate(t);
        
        a = new Account(tsr.result.token);
        StringResult sr = a.tokenDelete();

        assertEquals(sr.status, true);
    }
}
