package tago;

import domain.BucketCreateResult;
import domain.StringResult;
import model.account.Account;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BucketEditTest {
    
    public BucketEditTest() {
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
    public void testProfileCreate() {
        System.out.println("Bucket edit");
        Account account = new Account();
        account.bucket.name = "Created by JAVA SDK";
        account.bucket.create();
        account.bucket.name = "Edited by JAVA SDK";
        StringResult res = account.bucket.edit();
        account.bucket.delete();
        assertEquals(res.status, true);        
    }
}
