package tago.analysis;

import domain.AnalysisResult;
import domain.FindDataCountResult;
import model.account.Account;
import model.analysis.Analysis;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListTest {
    
    public ListTest() {
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
    public void test() {
        System.out.println("Analysis list");
        Account account = new Account();
        AnalysisResult ar = account.analysis.list();

        assertEquals(ar.status, true);
    }
}
